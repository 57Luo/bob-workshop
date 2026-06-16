// API 基礎 URL
const API_URL = '/api/stations';
const FEEDBACK_API_URL = '/api/feedbacks';

// 頁面載入時執行
document.addEventListener('DOMContentLoaded', function() {
    loadStations();
    setupFormSubmit();
    setupFeedbackForm();
    setupFeedbackFilter();
    setupCharCounter();
    loadFeedbacks();
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
        loadStationsToSelect(stations);
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


/**
 * 載入車站到下拉選單
 */
function loadStationsToSelect(stations) {
    const selects = ['feedbackStation', 'filterStation'];
    
    selects.forEach(selectId => {
        const select = document.getElementById(selectId);
        if (!select) return;
        
        const defaultOption = selectId === 'filterStation' ? '所有車站' : '請選擇車站';
        select.innerHTML = `<option value="">${defaultOption}</option>`;
        
        stations.forEach(station => {
            const option = document.createElement('option');
            option.value = station.id;
            option.textContent = `${station.code} - ${station.name}`;
            select.appendChild(option);
        });
    });
}

/**
 * 設定回饋表單提交事件
 */
function setupFeedbackForm() {
    const form = document.getElementById('feedbackForm');
    if (!form) return;
    
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        const feedbackData = {
            station: {
                id: parseInt(document.getElementById('feedbackStation').value)
            },
            passengerName: document.getElementById('passengerName').value.trim() || null,
            rating: parseInt(document.getElementById('rating').value),
            type: document.getElementById('feedbackType').value,
            content: document.getElementById('feedbackContent').value.trim()
        };
        
        try {
            const response = await fetch(FEEDBACK_API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(feedbackData)
            });
            
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || '提交回饋失敗');
            }
            
            // 成功後重置表單並重新載入列表
            form.reset();
            document.getElementById('charCount').textContent = '0';
            showSuccess('回饋提交成功！感謝您的寶貴意見');
            loadFeedbacks();
        } catch (error) {
            console.error('提交回饋錯誤:', error);
            showError(error.message || '提交回饋失敗，請檢查輸入資料');
        }
    });
}

/**
 * 設定回饋篩選器
 */
function setupFeedbackFilter() {
    const filterSelect = document.getElementById('filterStation');
    if (!filterSelect) return;
    
    filterSelect.addEventListener('change', function() {
        const stationId = this.value;
        loadFeedbacks(stationId || null);
    });
}

/**
 * 設定字元計數器
 */
function setupCharCounter() {
    const textarea = document.getElementById('feedbackContent');
    if (!textarea) return;
    
    textarea.addEventListener('input', function() {
        document.getElementById('charCount').textContent = this.value.length;
    });
}

/**
 * 載入回饋列表
 */
async function loadFeedbacks(stationId = null) {
    try {
        const url = stationId 
            ? `${FEEDBACK_API_URL}/station/${stationId}`
            : FEEDBACK_API_URL;
        
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('載入回饋失敗');
        }
        
        const feedbacks = await response.json();
        displayFeedbacks(feedbacks);
    } catch (error) {
        console.error('載入回饋錯誤:', error);
        const container = document.getElementById('feedbackList');
        if (container) {
            container.innerHTML = '<p class="text-danger">載入回饋資料失敗，請重新整理頁面</p>';
        }
    }
}

/**
 * 顯示回饋列表
 */
function displayFeedbacks(feedbacks) {
    const container = document.getElementById('feedbackList');
    if (!container) return;
    
    if (feedbacks.length === 0) {
        container.innerHTML = `
            <div class="text-center text-muted py-4">
                <i class="bi bi-inbox" style="font-size: 3rem;"></i>
                <p class="mt-2">目前沒有回饋資料</p>
            </div>
        `;
        return;
    }
    
    container.innerHTML = feedbacks.map(feedback => `
        <div class="card mb-3">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-start mb-2">
                    <div>
                        <h6 class="card-title mb-1">
                            <strong>${escapeHtml(feedback.station.code)} - ${escapeHtml(feedback.station.name)}</strong>
                            <span class="badge ${getTypeBadgeClass(feedback.type)} ms-2">
                                ${getTypeDisplayName(feedback.type)}
                            </span>
                        </h6>
                    </div>
                    <div class="text-end">
                        <div class="mb-1">${renderStars(feedback.rating)}</div>
                    </div>
                </div>
                <p class="card-text">${escapeHtml(feedback.content)}</p>
                <div class="d-flex justify-content-between align-items-center">
                    <small class="text-muted">
                        <i class="bi bi-person"></i> ${escapeHtml(feedback.passengerName || '匿名')} · 
                        <i class="bi bi-clock"></i> ${formatDateTime(feedback.createdAt)}
                    </small>
                    <button class="btn btn-sm btn-outline-danger" 
                            onclick="deleteFeedback(${feedback.id})">
                        <i class="bi bi-trash"></i> 刪除
                    </button>
                </div>
            </div>
        </div>
    `).join('');
}

/**
 * 刪除回饋
 */
async function deleteFeedback(id) {
    if (!confirm('確定要刪除此回饋嗎？')) {
        return;
    }
    
    try {
        const response = await fetch(`${FEEDBACK_API_URL}/${id}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || '刪除回饋失敗');
        }
        
        showSuccess('回饋刪除成功！');
        loadFeedbacks();
    } catch (error) {
        console.error('刪除回饋錯誤:', error);
        showError(error.message || '刪除回饋失敗');
    }
}

/**
 * 渲染星星評分
 */
function renderStars(rating) {
    const fullStars = '⭐'.repeat(rating);
    const emptyStars = '☆'.repeat(5 - rating);
    return `<span style="color: #ffc107;">${fullStars}${emptyStars}</span>`;
}

/**
 * 取得回饋類型顯示名稱
 */
function getTypeDisplayName(type) {
    const typeMap = {
        'FACILITY': '🏢 設施',
        'SERVICE': '👥 服務',
        'CLEANLINESS': '🧹 清潔',
        'SAFETY': '🛡️ 安全',
        'ACCESSIBILITY': '♿ 無障礙',
        'OTHER': '📝 其他'
    };
    return typeMap[type] || type;
}

/**
 * 取得回饋類型徽章樣式
 */
function getTypeBadgeClass(type) {
    const classMap = {
        'FACILITY': 'bg-primary',
        'SERVICE': 'bg-success',
        'CLEANLINESS': 'bg-info',
        'SAFETY': 'bg-danger',
        'ACCESSIBILITY': 'bg-warning text-dark',
        'OTHER': 'bg-secondary'
    };
    return classMap[type] || 'bg-secondary';
}
