package com.li.learn.datasourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/*CommandLineRunner:平常开发中有可能需要实现在项目启动后执行的功能，加上这个注解把要执行代码的写在run里面就可以*/
@SpringBootApplication
@Slf4j
public class DatasourceDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DatasourceDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();

    }

    private void showConnection() throws SQLException{
        log.info(dataSource.toString());
        Connection conn=dataSource.getConnection();
        log.info(conn.toString());
        conn.close();
    }
    private void showData(){
        jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row->log.info(row.toString()));
    }
}
