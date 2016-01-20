package com.gbook.profiles;

import com.gbook.profiles.model.DatabaseConfig;
import com.github.davidmoten.rx.jdbc.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-18
 * Time: 18:05
 */
@Singleton
public class DatabaseFactory {
    private DatabaseConfig dbConfig;
    private String url;

    @Inject
    public DatabaseFactory(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
        url = "jdbc:postgresql://"
                + dbConfig.getHost()
                + ":" + dbConfig.getPort()
                + "/" + dbConfig.getDatabase()
                + "?currentSchema=" + dbConfig.getSchema();
    }

    public Database getConnection() {
        return Database.from(url, dbConfig.getUsername(), dbConfig.getPassword());
    }
}
