package org.example.exception;


import org.example.exception.Impl.ResultCode;
import org.example.exception.plugin.PluginDependOnException;
import org.example.exception.plugin.PluginException;
import org.example.exception.plugin.PluginNotRegisterException;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : [Welsir]
 * @description : [统一接口格式输出类]
 * @createTime : [2023/5/19 20:20]
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    public static Logger logger = ChopperLogFactory.getLogger(LoggerType.System);


    @ExceptionHandler(PluginException.class)
    public Result handlerPluginException(HttpServletRequest request,PluginException ex){
        logger.error("Handle Exception Request Url:{},Exception:{}", request.getRequestURL(), ex);
        if(ex instanceof PluginNotRegisterException){
            PluginNotRegisterException exception = (PluginNotRegisterException) ex;
            return Result.error(exception.getResultCode());
        }else if(ex instanceof PluginDependOnException){
            PluginDependOnException exception = (PluginDependOnException) ex;
            return Result.error(exception.getResultCode(), Map.of(
                    "fatherName",exception.getFatherName(),
                    "sonName",exception.getSonName()
            ));
        }
        return Result.error("error");
    }

    @ExceptionHandler(BaseException.class)
    public Result handleException(HttpServletRequest request,
                                  Exception ex) {
        logger.error("Handle Exception Request Url:{},Exception:{}", request.getRequestURL(), ex);
        Result result = new Result();
        //系统异常
        if (ex instanceof BaseException) {
            BaseException se = (BaseException) ex;
            ResultCode resultCode = se.getResultCode();
            if (resultCode == null) {
                result = Result.error(se.getMessage());
            } else {
                result = new Result(resultCode.getCode(),
                        StringUtils.isEmpty(se.getMessage()) ? se.getMessage() : resultCode.getMsg());
            }
        }
        //参数错误
        else {
            result = new Result(ResultCode.ERROR.getCode(), ex.getMessage());
        }
        logger.info("exception handle result:" + result);
        return result;
    }

}
