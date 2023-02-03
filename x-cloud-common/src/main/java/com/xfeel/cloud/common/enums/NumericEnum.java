package com.xfeel.cloud.common.enums;

/**
 * @author Admin
 */

public enum NumericEnum {
    /**
     * 0
     */
    ZERO(0, "〇", "零", "", "⓪"),
    /**
     * 1
     */
    ONE(1, "一", "壹", "Ⅰ", "①"),
    /**
     * 2
     */
    TWO(2, "二", "贰", "Ⅱ", "②"),
    /**
     * 3
     */
    THREE(3, "三", "叁", "Ⅲ", "③"),
    /**
     * 4
     */
    FOUR(4, "四", "肆", "Ⅳ", "④"),
    /**
     * 5
     */
    FIVE(5, "五", "伍", "Ⅴ", "⑤"),
    /**
     * 6
     */
    SIX(6, "六", "陆", "Ⅵ", "⑥"),
    /**
     * 7
     */
    SEVEN(7, "七", "柒", "Ⅶ", "⑦"),
    /**
     * 8 八
     */
    EIGHT(8, "八", "捌", "Ⅷ", "⑧"),
    /**
     * 9 玖
     */
    NINE(9, "九", "玖", "Ⅸ", "⑨"),
    /**
     * 10
     */
    TEN(10, "十", "拾", "Ⅹ", "⑩");

    private final int value;

    private final String uppercase;

    private final String lowercase;

    private final String roman ;

    private final String circle;

    NumericEnum(int value, String lowercase, String uppercase, String roman, String circle) {
        this.value = value;
        this.lowercase = lowercase;
        this.uppercase = uppercase;
        this.roman = roman;
        this.circle = circle;
    }

    public int value() {
        return this.value;
    }

    public String getLowercase() {
        return lowercase;
    }

    public String getUppercase() {
        return uppercase;
    }

    public String getRoman() {
        return roman;
    }

    public String getCircle() {
        return circle;
    }

    public static NumericEnum valueOf(int value) {
        NumericEnum status = resolve(value);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + value + "]");
        }
        return status;
    }

    public static NumericEnum resolve(int code) {
        for (NumericEnum status : values()) {
            if (status.value == code) {
                return status;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
