package com.lea.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2020-10-11 17:55
 */
@Component
public class JDBCProperties {

    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${maxActive}")
    private Integer maxActive;

    public JDBCProperties() {}

    public JDBCProperties(String url, String username, String password, Integer maxActive) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxActive = maxActive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    @Override
    public String toString() {
        return "JDBCProperties{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", maxActive=" + maxActive +
                '}';
    }
}
