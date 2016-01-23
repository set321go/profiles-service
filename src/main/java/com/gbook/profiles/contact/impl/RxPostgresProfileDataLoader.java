package com.gbook.profiles.contact.impl;

import com.gbook.profiles.DatabaseFactory;
import com.gbook.profiles.contact.ContactDataFactory;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.ProfileContactDataLoader;
import com.gbook.profiles.identity.Identity;
import com.github.davidmoten.rx.jdbc.Database;
import com.google.inject.Singleton;
import rx.Observable;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 10:46
 */
@Singleton
public class RxPostgresProfileDataLoader implements ProfileContactDataLoader {
    @Inject
    private DatabaseFactory databaseFactory;

    @Inject
    private ContactDataFactory contactDataFactory;

    @Override
    public Observable<ProfileContact> findAllFor(Identity aIdentity) {
        return databaseFactory.getConnection()
                .select("SELECT contact_id, contact_type, contact_value, default_contact FROM profile_contacts WHERE user_guid=:guid")
                .parameter("guid", aIdentity.getGuid())
                .get(rs -> new ProfileContact(rs.getInt("contact_id"),
                        rs.getString("contact_type"),
                        contactDataFactory.getContactData(
                                rs.getString("contact_type"),
                                rs.getString("contact_value")),
                        rs.getBoolean("default_contact")));
    }

    @Override
    public Observable<Boolean> updateContactInfo(Identity aIdentity, ProfileContact aProfileContact) {
        Database conn = databaseFactory.getConnection();

        return conn.commit(conn.update("INSERT INTO profile_contacts (user_guid, contact_type, contact_value, default_contact) VALUES (:guid, :type, :value, :default) where guid=:contact_id")
                .parameter("guid", aIdentity.getGuid())
                .parameter("type", aProfileContact.getType())
                .parameter("value", aProfileContact.getValue().flatten())
                .parameter("default", aProfileContact.isDefaultContact())
                .parameter("contact_id", aProfileContact.getGuid())
                .dependsOn(conn.beginTransaction())
                .count());
    }

    @Override
    public Observable<Boolean> create(Identity aIdentity, ProfileContact aProfileContact) {
        Database conn = databaseFactory.getConnection();

        return conn.commit(conn.update("INSERT INTO profile_contacts (user_guid, contact_type, contact_value, default_contact) VALUES (:guid, :type, :value, :default)")
                .parameter("guid", aIdentity.getGuid())
                .parameter("type", aProfileContact.getType())
                .parameter("value", aProfileContact.getValue().flatten())
                .parameter("default", aProfileContact.isDefaultContact())
                .dependsOn(conn.beginTransaction())
                .count());
    }
}
