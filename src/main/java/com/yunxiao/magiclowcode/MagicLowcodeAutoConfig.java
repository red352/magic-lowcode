package com.yunxiao.magiclowcode;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.ssssssss.magicapi.spring.boot.starter.MagicAPIAutoConfiguration;

@Configuration
@ComponentScan(basePackages = "com.yunxiao.magiclowcode.custom")
@RequiredArgsConstructor
@AutoConfigureBefore(MagicAPIAutoConfiguration.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MagicLowcodeAutoConfig implements InitializingBean {

    final JdbcTemplate jdbcTemplate;
    void createTable() {
        var sql = """
                CREATE TABLE IF NOT EXISTS `magic_api_file_v2` (
                  `file_path` varchar(512) NOT NULL,
                  `file_content` mediumtext,
                  PRIMARY KEY (`file_path`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
                                
                """;
        jdbcTemplate.execute(sql);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        createTable();
    }
}
