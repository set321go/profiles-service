package com.gbook.profiles.common.impl;

import com.gbook.profiles.DatabaseFactory;
import com.gbook.profiles.common.CommonProfileDataLoader;
import com.gbook.profiles.common.ProfileCommon;
import com.gbook.profiles.identity.Identity;
import com.github.davidmoten.rx.jdbc.Database;
import com.google.inject.Singleton;
import ratpack.exec.Promise;
import ratpack.rx.RxRatpack;
import rx.Observable;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 19:34
 */
@Singleton
public class RxPostgresProfileDataLoader implements CommonProfileDataLoader {
    @Inject
    private DatabaseFactory databaseFactory;

    @Override
    public Observable<ProfileCommon> findFor(Identity aIdentity) {
        return databaseFactory.getConnection()
                .select("SELECT user_name FROM profile_common WHERE user_guid=:guid")
                .parameter("guid", aIdentity.getGuid())
                .get(rs -> new ProfileCommon(rs.getString("user_name")))
                .first();
    }

    @Override
    public Observable<Boolean> updateFor(Identity aIdentity, ProfileCommon aProfileCommon) {
        Database conn = databaseFactory.getConnection();

        return conn.commit(conn.update("INSERT INTO profile_common (user_name) VALUES (:name) WHERE user_guid=:guid")
                .parameter("name", aProfileCommon.getName())
                .parameter("guid", aIdentity.getGuid())
                .dependsOn(conn.beginTransaction())
                .count());
    }

    @Override
    public Observable<Boolean> create(Identity aIdentity, ProfileCommon aProfileCommon) {
        Database conn = databaseFactory.getConnection();

        return conn.commit(conn.update("INSERT INTO profile_common (user_guid, user_name) VALUES (:guid, :name)")
                .parameter("guid", aIdentity.getGuid())
                .parameter("name", aProfileCommon.getName())
                .dependsOn(conn.beginTransaction())
                .count());
    }
}
