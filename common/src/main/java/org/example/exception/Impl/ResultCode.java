package org.example.exception.Impl;/**
 * @description : [描述说明该类的功能]
 * @author : [Welsir]
 * @createTime : [2023/5/19 20:03]
 */

/**
 * @author welsir
 * @date 2023/5/19 20:03
 */
public enum ResultCode {

    OK(200, "成功"),
    ERROR(300,"系统异常"),
    PARAMETER_ERROR(301, "参数错误");

    private int code;

    private String msg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCode(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
