package org.example.exception;

/**
 * @author : [Welsir]
 * @description : [ResultCode基类]
 * @createTime : [2023/5/19 20:02]
 */
public interface BaseResultCode {
    /**
     * 状态码
     */
    int getCode();

    /**
     * 提示信息
     */
    String getMsg();
}
