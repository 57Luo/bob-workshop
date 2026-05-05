# IBM Bob Workshop 行前準備網頁

這是一個為 IBM Bob Workshop 設計的行前準備說明網頁，採用 IBM Bob 官網配色風格設計，包含完整版和精簡版兩個分頁。

## 📁 檔案結構

```
bob-workshop/
├── index.html          # 主頁面
├── styles.css          # 樣式表（深色科技風格）
├── script.js           # JavaScript 功能
└── README.md           # 說明文件
```

## ✨ 功能特色

- 🎨 **IBM Bob 官網配色**：採用 IBM Bob 品牌色彩系統
  - 主色：IBM Blue (#0f62fe)
  - 輔色：Purple (#8a3ffc)
  - 強調色：Cyan (#33b1ff)
  - 背景：Carbon Gray (#161616, #262626)
- 📱 **完全響應式**：支援桌面、平板、手機等各種裝置
- 🔄 **雙分頁設計**：完整版和精簡版內容切換
- ♿ **無障礙支援**：符合 ARIA 標準，支援鍵盤導航
- 💾 **狀態記憶**：自動記住使用者選擇的分頁
- 🖨️ **列印友善**：優化的列印樣式
- ⚡ **純靜態網頁**：無需後端，適合 GitHub Pages 部署

## 🚀 部署到 GitHub Pages

### 方法 1：透過 GitHub 網頁介面

1. 在 GitHub 建立新的 repository
2. 上傳所有檔案（index.html, styles.css, script.js）
3. 進入 repository 的 Settings
4. 找到 "Pages" 選項
5. 在 "Source" 下選擇 "main" 分支
6. 點擊 "Save"
7. 等待幾分鐘後，網站就會發布在 `https://[你的使用者名稱].github.io/[repository名稱]/`

### 方法 2：透過 Git 指令

```bash
# 1. 初始化 Git repository
git init

# 2. 加入所有檔案
git add .

# 3. 提交變更
git commit -m "Initial commit: IBM Bob Workshop 行前準備網頁"

# 4. 連結到 GitHub repository
git remote add origin https://github.com/[你的使用者名稱]/[repository名稱].git

# 5. 推送到 GitHub
git branch -M main
git push -u origin main

# 6. 在 GitHub 設定中啟用 GitHub Pages
```

## 🎨 自訂設定

### 修改顏色主題

在 `styles.css` 的 `:root` 區塊中修改顏色變數：

```css
:root {
    /* IBM Bob 官網配色 */
    --primary-color: #0f62fe;      /* IBM Blue */
    --primary-hover: #0353e9;      /* IBM Blue Hover */
    --secondary-color: #8a3ffc;    /* Purple */
    --accent-color: #33b1ff;       /* Cyan */
    --background: #161616;         /* Carbon Gray 100 */
    --surface: #262626;            /* Carbon Gray 90 */
    /* ... 其他顏色 */
}
```

### 修改內容

直接編輯 `index.html` 中的內容：
- 完整版內容在 `<div id="full" class="tab-content active">` 區塊
- 精簡版內容在 `<div id="quick" class="tab-content">` 區塊

### 修改 Workshop 資訊

在 `index.html` 中搜尋並替換：
- `[請填入日期時間]` → 實際的 Workshop 日期時間
- `[請填入地點]` → 實際的 Workshop 地點

## 🌐 瀏覽器支援

- ✅ Chrome/Edge (最新版本)
- ✅ Firefox (最新版本)
- ✅ Safari (最新版本)
- ✅ 行動裝置瀏覽器

## 📱 響應式斷點

- **桌面**: > 768px
- **平板**: 481px - 768px
- **手機**: ≤ 480px

## ⌨️ 鍵盤快捷鍵

在分頁按鈕上：
- `←` / `→`: 切換分頁
- `Home`: 跳到第一個分頁
- `End`: 跳到最後一個分頁
- `Tab`: 鍵盤導航

## 🔧 技術細節

### HTML
- 語義化標籤
- ARIA 無障礙屬性
- Meta 標籤優化

### CSS
- CSS 變數（Custom Properties）
- Flexbox 佈局
- CSS Grid（表格）
- 動畫效果
- 媒體查詢（響應式）

### JavaScript
- 原生 JavaScript（無需框架）
- LocalStorage 狀態管理
- 事件委派
- 鍵盤導航支援

## 📄 授權

此專案為 IBM Bob Workshop 內部使用。

## 👤 聯絡資訊

**技術支援**：
- Owen Chen
- Email: owenchen@tw.ibm.com
- 電話: 0928-107-182

## 📝 更新日誌

### v1.0.0 (2026-05-05)
- ✨ 初始版本發布
- 🎨 深色科技風格設計
- 📱 完整響應式支援
- ♿ 無障礙功能實作
- 🔄 雙分頁內容切換

---

**文件版本**: 1.0  
**最後更新**: 2026-05-05