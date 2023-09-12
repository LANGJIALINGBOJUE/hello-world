package com.langjialing.helloworld.config.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class SftpConfig {

    @Value("${sftp.host}")
    private String hostname;

    @Value("${sftp.port}")
    private Integer port;

    @Value("${sftp.user}")
    private String username;

    @Value("${sftp.password}")
    private String password;

    @Value("${sftp.timeout}")
    private Integer timeout;
    private Resource privateKey;

    @Value("${sftp.remoteRootPath}")
    private String remoteRootPath;
    private String fileSuffix;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Resource getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(Resource privateKey) {
        this.privateKey = privateKey;
    }

    public String getRemoteRootPath() {
        return remoteRootPath;
    }

    public void setRemoteRootPath(String remoteRootPath) {
        this.remoteRootPath = remoteRootPath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public SftpConfig(String hostname, Integer port, String username, String password,
                      Integer timeout, Resource privateKey, String remoteRootPath, String fileSuffix) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.timeout = timeout;
        this.privateKey = privateKey;
        this.remoteRootPath = remoteRootPath;
        this.fileSuffix = fileSuffix;
    }
    public SftpConfig(String hostname, Integer port, String username, String password,
                      Integer timeout, String remoteRootPath) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.timeout = timeout;
        this.remoteRootPath = remoteRootPath;
    }
    public SftpConfig() {
    }
}
