package com.springboot.springbootdemo.util;

import java.io.Serializable;

/**
 * Description: 统一web返回结果
 */
public class ReturnValue<T> implements Serializable {
    private static final long serialVersionUID = -1959544190118740608L;
    private int ret;
    private String msg;
    private T data;

    public ReturnValue() {
        this.ret = 0;
        this.msg = "";
        this.data = null;
    }

    public ReturnValue(int retCode, String msg, T data) {
        this.ret = 0;
        this.msg = "";
        this.data = null;
        this.ret = retCode;
        this.data = data;
        this.msg = msg;
    }

    public ReturnValue(int retCode, String msg) {
        this.ret = 0;
        this.msg = "";
        this.data = null;
        this.ret = retCode;
        this.msg = msg;
    }

    public ReturnValue(ReturnCodeAndMsgEnum codeAndMsg) {
        this(codeAndMsg.getCode(), codeAndMsg.getMsg(), null);
    }

    public ReturnValue(ReturnCodeAndMsgEnum codeAndMsg, T data) {
        this(codeAndMsg.getCode(), codeAndMsg.getMsg(), data);
    }

    public int getRet() {
        return this.ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnValue{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
