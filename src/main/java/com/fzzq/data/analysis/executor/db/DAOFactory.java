package com.fzzq.data.analysis.executor.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.fzzq.data.analysis.executor.config.DbConfig;
import com.hyd.dao.DAO;
import com.hyd.dao.DataSources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * Created by fengye on 2017/8/7.
 */
@Component
public class DAOFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DAOFactory.class);

    @Autowired
    private DbConfig dbConfig;

    private DataSources dataSources = new DataSources();

    public synchronized DAO getKFDao(String database) {
        if (!dataSources.contains(database)) {
            dataSources.setDataSource(database, getKFDataSource());
        }
        return dataSources.getDAO(database);
    }

    public synchronized DAO getZXDao(String database) {
        if (!dataSources.contains(database)) {
            dataSources.setDataSource(database, getZXDataSource());
        }
        return dataSources.getDAO(database);
    }

    public synchronized DAO getJFDao(String database) {
        if (!dataSources.contains(database)) {
            dataSources.setDataSource(database, getJFDataSource());
        }
        return dataSources.getDAO(database);
    }

    public synchronized DAO getCCDao(String database) {
        if (!dataSources.contains(database)) {
            dataSources.setDataSource(database, getCCDataSource());
        }
        return dataSources.getDAO(database);
    }

    public synchronized DAO getDCDao(String database) {
        if(!dataSources.contains(database)){
            dataSources.setDataSource(database, getDCDataSource());
        }
        return dataSources.getDAO(database);
    }

    private synchronized DataSource getKFDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriver());
        dataSource.setUrl(dbConfig.getUrl());
        dataSource.setUsername(dbConfig.getUser(DbConfig.USER_KF));
        dataSource.setPassword(dbConfig.getPass(DbConfig.PASS_KF));
        dataSource.setMaxActive(dbConfig.getPoolSize());
        return dataSource;
    }

    private synchronized DataSource getZXDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriver());
        dataSource.setUrl(dbConfig.getUrl());
        dataSource.setUsername(dbConfig.getUser(DbConfig.USER_ZX));
        dataSource.setPassword(dbConfig.getPass(DbConfig.PASS_ZX));
        dataSource.setMaxActive(dbConfig.getPoolSize());
        return dataSource;
    }

    private synchronized DataSource getJFDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriver());
        dataSource.setUrl(dbConfig.getUrl());
        dataSource.setUsername(dbConfig.getUser(DbConfig.USER_JF));
        dataSource.setPassword(dbConfig.getPass(DbConfig.PASS_JF));
        dataSource.setMaxActive(dbConfig.getPoolSize());
        return dataSource;
    }

    private synchronized DataSource getCCDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriver());
        dataSource.setUrl(dbConfig.getUrl());
        dataSource.setUsername(dbConfig.getUser(DbConfig.USER_CC));
        dataSource.setPassword(dbConfig.getPass(DbConfig.PASS_CC));
        dataSource.setMaxActive(dbConfig.getPoolSize());
        return dataSource;
    }

    //东财数据库
    private synchronized DataSource getDCDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriver());
        dataSource.setUrl(dbConfig.getUrl(DbConfig.DATABASE_DC));
        dataSource.setUsername(dbConfig.getUser(DbConfig.USER_DC));
        dataSource.setPassword(dbConfig.getPass(DbConfig.PASS_DC));
        dataSource.setMaxActive(dbConfig.getPoolSize());
        return dataSource;
    }

}
