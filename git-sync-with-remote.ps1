# Git 同步腳本 - 處理遠端和本地都有新進度的情況
# 適用於需要合併遠端和本地變更的場景

Write-Host "🔄 Git 同步腳本 - 合併遠端與本地變更" -ForegroundColor Green
Write-Host "=" * 60 -ForegroundColor Gray

# 詢問 GitHub Repository URL
Write-Host "`n📝 請提供您的 GitHub Repository 資訊:" -ForegroundColor Cyan
$repoUrl = Read-Host "GitHub Repository URL (例如: https://github.com/username/repo-name.git)"

if ([string]::IsNullOrWhiteSpace($repoUrl)) {
    Write-Host "❌ 錯誤: Repository URL 不能為空" -ForegroundColor Red
    exit 1
}

# 檢查是否已經是 Git 儲存庫
if (-not (Test-Path .git)) {
    Write-Host "`n📦 初始化 Git 儲存庫..." -ForegroundColor Cyan
    git init
    git branch -M main
}

# 檢查並設定 remote
$existingRemote = git remote -v 2>$null
if ($existingRemote) {
    Write-Host "`n📋 現有的 remote 設定:" -ForegroundColor Cyan
    git remote -v
    
    Write-Host "`n🔄 更新 remote URL..." -ForegroundColor Cyan
    git remote set-url origin $repoUrl
} else {
    Write-Host "`n➕ 新增 remote..." -ForegroundColor Cyan
    git remote add origin $repoUrl
}

Write-Host "✅ Remote 設定完成" -ForegroundColor Green

# 檢查本地是否有未提交的變更
$status = git status --porcelain
if ($status) {
    Write-Host "`n📝 發現未提交的變更,正在提交..." -ForegroundColor Cyan
    git add .
    
    $commitMsg = Read-Host "`n請輸入 commit 訊息 (直接按 Enter 使用預設訊息)"
    if ([string]::IsNullOrWhiteSpace($commitMsg)) {
        $commitMsg = "feat: 新增乘客回饋功能 - $(Get-Date -Format 'yyyy-MM-dd HH:mm')"
    }
    
    git commit -m $commitMsg
    Write-Host "✅ 本地變更已提交" -ForegroundColor Green
}

# 顯示合併策略選項
Write-Host "`n🔀 選擇合併策略:" -ForegroundColor Yellow
Write-Host "1. Merge (建立合併提交) - 保留完整歷史" -ForegroundColor White
Write-Host "2. Rebase (重新套用提交) - 保持線性歷史" -ForegroundColor White
Write-Host "3. Pull with --allow-unrelated-histories (首次同步)" -ForegroundColor White
$strategy = Read-Host "`n請選擇 (1/2/3,預設為 1)"

if ([string]::IsNullOrWhiteSpace($strategy)) {
    $strategy = "1"
}

Write-Host "`n⬇️  拉取遠端資料..." -ForegroundColor Cyan

switch ($strategy) {
    "1" {
        Write-Host "使用 Merge 策略..." -ForegroundColor Cyan
        git pull origin main --no-rebase
    }
    "2" {
        Write-Host "使用 Rebase 策略..." -ForegroundColor Cyan
        git pull origin main --rebase
    }
    "3" {
        Write-Host "使用 --allow-unrelated-histories..." -ForegroundColor Cyan
        git pull origin main --allow-unrelated-histories --no-rebase
    }
}

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ 成功拉取並合併遠端資料" -ForegroundColor Green
    
    # 顯示合併後的狀態
    Write-Host "`n📊 目前狀態:" -ForegroundColor Cyan
    git status
    
    # 詢問是否要推送
    Write-Host "`n" -NoNewline
    $pushNow = Read-Host "是否要立即推送到 GitHub? (Y/n)"
    if ($pushNow -ne "n") {
        Write-Host "`n⬆️  推送到 GitHub..." -ForegroundColor Cyan
        git push origin main
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "`n✅ 成功推送到 GitHub!" -ForegroundColor Green
            Write-Host "🎉 同步完成!" -ForegroundColor Green
        } else {
            Write-Host "`n⚠️  推送失敗,請檢查身份驗證" -ForegroundColor Yellow
        }
    }
} else {
    Write-Host "`n⚠️  合併時發生衝突!" -ForegroundColor Yellow
    Write-Host "`n📋 衝突的檔案:" -ForegroundColor Cyan
    git status --short | Where-Object { $_ -match "^UU" }
    
    Write-Host "`n💡 解決衝突的步驟:" -ForegroundColor Yellow
    Write-Host "1. 開啟衝突的檔案,手動解決衝突標記 (<<<<<<<, =======, >>>>>>>)" -ForegroundColor White
    Write-Host "2. 解決後執行: git add <檔案名稱>" -ForegroundColor Cyan
    Write-Host "3. 完成所有衝突後執行: git commit" -ForegroundColor Cyan
    Write-Host "4. 最後執行: git push origin main" -ForegroundColor Cyan
    
    Write-Host "`n或者,如果要放棄合併:" -ForegroundColor Yellow
    Write-Host "   git merge --abort" -ForegroundColor Cyan
}

Write-Host "`n" + ("=" * 60) -ForegroundColor Gray
Write-Host "📖 更多資訊請參考: GIT_SETUP_GUIDE.md" -ForegroundColor Cyan

# Made with Bob