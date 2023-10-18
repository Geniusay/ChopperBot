package org.example.core.factory;

import org.openqa.selenium.Cookie;

import java.util.Set;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 21:17
 */

public interface PlatformOperation {

    Set<Cookie> login(int id, String account);

}
