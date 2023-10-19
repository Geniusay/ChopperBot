package org.example.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

public interface SQLInitFunc {

    boolean databaseExists();

    boolean tableExists(String tableName);

    boolean createDatabase();

    boolean createTable(String sql);

    boolean initData(String sql);

    <T> boolean initData(List<T> data,BaseMapper<T> baseMapper);
}
