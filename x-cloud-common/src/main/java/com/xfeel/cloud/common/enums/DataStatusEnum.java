package com.xfeel.cloud.common.enums;

/**
 * @author Admin
 */
public enum DataStatusEnum {
    NORMAL(1), DELETED(0);

    private int status;

    DataStatusEnum(int status) {
        this.status = status;
    }

}
