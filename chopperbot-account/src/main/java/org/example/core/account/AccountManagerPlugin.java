package org.example.core.account;

import org.example.plugin.SpringBootPlugin;
import org.example.sql.SQLInitHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/20 02:48
 **/
@Component
public class AccountManagerPlugin extends SpringBootPlugin {

    @Resource
    SQLInitHelper helper;

    @Override
    public boolean init() {
        helper.initTable("account","CREATE TABLE \"account\" (\n" +
                "\t\"uid\"\tINTEGER NOT NULL,\n" +
                "\t\"username\"\tTEXT,\n" +
                "\t\"cookies\"\tTEXT NOT NULL,\n" +
                "\t\"is_complete_match\"\tTEXT,\n" +
                "\t\"platform_id\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"uid\")\n" +
                ")");
        helper.initTable("account_type","CREATE TABLE \"account_type\" (\n" +
                "  \"uid\" INTEGER NOT NULL,\n" +
                "  \"type\" TEXT NOT NULL,\n" +
                "  PRIMARY KEY (\"uid\")\n" +
                ")");
        return super.init();
    }
}
