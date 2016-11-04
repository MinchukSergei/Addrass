CREATE DATABASE IF NOT EXISTS addrass_db CHARACTER SET utf8;

CREATE TABLE user_group
(
  pk_id INT(11) PRIMARY KEY NOT NULL,
  name VARCHAR(32) NOT NULL,
  icon MEDIUMBLOB NOT NULL
);
CREATE UNIQUE INDEX addrass_group_name_uindex ON user_group (name);

CREATE TABLE event_type
(
  pk_id INT(11) PRIMARY KEY NOT NULL,
  name VARCHAR(16) NOT NULL,
  icon MEDIUMBLOB NOT NULL
);
CREATE UNIQUE INDEX event_type_name_uindex ON event_type (name);

CREATE TABLE user_color
(
  pk_id INT(11) PRIMARY KEY NOT NULL,
  color VARCHAR(16) NOT NULL
);
CREATE UNIQUE INDEX user_color_color_uindex ON user_color (color);

CREATE TABLE user_data
(
  user_login VARCHAR(32) PRIMARY KEY NOT NULL,
  user_password VARCHAR(64) NOT NULL,
  user_name VARCHAR(64) NOT NULL,
  user_phone_field VARCHAR(15),
  user_email_field VARCHAR(32),
  user_organization_field VARCHAR(32),
  user_addrass_field VARCHAR(32),
  user_notes_field VARCHAR(64),
  user_photo LONGBLOB
);

CREATE TABLE user_event
(
  pk_id INT(11) PRIMARY KEY NOT NULL,
  name VARCHAR(32) NOT NULL,
  event_owner VARCHAR(32),
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  event_type INT(11),
  event_coordinate_x DOUBLE,
  event_coordinate_y DOUBLE,
  CONSTRAINT user_event_event_type_pk_id_fk FOREIGN KEY (event_type) REFERENCES event_type (pk_id),
  CONSTRAINT user_event_user_data_user_login_fk FOREIGN KEY (event_owner) REFERENCES user_data (user_login)
);
CREATE INDEX user_event_event_type_pk_id_fk ON user_event (event_type);
CREATE INDEX user_event_user_data_user_login_fk ON user_event (event_owner);

CREATE TABLE contact_list
(
  pk_id INT(11) PRIMARY KEY NOT NULL,
  user_main VARCHAR(32) NOT NULL,
  user_friend VARCHAR(32) NOT NULL,
  black_list TINYINT(1) DEFAULT '0' NOT NULL,
  user_color INT(11),
  user_group INT(11),
  CONSTRAINT contact_list_user_color_pk_id_fk FOREIGN KEY (user_color) REFERENCES user_color (pk_id),
  CONSTRAINT contact_list_user_data_user_login_fk_friend FOREIGN KEY (user_friend) REFERENCES user_data (user_login),
  CONSTRAINT contact_list_user_data_user_login_fk_main FOREIGN KEY (user_main) REFERENCES user_data (user_login),
  CONSTRAINT contact_list_user_group_pk_id_fk FOREIGN KEY (user_group) REFERENCES user_group (pk_id)
);
CREATE INDEX contact_list_user_color_pk_id_fk ON contact_list (user_color);
CREATE INDEX contact_list_user_data_user_login_fk_friend ON contact_list (user_friend);
CREATE INDEX contact_list_user_data_user_login_fk_main ON contact_list (user_main);
CREATE INDEX contact_list_user_group_pk_id_fk ON contact_list (user_group);

CREATE TABLE event_member
(
  pk_id INT(11) PRIMARY KEY NOT NULL,
  event_id INT(11),
  user_login VARCHAR(32),
  CONSTRAINT event_member_user_data_user_login_fk FOREIGN KEY (user_login) REFERENCES user_data (user_login),
  CONSTRAINT event_member_user_event_pk_id_fk FOREIGN KEY (event_id) REFERENCES user_event (pk_id)
);
CREATE INDEX event_member_user_data_user_login_fk ON event_member (user_login);
CREATE INDEX event_member_user_event_pk_id_fk ON event_member (event_id);







