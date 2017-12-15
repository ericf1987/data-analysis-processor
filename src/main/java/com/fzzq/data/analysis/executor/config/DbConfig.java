package com.fzzq.data.analysis.executor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by fengye on 2017/8/7.
 */
@Component
@ConfigurationProperties(prefix = "db")
public class DbConfig {

    public static final String USER_KF = "kf";
    public static final String USER_ZX = "zx";
    public static final String USER_JF = "jf";
    public static final String USER_CC = "cc";
    public static final String USER_DC = "outuser";

    public static final String PASS_KF = "zxcvbnm";
    public static final String PASS_ZX = "zxcvbnm";
    public static final String PASS_JF = "jf";
    public static final String PASS_CC = "cc";
    public static final String PASS_DC = "testpwd";

    public static final String DATABASE_DC = "devmultidb";

    private String driver;

    private String url;

    private String user;

    private String pass;

    private String managerUser;

    private String managerPass;

    private int poolSize;

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public String getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(String managerUser) {
        this.managerUser = managerUser;
    }

    public String getManagerPass() {
        return managerPass;
    }

    public void setManagerPass(String managerPass) {
        this.managerPass = managerPass;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getManagerUrl() {
        return getUrl(getManagerUser());
    }

    public String getUrl(String database) {
        return url.replaceAll("\\{\\{database}}", database);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public String getUser(String userName) {
        return userName.replaceAll("\\{\\{user}}", userName);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public String getPass(String pass){
        return pass.replaceAll("\\{\\{pass}}", pass);
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
