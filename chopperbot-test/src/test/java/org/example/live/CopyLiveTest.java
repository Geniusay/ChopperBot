package org.example.live;

import org.example.ConsoleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Genius
 * @date 2023/09/15 20:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CopyLiveTest {

    @Test
    public void testCopyLive() throws InterruptedException, IOException {
        System.out.println("copy");
        Thread.sleep(10000);
        String sourceFilePath = ("E:\\Project\\ChopperBot\\chopperbot-test\\config\\LiveRecord\\online\\bilibili\\Asaki大人_2023-09-15 17_59_43.flv");
        String targetFilePath =("E:\\Project\\ChopperBot\\chopperbot-test\\config\\LiveRecord\\online\\bilibili\\Asaki大人_2023-09-15 17_59_43_temp.flv");
        Files.copy(Path.of(sourceFilePath), Path.of(targetFilePath));
        Thread.sleep(10000);
    }
}
