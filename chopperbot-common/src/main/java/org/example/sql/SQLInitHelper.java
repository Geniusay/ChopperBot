package org.example.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Genius
 * @date 2023/10/19 22:23
 **/
@Component
public class SQLInitHelper {

    @Resource
    SQLInitFunc sqlInitFunc;

    public boolean hasTable(String tableName){
        return sqlInitFunc.tableExists(tableName);
    }
    public boolean initTable(String tableName,String sql){
        if(!hasTable(tableName)){
            if (sqlInitFunc.createTable(sql)) {
                ChopperLogFactory.getLogger(LoggerType.System).info(tableName+" table create success!");
                return true;
            }
        }
        return true;
    }

    public boolean initDatabase(){
        if(!sqlInitFunc.databaseExists()){
            return sqlInitFunc.createDatabase();
        }
        return true;
    }

    public<T> boolean initData(List<T> data, BaseMapper<T> mapper){
        return sqlInitFunc.initData(data,mapper);
    }
}
