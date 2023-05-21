package org.example.util;
/**
 * @description : [统一接口格式输出类]
 * @author : [Welsir]
 * @createTime : [2023/5/19 20:17]
 */

import org.example.exception.BaseResultCode;
import org.example.exception.Impl.ResultCode;

import java.io.Serializable;

/**
 * @author welsir
 * @date 2023/5/19 20:17
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -1773941471021475043L;

    private Object data;
    private int code;
    private String msg;

    public Result()
    {
    }

    public Result(int code, Object data, String msg)
    {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(int code, String desc)
    {
        this(code, null, desc);
    }


    public static Result success()
    {
        return success(null);
    }

    public static Result success(Object data)
    {
        Result result = new Result();
        result.setData(data);
        result.setCode(ResultCode.OK.getCode());
        return result;
    }

    public static Result error(String msg)
    {
        Result result = new Result();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "{data:"+data+",code:"+code+",msg:"+msg+"}";
    }
}
