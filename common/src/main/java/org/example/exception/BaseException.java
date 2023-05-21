package org.example.exception;

import org.example.exception.Impl.ResultCode;

/**
 * @description : [异常基类]
 * @author : [Welsir]
 * @createTime : [2023/5/19 20:04]
 */

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 5225171867523879342L;

    private int code;

    private String msg;

    private Object[] params;

    ResultCode resultCode;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public BaseException(String message, Object[] params, Throwable cause)
    {
        super(message, cause);
        this.params = params;
    }

    public BaseException() {
        super();
    }

    public BaseException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String msg, Object[] params) {
        super(msg);
        this.params = params;
    }

    public BaseException(String msg,Throwable throwable){
        super(msg,throwable);
    }

    public BaseException(String msg, int code, Object[] params) {
        this(msg, code);
        this.params = params;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
