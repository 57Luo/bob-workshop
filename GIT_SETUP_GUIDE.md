# 🔗 GitHub 連線設定指南

> 將乘客回饋系統上傳到 GitHub 的完整步驟

---

## 📋 前置準備

### 1. 確認已安裝 Git
```powershell
git --version
```
如果沒有安裝,請到 https://git-scm.com/download/win 下載安裝。

### 2. 設定 Git 使用者資訊 (首次使用)
```powershell
git config --global user.name "您的名字"
git config --global user.email "您的Email"
```

---

## 🚀 步驟一: 初始化 Git 儲存庫

在專案目錄執行:

```powershell
# 進入專案目錄
cd bob-workshop-main/topics/station-system

# 初始化 Git 儲存庫
git init

# 查看狀態
git status
```

---

## 📝 步驟二: 建立 .gitignore 檔案

建立 `.gitignore` 檔案以排除不需要的檔案:

```gitignore
# Maven
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

# IDE
.idea/
*.iml
*.iws
*.ipr
.vscode/
.settings/
.project
.classpath

# OS
.DS_Store
Thumbs.db

# Logs
*.log

# Temporary files
*.tmp
*.bak
*.swp
*~

# H2 Database files
*.db
*.trace.db
```

---

## 📦 步驟三: 提交程式碼到本地儲存庫

```powershell
# 加入所有檔案
git add .

# 查看將要提交的檔案
git status

# 提交到本地儲存庫
git commit -m "feat: 新增乘客回饋功能

- 建立 Feedback 實體與 Repository
- 實作 FeedbackService 業務邏輯
- 建立 FeedbackController REST API
- 整合前端回饋表單與統計顯示
- 新增 16 筆測試資料
- 完整的文件說明"
```

---

## 🌐 步驟四: 在 GitHub 建立新儲存庫

### 方法 A: 使用 GitHub 網站

1. 登入 GitHub (https://github.com)
2. 點擊右上角 `+` → `New repository`
3. 填寫資訊:
   - **Repository name**: `metro-station-feedback-system`
   - **Description**: `捷運車站管理系統 - 乘客回饋功能`
   - **Public** 或 **Private**: 依需求選擇
   - **不要勾選** "Initialize this repository with a README"
4. 點擊 `Create repository`

### 方法 B: 使用 GitHub CLI (如果已安裝)

```powershell
# 建立新的 GitHub 儲存庫
gh repo create metro-station-feedback-system --public --source=. --remote=origin

# 或建立私有儲存庫
gh repo create metro-station-feedback-system --private --source=. --remote=origin
```

---

## 🔗 步驟五: 連接到 GitHub 遠端儲存庫

### 如果使用方法 A (手動建立):

```powershell
# 新增遠端儲存庫 (替換成您的 GitHub 使用者名稱)
git remote add origin https://github.com/您的使用者名稱/metro-station-feedback-system.git

# 驗證遠端連線
git remote -v

# 設定主分支名稱
git branch -M main

# 推送到 GitHub
git push -u origin main
```

### 如果使用方法 B (GitHub CLI):

```powershell
# 推送到 GitHub (已自動設定)
git push -u origin main
```

---

## 🔐 步驟六: 處理身份驗證

### 選項 A: 使用 Personal Access Token (推薦)

1. 到 GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. 點擊 `Generate new token (classic)`
3. 設定:
   - **Note**: `Metro Station System`
   - **Expiration**: 選擇期限
   - **Scopes**: 勾選 `repo` (完整控制)
4. 點擊 `Generate token`
5. **複製 token** (只會顯示一次!)
6. 推送時使用 token 作為密碼

### 選項 B: 使用 SSH Key

```powershell
# 生成 SSH Key
ssh-keygen -t ed25519 -C "您的Email"

# 複製公鑰
Get-Content ~/.ssh/id_ed25519.pub | clip

# 到 GitHub Settings → SSH and GPG keys → New SSH key
# 貼上公鑰並儲存

# 修改遠端 URL 為 SSH
git remote set-url origin git@github.com:您的使用者名稱/metro-station-feedback-system.git

# 推送
git push -u origin main
```

---

## ✅ 步驟七: 驗證上傳成功

```powershell
# 查看遠端分支
git branch -r

# 查看提交歷史
git log --oneline

# 訪問 GitHub 儲存庫
# https://github.com/您的使用者名稱/metro-station-feedback-system
```

---

## 📚 後續操作

### 更新程式碼到 GitHub

```powershell
# 查看變更
git status

# 加入變更的檔案
git add .

# 提交變更
git commit -m "描述您的變更"

# 推送到 GitHub
git push
```

### 建立分支進行開發

```powershell
# 建立並切換到新分支
git checkout -b feature/new-feature

# 開發完成後推送分支
git push -u origin feature/new-feature

# 在 GitHub 上建立 Pull Request
```

### 拉取最新程式碼

```powershell
# 拉取並合併
git pull

# 或分開執行
git fetch
git merge origin/main
```

---

## 🎯 建議的 Commit Message 格式

```
類型: 簡短描述

詳細說明 (選填)

相關 Issue (選填)
```

**類型**:
- `feat`: 新功能
- `fix`: 修復 Bug
- `docs`: 文件更新
- `style`: 程式碼格式調整
- `refactor`: 重構
- `test`: 測試相關
- `chore`: 建置或輔助工具

**範例**:
```
feat: 新增回饋刪除功能

- 實作 DELETE /api/feedbacks/{id} 端點
- 新增前端刪除按鈕
- 更新文件

Closes #123
```

---

## 🔧 常見問題

### Q1: 推送時要求輸入密碼?
**A**: 使用 Personal Access Token 而非 GitHub 密碼。

### Q2: 推送被拒絕 (rejected)?
**A**: 先拉取遠端變更:
```powershell
git pull --rebase origin main
git push
```

### Q3: 如何撤銷最後一次 commit?
**A**: 
```powershell
# 保留變更
git reset --soft HEAD~1

# 不保留變更
git reset --hard HEAD~1
```

### Q4: 如何查看變更內容?
**A**:
```powershell
# 查看未暫存的變更
git diff

# 查看已暫存的變更
git diff --staged
```

---

## 📖 相關資源

- [Git 官方文件](https://git-scm.com/doc)
- [GitHub 文件](https://docs.github.com/)
- [GitHub CLI](https://cli.github.com/)
- [Git 教學](https://learngitbranching.js.org/)

---

## 🎉 完成!

現在您的乘客回饋系統已經連接到 GitHub,可以:
- ✅ 版本控制
- ✅ 協作開發
- ✅ 備份程式碼
- ✅ 展示作品

**祝您開發順利!** 🚀

---

**Made with ❤️ by Bob AI Assistant**