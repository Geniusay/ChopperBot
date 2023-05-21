package org.example.controller;/**
 * @description : [描述说明该类的功能]
 * @author : [Welsir]
 * @createTime : [2023/5/21 19:45]
 */

import org.example.exception.BaseException;
import org.example.exception.Impl.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author welsir
 * @date 2023/5/21 19:45
 */
@RestController
public class ExceptionController {

    @GetMapping("test")
    public void testLogin() throws BaseException {
        throw new BaseException();
    }
}
