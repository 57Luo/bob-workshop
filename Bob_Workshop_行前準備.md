# IBM Bob Hands-on Workshop 行前準備說明

> **Workshop 時間**: [請填入日期時間]
> **地點**: [請填入地點]
> **預計時長**: 3-4 小時

## 📋 活動議程

### Part 1: IBM Bob 快速介紹（30-45 分鐘）
- Bob 核心概念簡介
- 主要功能展示
- 實際操作演示

### Part 2: Bobathon 實戰挑戰（2-3 小時）
- 🎯 挑戰主題將於活動現場公佈
- 分組協作完成任務
- 實際體驗 Bob 的強大功能
- 成果分享與交流

💡 **Bobathon 說明**：這是一個實戰編程挑戰活動，您將運用 Bob 完成指定任務，體驗 AI 輔助開發的效率與樂趣！

---

## 📋 目錄

1. [參加者需求](#參加者需求)
2. [帳號準備](#帳號準備)
3. [環境安裝](#環境安裝)
4. [網路需求](#網路需求)
5. [預先測試](#預先測試)
6. [Workshop 當天準備](#workshop-當天準備)
7. [疑難排解](#疑難排解)
8. [聯絡資訊](#聯絡資訊)

---

## 🎯 參加者需求

### 技術背景
- **不需要**具備 AI 工具使用經驗
- **建議**有基礎程式開發經驗（任何語言皆可）
- **不需要**熟悉 Node.js、React 或 TypeScript

### 攜帶物品
- ✅ 筆記型電腦（Mac、Windows 或 Linux）
- ✅ 電源線
- ✅ 滑鼠（建議，非必要）
- ✅ 筆記本（記錄重點）

---

## 🔐 帳號準備

### 1. 建立 Bob 帳號（必要）

**請在 Workshop 前完成註冊**

#### 試用帳號註冊

1. 前往 Bob 官網：
   ```
   https://bob.ibm.com
   ```
2. 選擇「取得免費試用」

3. 點選 "使用 Google 開始免費試用"
   or 建議使用公司email 申請

4. 完成註冊流程

5. 確認帳號啟用：
   - 檢查信箱收到確認信
   - 點擊 "Verify Email" 確認帳號

---

## 💻 環境安裝

### 系統需求

| 項目 | 最低需求 | 建議配置 |
|------|---------|---------|
| **作業系統** | macOS / Windows / Linux | 最新版本 |
| **記憶體** | 4 GB RAM | 8 GB RAM 以上 |
| **硬碟空間** | 500 MB | 1 GB 以上 |
| **網路** | 穩定網路連線 | 高速網路 |

### 必要軟體安裝

#### 2. IBM Bob IDE（必要）

**請在 Workshop 前 3 天完成安裝**

##### macOS 安裝步驟：

1. 下載安裝檔：
   ```
   https://bob.ibm.com/download
   ```

2. 開啟下載的 `.pkg` 檔案

3. 依照安裝精靈步驟：
   - 同意授權條款
   - 選擇安裝位置（建議使用預設）
   - 輸入管理員密碼
   - 完成安裝

4. 從應用程式資料夾啟動 Bob

##### Windows 安裝步驟：

1. 下載安裝檔：
   ```
   https://bob.ibm.com/download
   ```

2. 執行下載的 `.exe` 安裝程式

3. 依照安裝精靈步驟：
   - 同意授權條款
   - 選擇安裝目錄（建議使用預設）
   - 選擇開始功能表資料夾
   - 點擊「完成」

4. 從桌面捷徑或開始功能表啟動 Bob

##### Linux 安裝步驟：

1. 下載對應的安裝包（.deb 或 .rpm）

2. 使用套件管理器安裝：
   ```bash
   # Debian/Ubuntu
   sudo dpkg -i bob-installer.deb
   
   # Red Hat/Fedora
   sudo rpm -i bob-installer.rpm
   ```

3. 從應用程式選單啟動 Bob

---

#### 3. Git（必要）

**用於版本控制與專案複製**

##### 下載與安裝：

1. 前往 Git 官網：
   ```
   https://git-scm.com/downloads
   ```

2. 下載對應作業系統的版本

3. 安裝 Git：
   - **macOS**: 開啟 `.pkg` 檔案安裝
   - **Windows**: 執行 `.exe` 安裝程式（建議使用預設設定）
   - **Linux**: 使用套件管理器
     ```bash
     # Debian/Ubuntu
     sudo apt-get install git
     
     # Red Hat/Fedora
     sudo yum install git
     ```

4. 驗證安裝：
   ```bash
   git --version
   ```

**預期輸出**：
```
git version 2.x.x
```
---

## 🌐 網路需求

### 必要網路存取

請確認您的網路環境可以存取以下服務：

#### IBM Bob 服務
- `bob.ibm.com` - Bob 主要服務
- `api.bob.ibm.com` - Bob API 端點
- `auth.bob.ibm.com` - 認證服務

#### 開發工具
- `github.com` - Git 儲存庫
- `docker.io` - Docker Hub
- `registry.npmjs.org` - NPM 套件庫

### 防火牆設定

如果您的公司有防火牆限制，請聯絡 IT 部門開放以下連線：

- **HTTPS (443)**: 所有 Bob 相關服務
- **HTTP (80)**: 部分開發工具
- **Docker Registry**: Docker 映像下載

**參考文件**：
```
https://bob.ibm.com/docs/ide/troubleshooting/ts-firewall-rules
```

### 網路速度建議

- **下載速度**: 至少 10 Mbps
- **上傳速度**: 至少 5 Mbps
- **延遲**: 低於 100ms

---

## ✅ 預先測試

### 測試清單

請在 Workshop 前完成以下測試，確保環境正常運作：

#### 1. Bob IDE 測試

```
□ 成功啟動 Bob 應用程式
□ 可以開啟聊天面板
□ 介面顯示正常（無錯誤訊息）
```

**測試步驟**：
1. 啟動 Bob 應用程式
2. 點擊側邊欄的 Bob 圖示開啟聊天面板
3. 確認可以看到輸入框

#### 2. Bob 登入測試

```
□ 成功使用 試用帳號 登入 Bob
□ 完成瀏覽器認證流程
□ 返回 Bob 後顯示已登入狀態
```

**測試步驟**：
1. 在 Bob 中點擊登入
2. 瀏覽器開啟認證頁面
3. 輸入 IBMid 帳號密碼
4. 完成認證後返回 Bob
5. 確認顯示您的帳號資訊


#### 4. 整合測試

**在 Bob 中執行簡單指令**：

1. 開啟 Bob 聊天面板

2. 輸入以下指令：
   ```
   列出當前目錄的檔案
   ```

3. Bob 應該會：
   - 要求執行權限
   - 顯示目錄內容

4. 如果成功，表示 Bob 可以正常執行指令

---

### 心理準備

- 保持開放心態學習新工具
- 不用擔心犯錯，這是學習過程
- 隨時提問，沒有愚蠢的問題
- 與其他參加者交流經驗

---

## 🔧 疑難排解

### 常見問題與解決方案

#### Q1: Bob 無法啟動

**症狀**: 點擊 Bob 圖示後沒有反應

**解決方案**:
1. 檢查系統需求是否符合
2. 重新啟動電腦
3. 重新安裝 Bob
4. 檢查防毒軟體是否阻擋

#### Q2: 無法登入 IBMid

**症狀**: 認證頁面無法開啟或登入失敗

**解決方案**:
1. 檢查網路連線
2. 清除瀏覽器快取
3. 嘗試使用不同瀏覽器
4. 重設 IBMid 密碼
5. 聯絡 IBM 支援


#### Q3: 網路連線問題

**症狀**: Bob 無法連接服務或下載失敗

**解決方案**:
1. 檢查網路連線
2. 嘗試使用手機熱點
3. 聯絡 IT 部門檢查防火牆
4. 使用 VPN（如果公司有提供）

#### Q6: 記憶體不足

**症狀**: 系統變慢或應用程式當機

**解決方案**:
1. 關閉不必要的應用程式
2. 重新啟動電腦
3. 增加虛擬記憶體
4. 考慮升級硬體

---

## 📞 聯絡資訊

### Workshop 前技術支援

如果您在準備過程中遇到問題，請聯絡：

**技術支援窗口**:
- 姓名: Owen CHEN]
- Email: owenchen@tw.ibm.com
- 電話: 0928107182

### 官方資源

- **Bob 官方文件**: https://bob.ibm.com/docs
- **Bob 下載頁面**: https://bob.ibm.com/download
- **IBMid 支援**: https://www.ibm.com/account
- **Docker 文件**: https://docs.docker.com
- **Git 文件**: https://git-scm.com/doc

---

## 📚 預習資源（選用）

如果您想提前了解 Bob，可以參考以下資源：

### 官方文件
- [Bob 快速入門](https://bob.ibm.com/docs/ide/getting-started/quickstart)
- [Bob 最佳實踐](https://bob.ibm.com/docs/ide/getting-started/best-practices)
- [Bob 安全指南](https://bob.ibm.com/docs/ide/security/bob-security-guidance)

### 影片教學
- Bob 介紹影片（如有提供）
- 使用案例展示

### 社群資源
- IBM Bob 社群論壇
- 技術部落格文章

---

## ✨ Workshop 學習目標

完成本次 Workshop 後，您將能夠：

1. ✅ 理解 IBM Bob 的核心概念與架構
2. ✅ 使用 Bob 的不同模式（Ask、Plan、Code）
3. ✅ 透過 Bobathon 實戰體驗 AI 輔助開發
4. ✅ 掌握 Bob 的審核工作流程
5. ✅ 學會與 Bob 進行有效的對話互動
6. ✅ 了解如何在實際專案中應用 Bob
7. ✅ 建立使用 Bob 的最佳實踐
8. ✅ 體驗團隊協作與 AI 工具的結合

---

## 🎉 期待在 Workshop 見到您！

如有任何問題，請隨時聯絡我們。

祝您準備順利！

---

**文件版本**: 1.0  
**最後更新**: 2026-05-05  
**維護者**: [請填入]