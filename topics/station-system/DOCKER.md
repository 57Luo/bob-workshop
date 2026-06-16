# 🐳 Docker 部署指南

本文件說明如何使用 Docker 和 Docker Compose 部署捷運車站管理系統。

---

## 📋 前置需求

- Docker 20.10 或以上
- Docker Compose 2.0 或以上

### 檢查 Docker 版本

```bash
docker --version
docker-compose --version
```

---

## 🚀 快速開始

### 方法一：使用 Docker Compose（推薦）

```bash
# 1. 進入專案目錄
cd topics/station-system

# 2. 建置並啟動服務
docker-compose up -d

# 3. 查看日誌
docker-compose logs -f

# 4. 停止服務
docker-compose down
```

### 方法二：使用 Docker 指令

```bash
# 1. 建置 Docker Image
docker build -t metro-station-system:latest .

# 2. 執行容器
docker run -d \
  --name metro-station-system \
  -p 8080:8080 \
  metro-station-system:latest

# 3. 查看日誌
docker logs -f metro-station-system

# 4. 停止容器
docker stop metro-station-system
docker rm metro-station-system
```

---

## 🌐 存取應用程式

啟動成功後，可以透過以下網址存取：

- **Web UI**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
- **API 端點**: http://localhost:8080/api/stations

### H2 資料庫連線資訊

- JDBC URL: `jdbc:h2:mem:metro`
- Username: `sa`
- Password: (留空)

---

## 🔧 常用指令

### Docker Compose 指令

```bash
# 建置並啟動（背景執行）
docker-compose up -d

# 建置並啟動（前景執行，可看到即時日誌）
docker-compose up

# 重新建置並啟動
docker-compose up -d --build

# 停止服務
docker-compose stop

# 停止並移除容器
docker-compose down

# 停止並移除容器、網路、映像檔
docker-compose down --rmi all

# 查看服務狀態
docker-compose ps

# 查看日誌
docker-compose logs

# 即時追蹤日誌
docker-compose logs -f

# 進入容器
docker-compose exec station-system sh
```

### Docker 指令

```bash
# 查看執行中的容器
docker ps

# 查看所有容器（包含停止的）
docker ps -a

# 查看映像檔
docker images

# 刪除容器
docker rm metro-station-system

# 刪除映像檔
docker rmi metro-station-system:latest

# 查看容器日誌
docker logs metro-station-system

# 即時追蹤日誌
docker logs -f metro-station-system

# 進入容器
docker exec -it metro-station-system sh

# 查看容器資源使用情況
docker stats metro-station-system
```

---

## 🏗️ Docker 架構說明

### Dockerfile 特點

- **多階段建置**：使用 JDK 建置，JRE 執行，減少映像檔大小
- **Alpine Linux**：使用輕量級 Alpine 基礎映像
- **快取優化**：先複製依賴檔案，利用 Docker 快取層加速建置
- **健康檢查**：自動檢查應用程式健康狀態
- **JVM 調校**：預設記憶體設定 `-Xmx512m -Xms256m`

### docker-compose.yml 特點

- **自動重啟**：使用 `restart: unless-stopped` 確保服務穩定
- **健康檢查**：定期檢查服務健康狀態
- **網路隔離**：使用自訂網路 `station-network`
- **環境變數**：支援透過環境變數配置應用程式

---

## ⚙️ 環境變數配置

可以透過修改 `docker-compose.yml` 或建立 `.env` 檔案來配置環境變數：

```yaml
environment:
  # Spring Boot Profile
  - SPRING_PROFILES_ACTIVE=docker
  
  # JVM 記憶體設定
  - JAVA_OPTS=-Xmx512m -Xms256m
  
  # 應用程式端口（容器內部）
  - SERVER_PORT=8080
```

### 建立 .env 檔案（選用）

```bash
# .env
SPRING_PROFILES_ACTIVE=docker
JAVA_OPTS=-Xmx512m -Xms256m
```

---

## 🔍 健康檢查

Docker 容器包含健康檢查機制：

- **檢查間隔**：30 秒
- **超時時間**：3 秒
- **重試次數**：3 次
- **啟動等待**：40 秒

查看健康狀態：

```bash
# 使用 Docker Compose
docker-compose ps

# 使用 Docker
docker ps
```

---

## 🐛 疑難排解

### 問題 1：Port 8080 已被佔用

**解決方法**：修改 `docker-compose.yml` 中的端口映射

```yaml
ports:
  - "8081:8080"  # 改用 8081
```

### 問題 2：容器無法啟動

**檢查步驟**：

```bash
# 1. 查看容器狀態
docker-compose ps

# 2. 查看詳細日誌
docker-compose logs

# 3. 檢查容器健康狀態
docker inspect metro-station-system | grep Health -A 10
```

### 問題 3：建置失敗

**解決方法**：

```bash
# 1. 清理舊的建置快取
docker-compose down --rmi all

# 2. 重新建置
docker-compose build --no-cache

# 3. 啟動服務
docker-compose up -d
```

### 問題 4：記憶體不足

**解決方法**：調整 JVM 記憶體設定

```yaml
environment:
  - JAVA_OPTS=-Xmx256m -Xms128m  # 降低記憶體使用
```

---

## 📊 效能調校

### JVM 記憶體設定建議

| 環境 | 建議設定 |
|------|---------|
| 開發環境 | `-Xmx256m -Xms128m` |
| 測試環境 | `-Xmx512m -Xms256m` |
| 生產環境 | `-Xmx1g -Xms512m` |

### Docker 資源限制

可以在 `docker-compose.yml` 中設定資源限制：

```yaml
services:
  station-system:
    # ... 其他設定
    deploy:
      resources:
        limits:
          cpus: '1.0'
          memory: 1G
        reservations:
          cpus: '0.5'
          memory: 512M
```

---

## 🔐 安全性建議

1. **不要在映像檔中包含敏感資訊**
2. **使用環境變數管理配置**
3. **定期更新基礎映像**
4. **使用非 root 使用者執行應用程式**（進階）
5. **掃描映像檔漏洞**

```bash
# 使用 Docker Scout 掃描漏洞
docker scout cves metro-station-system:latest
```

---

## 📚 相關資源

- [Docker 官方文件](https://docs.docker.com/)
- [Docker Compose 文件](https://docs.docker.com/compose/)
- [Spring Boot Docker 指南](https://spring.io/guides/topicals/spring-boot-docker/)

---

## 💡 提示

- 使用 Docker Compose 可以簡化多容器應用的管理
- 開發時建議使用 `-f` 參數即時查看日誌
- 生產環境建議使用 `-d` 參數在背景執行
- 定期清理未使用的映像檔和容器以節省空間

---

**準備好使用 Docker 部署了嗎？** 🚀