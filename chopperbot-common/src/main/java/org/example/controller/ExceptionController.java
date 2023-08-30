package org.example.controller;
/**
 * @description : [描述说明该类的功能]
 * @author : [Welsir]
 * @createTime : [2023/5/19 20:56]
 */

import org.example.exception.BaseException;
import org.springframework.web.bind.annotation.*;

/**
 * @author welsir
 * @date 2023/5/19 20:56
 */
@RestController
public class ExceptionController {

    @GetMapping("test")
    public void testLogin() throws BaseException {
        System.out.println(10/0);
    }
}
