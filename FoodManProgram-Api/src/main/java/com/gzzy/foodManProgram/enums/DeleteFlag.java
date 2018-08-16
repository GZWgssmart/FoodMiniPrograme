package com.gzzy.foodManProgram.enums;

/**
 * 删除标志
 */
public enum DeleteFlag {

    NORMARL(0, "正常"), DELETED(1, "已删除");

    private int value;

    private String name;

    DeleteFlag(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
