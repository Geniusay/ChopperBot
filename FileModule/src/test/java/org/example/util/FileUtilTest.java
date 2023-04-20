package org.example.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Genius
 * @date 2023/04/20 14:15
 **/
public class FileUtilTest {

    @Test
    public void testDelete() throws IOException {
        FileUtil.deleteDirectory("E:\\Project\\ChopperBot\\FileModule\\src\\main\\resources\\trash");
    }
}
