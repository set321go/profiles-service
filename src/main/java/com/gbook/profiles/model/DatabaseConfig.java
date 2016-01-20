package com.gbook.profiles.model;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-18
 * Time: 18:12
 */
public class DatabaseConfig {
    private String host;
    private String port;
    private String database;
    private String schema;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String aHost) {
        host = aHost;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String aPort) {
        port = aPort;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String aDatabase) {
        database = aDatabase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String aUsername) {
        username = aUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String aPassword) {
        password = aPassword;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String aSchema) {
        schema = aSchema;
    }
}
