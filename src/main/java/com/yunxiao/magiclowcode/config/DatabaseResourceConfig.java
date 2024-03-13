package com.yunxiao.magiclowcode.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author LuoYunXiao
 * @since 2024/3/13 16:19
 */
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "magic-api", name = "resource.type", havingValue = "database")
public class DatabaseResourceConfig implements InitializingBean {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseResourceConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void createTable() {
        var sql = """
                CREATE TABLE IF NOT EXISTS `magic_api_file_v2` (
                  `file_path` varchar(512) NOT NULL,
                  `file_content` mediumtext,
                  PRIMARY KEY (`file_path`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
                                
                """;
        log.info("执行建表语句\n{}", sql);
        jdbcTemplate.execute(sql);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createTable();
    }
}
