package com.springboot.springbootdemo.util;

/**
 * Description: web相关接口返回状态枚举
 */
public enum  ReturnCodeAndMsgEnum {
    Success(0, "ok"),
    No_Data(-1, "no data"),
    SYSTEM_ERROR(10004, "system error");

    private String msg;
    private int code;

    private ReturnCodeAndMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ReturnCodeAndMsgEnum getByCode(int code) {
        ReturnCodeAndMsgEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ReturnCodeAndMsgEnum aiTypeEnum = var1[var3];
            if (aiTypeEnum.code == code) {
                return aiTypeEnum;
            }
        }

        return Success;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}