package com.metro.model;

/**
 * 回饋類型列舉
 * 
 * 定義乘客回饋的六種類型
 */
public enum FeedbackType {
    /**
     * 設施相關回饋
     */
    FACILITY("設施"),
    
    /**
     * 服務品質回饋
     */
    SERVICE("服務"),
    
    /**
     * 清潔度回饋
     */
    CLEANLINESS("清潔"),
    
    /**
     * 安全性回饋
     */
    SAFETY("安全"),
    
    /**
     * 無障礙設施回饋
     */
    ACCESSIBILITY("無障礙"),
    
    /**
     * 其他類型回饋
     */
    OTHER("其他");
    
    private final String displayName;
    
    FeedbackType(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * 取得顯示名稱
     * 
     * @return 中文顯示名稱
     */
    public String getDisplayName() {
        return displayName;
    }
}

// Made with Bob