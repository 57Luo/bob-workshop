# 🚀 快速啟動指南 - 乘客回饋系統

> 5 分鐘內啟動並體驗完整的乘客回饋功能!

---

## ⚡ 快速啟動

### 步驟 1: 啟動應用程式

**Windows 使用者:**
```powershell
cd bob-workshop-main/topics/station-system
.\mvnw.cmd spring-boot:run
```

**Mac/Linux 使用者:**
```bash
cd bob-workshop-main/topics/station-system
./mvnw spring-boot:run
```

### 步驟 2: 開啟瀏覽器

訪問: **http://localhost:8080**

---

## 🎯 功能體驗

### 1️⃣ 查看現有回饋

頁面載入後,您會看到:
- ✅ **車站列表**: 顯示所有捷運車站
- ✅ **車站評分統計**: 前 6 名高評分車站
- ✅ **最新回饋**: 最近 10 筆乘客回饋

### 2️⃣ 提交新回饋

在「提交您的回饋」區塊:
1. 選擇車站 (例如: R16 - 左營(高鐵))
2. 輸入您的姓名
3. 選擇評分 (1-5 星)
4. 輸入評論 (選填)
5. 點擊「提交回饋」

### 3️⃣ 查看統計更新

提交後,統計資料會自動更新:
- 平均評分重新計算
- 回饋數量增加
- 新回饋出現在列表頂部

---

## 🔍 API 測試

### 使用 curl 測試

```bash
# 1. 取得所有回饋
curl http://localhost:8080/api/feedbacks

# 2. 取得統計資料
curl http://localhost:8080/api/feedbacks/statistics

# 3. 取得特定車站回饋 (車站 ID: 16)
curl http://localhost:8080/api/feedbacks/station/16

# 4. 新增回饋
curl -X POST http://localhost:8080/api/feedbacks \
  -H "Content-Type: application/json" \
  -d '{
    "station": {"id": 16},
    "rating": 5,
    "comment": "非常棒的車站!",
    "passengerName": "測試使用者"
  }'
```

### 使用瀏覽器測試

直接在瀏覽器訪問:
- http://localhost:8080/api/feedbacks
- http://localhost:8080/api/feedbacks/statistics
- http://localhost:8080/api/stations

---

## 💾 資料庫查看

### H2 Console

1. 訪問: **http://localhost:8080/h2-console**
2. 連線資訊:
   - JDBC URL: `jdbc:h2:mem:metro`
   - Username: `sa`
   - Password: (留空)
3. 點擊「Connect」

### 查詢範例

```sql
-- 查看所有回饋
SELECT * FROM feedback;

-- 查看車站及其回饋數量
SELECT s.name, COUNT(f.id) as feedback_count
FROM station s
LEFT JOIN feedback f ON s.id = f.station_id
GROUP BY s.name
ORDER BY feedback_count DESC;

-- 查看平均評分最高的車站
SELECT s.name, AVG(f.rating) as avg_rating, COUNT(f.id) as total
FROM station s
INNER JOIN feedback f ON s.id = f.station_id
GROUP BY s.name
HAVING COUNT(f.id) > 0
ORDER BY avg_rating DESC;
```

---

## 📊 測試資料

系統已預載 16 筆測試回饋,涵蓋以下車站:
- 🚄 左營(高鐵): 3 則 (平均 4.7 星)
- 🏢 中央車站: 4 則 (平均 4.0 星)
- 🛍️ 三多商圈: 2 則 (平均 4.5 星)
- 🎵 巨蛋: 2 則 (平均 4.5 星)
- 🎨 文化中心: 2 則 (平均 4.5 星)
- ✈️ 小港: 2 則 (平均 3.5 星)

---

## 🎨 功能展示

### 回饋提交流程
```
選擇車站 → 輸入資料 → 提交 → 即時更新
```

### 統計顯示
- 📊 車站評分卡片
- ⭐ 星星評分視覺化
- 📈 回饋數量統計

### 回饋列表
- 💬 最新回饋優先
- 🏷️ 車站標籤顯示
- 👤 乘客姓名
- 📅 時間戳記

---

## 🛠️ 常見問題

### Q: 如何重置資料?
**A**: 重啟應用程式,H2 會自動重新載入初始資料。

### Q: 可以修改測試資料嗎?
**A**: 可以!編輯 `src/main/resources/data.sql` 檔案。

### Q: 如何停止應用程式?
**A**: 在終端機按 `Ctrl + C`。

### Q: 前端無法連接後端?
**A**: 確認:
1. 後端已成功啟動 (看到 "Started StationApplication")
2. 訪問 http://localhost:8080/api/stations 測試 API
3. 檢查瀏覽器 Console 是否有錯誤

---

## 📱 響應式設計

系統支援多種裝置:
- 💻 桌面電腦 (1920x1080+)
- 💻 筆記型電腦 (1366x768+)
- 📱 平板電腦 (768x1024)
- 📱 手機 (375x667)

---

## 🎯 下一步

1. **探索 API**: 使用 Postman 或 curl 測試所有端點
2. **查看程式碼**: 研究後端與前端實作
3. **自訂功能**: 嘗試新增更多功能
4. **部署上線**: 將應用程式部署到雲端

---

## 📚 詳細文件

- 📖 [完整實作說明](./FEEDBACK_IMPLEMENTATION.md)
- 📖 [專案 README](./README.md)
- 📖 [API 文件](./FEEDBACK_IMPLEMENTATION.md#api-端點)

---

## 🎉 享受使用!

現在您已經成功啟動了完整的乘客回饋系統!

有任何問題,請參考詳細文件或聯絡開發團隊。

---

**Made with ❤️ by Bob AI Assistant**