-- 捷運系統車站初始資料
-- 紅線和橘線的主要車站

-- 紅線 (Red Line)
INSERT INTO station (code, name, line, created_at) VALUES
('RK1', '岡山車站', '紅線', CURRENT_TIMESTAMP),
('R24', '岡山醫院', '紅線', CURRENT_TIMESTAMP),
('R23', '橋頭火車站', '紅線', CURRENT_TIMESTAMP),
('R22A', '橋頭糖廠', '紅線', CURRENT_TIMESTAMP),
('R22', '青埔', '紅線', CURRENT_TIMESTAMP),
('R21', '都會公園', '紅線', CURRENT_TIMESTAMP),
('R20', '後勁', '紅線', CURRENT_TIMESTAMP),
('R19', '楠梓科技園區', '紅線', CURRENT_TIMESTAMP),
('R18', '油廠國小', '紅線', CURRENT_TIMESTAMP),
('R17', '世運', '紅線', CURRENT_TIMESTAMP),
('R16', '左營（高鐵）', '紅線', CURRENT_TIMESTAMP),
('R15', '生態園區', '紅線', CURRENT_TIMESTAMP),
('R14', '巨蛋', '紅線', CURRENT_TIMESTAMP),
('R13', '凹子底', '紅線', CURRENT_TIMESTAMP),
('R12', '後驛', '紅線', CURRENT_TIMESTAMP),
('R11', '中央車站', '紅線', CURRENT_TIMESTAMP),
('R9', '中央公園', '紅線', CURRENT_TIMESTAMP),
('R8', '三多商圈', '紅線', CURRENT_TIMESTAMP),
('R7', '獅甲', '紅線', CURRENT_TIMESTAMP),
('R6', '凱旋', '紅線', CURRENT_TIMESTAMP),
('R5', '前鎮高中', '紅線', CURRENT_TIMESTAMP),
('R4A', '草衙', '紅線', CURRENT_TIMESTAMP),
('R4', '國際機場', '紅線', CURRENT_TIMESTAMP),
('R3', '小港', '紅線', CURRENT_TIMESTAMP),

-- 橘線 (Orange Line)
('OT1', '大寮', '橘線', CURRENT_TIMESTAMP),
('O14', '鳳山國中', '橘線', CURRENT_TIMESTAMP),
('O13', '大東', '橘線', CURRENT_TIMESTAMP),
('O12', '鳳山', '橘線', CURRENT_TIMESTAMP),
('O11', '鳳山西站 (市議會)', '橘線', CURRENT_TIMESTAMP),
('O10', '衛武營', '橘線', CURRENT_TIMESTAMP),
('O9', '苓雅運動園區', '橘線', CURRENT_TIMESTAMP),
('O8', '五塊厝', '橘線', CURRENT_TIMESTAMP),
('O7', '文化中心', '橘線', CURRENT_TIMESTAMP),
('O6', '信義國小', '橘線', CURRENT_TIMESTAMP),
('O4', '前金', '橘線', CURRENT_TIMESTAMP),
('O2', '鹽埕埔', '橘線', CURRENT_TIMESTAMP),
('O1', '哈瑪星', '橘線', CURRENT_TIMESTAMP);

-- 乘客回饋測試資料
-- 為幾個熱門車站新增回饋
INSERT INTO feedback (station_id, rating, comment, passenger_name, created_at) VALUES
-- 左營（高鐵）站的回饋 (ID=11, R16)
(11, 5, '車站非常乾淨整潔,轉乘高鐵很方便!', '王小明', CURRENT_TIMESTAMP),
(11, 4, '指標清楚,但尖峰時段人潮較多', '李美麗', CURRENT_TIMESTAMP),
(11, 5, '服務人員態度親切,設施完善', '張大華', CURRENT_TIMESTAMP),

-- 中央車站的回饋 (ID=16, R11)
(16, 4, '交通便利,但月台有點擁擠', '陳小芳', CURRENT_TIMESTAMP),
(16, 5, '地理位置絕佳,周邊商圈發達', '林志明', CURRENT_TIMESTAMP),
(16, 3, '尖峰時段等車時間較長', '黃淑芬', CURRENT_TIMESTAMP),

-- 三多商圈的回饋 (ID=18, R8)
(18, 5, '購物超方便!出站就是百貨公司', '吳佳玲', CURRENT_TIMESTAMP),
(18, 4, '商圈熱鬧,但假日人很多', '劉建國', CURRENT_TIMESTAMP),

-- 中央公園的回饋 (ID=17, R9)
(17, 5, '公園景色優美,適合散步', '周雅婷', CURRENT_TIMESTAMP),
(17, 4, '轉乘動線設計良好', '鄭文傑', CURRENT_TIMESTAMP),

-- 巨蛋站的回饋 (ID=13, R14)
(13, 5, '看演唱會超方便!', '許雅雯', CURRENT_TIMESTAMP),
(13, 4, '活動散場時人潮管制做得不錯', '謝明宏', CURRENT_TIMESTAMP),

-- 文化中心的回饋 (ID=33, O7)
(33, 5, '文藝氣息濃厚,環境優美', '蔡淑惠', CURRENT_TIMESTAMP),
(33, 4, '適合假日休閒,但停車位較少', '楊志強', CURRENT_TIMESTAMP),

-- 小港站的回饋 (ID=24, R3)
(24, 3, '離機場還有一段距離,建議增加接駁車', '賴美玲', CURRENT_TIMESTAMP),
(24, 4, '車站整潔,但周邊設施較少', '郭俊傑', CURRENT_TIMESTAMP);

-- Made with Bob
