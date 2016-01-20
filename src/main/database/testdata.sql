INSERT INTO profiles.profile_common (user_guid, user_name) VALUES ('85968854-b7f4-11e5-9912-ba0be0483c18', 'Dummy Name');

INSERT INTO profiles.profile_contacts (user_guid, contact_type, default_contact, contact_value) VALUES ('85968854-b7f4-11e5-9912-ba0be0483c18', 'email', TRUE, 'a@a.com');
INSERT INTO profiles.profile_contacts (user_guid, contact_type, default_contact, contact_value) VALUES ('85968854-b7f4-11e5-9912-ba0be0483c18', 'phone', FALSE , '111-111-1111');
INSERT INTO profiles.profile_contacts (user_guid, contact_type, default_contact, contact_value) VALUES ('85968854-b7f4-11e5-9912-ba0be0483c18', 'address', FALSE , 'Street:City:Region:Postcode:CA');