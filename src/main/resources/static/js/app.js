// API 基礎 URL
const API_URL = '/api/stations';
const FEEDBACK_API_URL = '/api/feedbacks';

// 頁面載入時執行
document.addEventListener('DOMContentLoaded', function() {
    loadStations();
    setupFormSubmit();
    loadFeedbacks();
    loadStatistics();
    setupFeedbackFormSubmit();
});

/**
 * 載入所有車站
 */
async function loadStations() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('載入車站失敗');
        }
        const stations = await response.json();
        displayStations(stations);
    } catch (error) {
        console.error('載入車站錯誤:', error);
        showError('載入車站資料失敗，請重新整理頁面');
    }
}

/**
 * 顯示車站列表
 */
function displayStations(stations) {
    const tbody = document.getElementById('stationList');
    
    if (stations.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="5" class="text-center text-muted">
                    <i class="bi bi-inbox"></i> 目前沒有車站資料
                </td>
            </tr>
        `;
        return;
    }
    
    tbody.innerHTML = stations.map(station => `
        <tr>
            <td><strong>${escapeHtml(station.code)}</strong></td>
            <td>${escapeHtml(station.name)}</td>
            <td>
                <span class="badge ${station.line === '紅線' ? 'bg-danger' : 'bg-warning text-dark'}">
                    ${escapeHtml(station.line)}
                </span>
            </td>
            <td>${formatDateTime(station.createdAt)}</td>
            <td class="text-center">
                <button class="btn btn-sm btn-outline-danger" onclick="deleteStation(${station.id}, '${escapeHtml(station.name)}')">
                    <i class="bi bi-trash"></i> 刪除
                </button>
            </td>
        </tr>
    `).join('');
}

/**
 * 設定表單提交事件
 */
function setupFormSubmit() {
    const form = document.getElementById('stationForm');
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        const station = {
            code: document.getElementById('code').value.trim(),
            name: document.getElementById('name').value.trim(),
            line: document.getElementById('line').value
        };
        
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(station)
            });
            
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || '新增車站失敗');
            }
            
            // 成功後重置表單並重新載入列表
            form.reset();
            showSuccess('車站新增成功！');
            loadStations();
        } catch (error) {
            console.error('新增車站錯誤:', error);
            showError(error.message || '新增車站失敗，請檢查輸入資料');
        }
    });
}

/**
 * 刪除車站
 */
async function deleteStation(id, name) {
    if (!confirm(`確定要刪除車站「${name}」嗎？`)) {
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || '刪除車站失敗');
        }
        
        showSuccess('車站刪除成功！');
        loadStations();
    } catch (error) {
        console.error('刪除車站錯誤:', error);
        showError(error.message || '刪除車站失敗');
    }
}

/**
 * 格式化日期時間
 */
function formatDateTime(dateTimeString) {
    const date = new Date(dateTimeString);
    return date.toLocaleString('zh-TW', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    });
}

/**
 * 跳脫 HTML 特殊字元
 */
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

/**
 * 顯示成功訊息
 */
function showSuccess(message) {
    showAlert(message, 'success');
}

/**
 * 顯示錯誤訊息
 */
function showError(message) {
    showAlert(message, 'danger');
}

/**
 * 顯示提示訊息
 */
function showAlert(message, type) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show position-fixed top-0 start-50 translate-middle-x mt-3`;
    alertDiv.style.zIndex = '9999';
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    document.body.appendChild(alertDiv);
    
    // 3 秒後自動移除
    setTimeout(() => {
        alertDiv.remove();
    }, 3000);
}

// Made with Bob

// ==================== 回饋功能 ====================

/**
 * 載入所有回饋
 */
async function loadFeedbacks() {
    try {
        const response = await fetch(FEEDBACK_API_URL);
        if (!response.ok) {
            throw new Error('載入回饋失敗');
        }
        const feedbacks = await response.json();
        displayFeedbacks(feedbacks);
    } catch (error) {
        console.error('載入回饋錯誤:', error);
        document.getElementById('feedbackList').innerHTML = `
            <div class="alert alert-warning">
                <i class="bi bi-exclamation-triangle"></i> 載入回饋資料失敗
            </div>
        `;
    }
}

/**
 * 顯示回饋列表
 */
function displayFeedbacks(feedbacks) {
    const container = document.getElementById('feedbackList');
    
    if (feedbacks.length === 0) {
        container.innerHTML = `
            <div class="text-center text-muted py-4">
                <i class="bi bi-inbox" style="font-size: 2rem;"></i>
                <p class="mt-2">目前還沒有回饋資料</p>
            </div>
        `;
        return;
    }
    
    // 只顯示最新的 10 筆回饋
    const recentFeedbacks = feedbacks.slice(-10).reverse();
    
    container.innerHTML = recentFeedbacks.map(feedback => `
        <div class="feedback-item mb-3 p-3 border rounded">
            <div class="d-flex justify-content-between align-items-start mb-2">
                <div>
                    <strong class="text-primary">
                        <i class="bi bi-geo-alt"></i> ${escapeHtml(feedback.station.name)}
                    </strong>
                    <span class="badge bg-secondary ms-2">${escapeHtml(feedback.station.code)}</span>
                </div>
                <div class="text-end">
                    <div class="rating-stars">${generateStars(feedback.rating)}</div>
                    <small class="text-muted">${formatDateTime(feedback.createdAt)}</small>
                </div>
            </div>
            ${feedback.comment ? `
                <p class="mb-2 text-secondary">${escapeHtml(feedback.comment)}</p>
            ` : ''}
            <small class="text-muted">
                <i class="bi bi-person"></i> ${escapeHtml(feedback.passengerName)}
            </small>
        </div>
    `).join('');
}

/**
 * 載入統計資料
 */
async function loadStatistics() {
    try {
        const response = await fetch(`${FEEDBACK_API_URL}/statistics`);
        if (!response.ok) {
            throw new Error('載入統計失敗');
        }
        const statistics = await response.json();
        displayStatistics(statistics);
    } catch (error) {
        console.error('載入統計錯誤:', error);
        document.getElementById('statisticsContainer').innerHTML = `
            <div class="col-12">
                <div class="alert alert-warning">
                    <i class="bi bi-exclamation-triangle"></i> 載入統計資料失敗
                </div>
            </div>
        `;
    }
}

/**
 * 顯示統計資料
 */
function displayStatistics(statistics) {
    const container = document.getElementById('statisticsContainer');
    
    // 篩選有回饋的車站並按評分排序
    const stationsWithFeedback = statistics
        .filter(stat => stat.totalFeedbacks > 0)
        .sort((a, b) => b.averageRating - a.averageRating)
        .slice(0, 6); // 只顯示前 6 名
    
    if (stationsWithFeedback.length === 0) {
        container.innerHTML = `
            <div class="col-12 text-center text-muted py-3">
                <i class="bi bi-bar-chart"></i> 目前還沒有統計資料
            </div>
        `;
        return;
    }
    
    container.innerHTML = stationsWithFeedback.map(stat => `
        <div class="col-md-4 mb-3">
            <div class="stat-card p-3 border rounded bg-light">
                <h6 class="mb-2">
                    <i class="bi bi-geo-alt-fill text-primary"></i> 
                    ${escapeHtml(stat.stationName)}
                </h6>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="rating-stars">${generateStars(Math.round(stat.averageRating))}</div>
                    <span class="badge bg-primary">${stat.averageRating.toFixed(1)} 分</span>
                </div>
                <small class="text-muted">
                    <i class="bi bi-chat-left-text"></i> ${stat.totalFeedbacks} 則回饋
                </small>
            </div>
        </div>
    `).join('');
}

/**
 * 設定回饋表單提交事件
 */
function setupFeedbackFormSubmit() {
    const form = document.getElementById('feedbackForm');
    
    // 載入車站選項
    loadStationOptions();
    
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        const stationId = document.getElementById('feedbackStation').value;
        const rating = parseInt(document.getElementById('feedbackRating').value);
        const comment = document.getElementById('feedbackComment').value.trim();
        const passengerName = document.getElementById('feedbackName').value.trim();
        
        const feedback = {
            station: { id: parseInt(stationId) },
            rating: rating,
            comment: comment || null,
            passengerName: passengerName
        };
        
        try {
            const response = await fetch(FEEDBACK_API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(feedback)
            });
            
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || '提交回饋失敗');
            }
            
            // 成功後重置表單並重新載入資料
            form.reset();
            showSuccess('感謝您的回饋!');
            loadFeedbacks();
            loadStatistics();
        } catch (error) {
            console.error('提交回饋錯誤:', error);
            showError(error.message || '提交回饋失敗,請稍後再試');
        }
    });
}

/**
 * 載入車站選項到下拉選單
 */
async function loadStationOptions() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('載入車站失敗');
        }
        const stations = await response.json();
        
        const select = document.getElementById('feedbackStation');
        select.innerHTML = '<option value="">請選擇車站</option>' + 
            stations.map(station => `
                <option value="${station.id}">
                    ${escapeHtml(station.code)} - ${escapeHtml(station.name)} (${escapeHtml(station.line)})
                </option>
            `).join('');
    } catch (error) {
        console.error('載入車站選項錯誤:', error);
    }
}

/**
 * 生成星星評分顯示
 */
function generateStars(rating) {
    const fullStars = '⭐'.repeat(rating);
    const emptyStars = '☆'.repeat(5 - rating);
    return `<span class="text-warning">${fullStars}${emptyStars}</span>`;
}

// Made with Bob
