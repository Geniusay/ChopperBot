package org.example.sqlite;

import org.example.ConsoleApplication;
import org.example.bean.FocusLiver;
import org.example.constpool.ConstPool;
import org.example.mapper.FocusLiverMapper;
import org.example.service.FocusLiverService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/09/09 20:07
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SQLiteTest {

    @Resource
    FocusLiverService service;

    @Test
    public void testSQLiteInsert(){
        FocusLiver focusLiver = new FocusLiver(0, "Asaki大人", "6154037", ConstPool.BILIBILI, "独立游戏");
        System.out.println(service.addLivers(focusLiver));
    }
}
