package de.jeha.demo.springboot.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author jenshadlich@googlemail.com
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource pooledMysqlDataSource(MysqlConfiguration mysqlConfiguration) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl(mysqlConfiguration.getUrl());
        basicDataSource.setMaxTotal(10);
        basicDataSource.setMaxIdle(10);
        basicDataSource.setMinIdle(2);
        basicDataSource.setTestOnBorrow(false);
        basicDataSource.setTestWhileIdle(true);
        basicDataSource.setTimeBetweenEvictionRunsMillis(120_000);
        basicDataSource.setNumTestsPerEvictionRun(20);
        basicDataSource.setValidationQueryTimeout(10_000);
        basicDataSource.setValidationQuery("SELECT 1 FROM DUAL;");
        basicDataSource.setSoftMinEvictableIdleTimeMillis(900_000);
        basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        basicDataSource.setMaxWaitMillis(10_000);
        basicDataSource.setDefaultAutoCommit(false);
        return basicDataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager mysqlTransactionManager(DataSource mysqlSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(mysqlSource);
        return dataSourceTransactionManager;
    }

}
