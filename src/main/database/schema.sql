CREATE SCHEMA profiles
  CREATE TABLE profile_common(
    common_id SERIAL PRIMARY KEY,
    user_guid UUID UNIQUE,
    user_name TEXT
  )
  CREATE TABLE profile_contacts(
    contact_id SERIAL PRIMARY KEY,
    user_guid UUID,
    contact_type VARCHAR(10),
    contact_value TEXT,
    default_contact BOOLEAN
  );