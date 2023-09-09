package org.example.config;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Genius
 * @date 2023/09/09 19:35
 **/
@Configuration
@MapperScan("org.example.mapper")
public class MybatisConfig {


}
