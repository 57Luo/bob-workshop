# Git 初始化腳本
# 自動設定 Git 儲存庫並準備推送到 GitHub

Write-Host "🚀 開始初始化 Git 儲存庫..." -ForegroundColor Green

# 檢查是否已經是 Git 儲存庫
if (Test-Path .git) {
    Write-Host "⚠️  已經是 Git 儲存庫" -ForegroundColor Yellow
    $continue = Read-Host "是否要重新初始化? (y/N)"
    if ($continue -ne "y") {
        Write-Host "❌ 取消操作" -ForegroundColor Red
        exit
    }
    Remove-Item -Recurse -Force .git
}

# 初始化 Git
Write-Host "`n📦 初始化 Git 儲存庫..." -ForegroundColor Cyan
git init

# 設定主分支名稱
Write-Host "🌿 設定主分支為 main..." -ForegroundColor Cyan
git branch -M main

# 加入所有檔案
Write-Host "`n📝 加入所有檔案..." -ForegroundColor Cyan
git add .

# 顯示將要提交的檔案
Write-Host "`n📋 將要提交的檔案:" -ForegroundColor Cyan
git status --short

# 提交
Write-Host "`n💾 提交到本地儲存庫..." -ForegroundColor Cyan
git commit -m "feat: 新增乘客回饋功能

- 建立 Feedback 實體與 Repository
- 實作 FeedbackService 業務邏輯  
- 建立 FeedbackController REST API (8個端點)
- 整合前端回饋表單與統計顯示
- 新增 16 筆測試資料
- 完整的文件說明 (README, QUICKSTART, GIT_SETUP_GUIDE)
- 修正 data.sql 的 station_id 參照錯誤

技術棧:
- Spring Boot 3.2.0 + Java 17
- H2 Database
- Bootstrap 5
- Vanilla JavaScript

功能特色:
- 1-5 星評分系統
- 文字評論功能
- 即時統計分析
- 響應式設計
- RESTful API

Made with ❤️ by Bob AI Assistant"

Write-Host "`n✅ Git 儲存庫初始化完成!" -ForegroundColor Green
Write-Host "`n📌 下一步:" -ForegroundColor Yellow
Write-Host "1. 在 GitHub 建立新儲存庫 (https://github.com/new)" -ForegroundColor White
Write-Host "   - Repository name: metro-station-feedback-system" -ForegroundColor White
Write-Host "   - 不要勾選 'Initialize this repository with a README'" -ForegroundColor White
Write-Host "`n2. 執行以下指令連接到 GitHub:" -ForegroundColor White
Write-Host "   git remote add origin https://github.com/您的使用者名稱/metro-station-feedback-system.git" -ForegroundColor Cyan
Write-Host "   git push -u origin main" -ForegroundColor Cyan
Write-Host "`n📖 詳細說明請參考: GIT_SETUP_GUIDE.md" -ForegroundColor Yellow

# Made with Bob