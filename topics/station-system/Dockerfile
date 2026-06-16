# 使用 Eclipse Temurin JDK 17 作為建置階段
FROM eclipse-temurin:17-jdk-alpine AS builder

# 設定工作目錄
WORKDIR /app

# 複製 Maven Wrapper 和 pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# 修正 mvnw 執行權限並轉換 line ending (解決 Windows 相容性問題)
RUN chmod +x mvnw && \
    dos2unix mvnw 2>/dev/null || sed -i 's/\r$//' mvnw

# 下載依賴 (利用 Docker 快取層)
RUN ./mvnw dependency:go-offline -B

# 複製原始碼
COPY src src

# 建置應用程式
RUN ./mvnw clean package -DskipTests

# 使用輕量級 JRE 作為執行階段
FROM eclipse-temurin:17-jre-alpine

# 設定工作目錄
WORKDIR /app

# 從建置階段複製 JAR 檔案
COPY --from=builder /app/target/*.jar app.jar

# 暴露應用程式端口
EXPOSE 8080

# 設定 JVM 參數
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# 健康檢查
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/api/stations || exit 1

# 啟動應用程式
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# Made with Bob
