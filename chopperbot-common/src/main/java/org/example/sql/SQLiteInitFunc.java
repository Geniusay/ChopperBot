package org.example.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.sqlite.SQLiteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Genius
 * @date 2023/10/19 21:43
 **/
@Component
public class SQLiteInitFunc implements SQLInitFunc {

    @Value("${spring.datasource.url}")
    private String sqlPath;

    private String getPath(){
        if(StringUtils.hasText(sqlPath)){
            String[] split = sqlPath.split(":");
            if(split.length!=0){
                return split[split.length - 1];
            }
        }else{
            throw new RuntimeException("数据库不存在");
        }
        return "";
    }

    @Override
    public boolean databaseExists() {
        return Files.exists(Paths.get(getPath()));
    }

    @Override
    public boolean tableExists(String tableName) {
        try(Connection conn = DriverManager.getConnection(sqlPath);
            Statement stmt = conn.createStatement()){
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, tableName, null);
            return tables.next();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createDatabase() {
        try {
            return Files.exists(Files.createFile(Paths.get(getPath())));
        }catch (IOException e){
            return false;
        }
    }

    @Override
    public boolean createTable(String sql) {
        return doSQL(sql);
    }

    @Override
    public boolean initData(String sql) {
        return doSQL(sql);
    }

    @Override
    public <T> boolean initData(List<T> data, BaseMapper<T> baseMapper) {
        for (T entity : data) {
            try {
                if (baseMapper.insert(entity)==0) {
                    return false;
                }
            }catch (Exception e){
                if(!(e instanceof UncategorizedSQLException)){
                   return false;
                }
            }
        }
        return true;
    }

    private boolean doSQL(String sql){
        try (Connection conn = DriverManager.getConnection(sqlPath);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
