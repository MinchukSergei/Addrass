CREATE DATABASE IF NOT EXISTS addrass_db CHARACTER SET utf8;


CREATE TABLE user_icon
(
  pk_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  icon_bytes LONGBLOB NOT NULL
);
CREATE UNIQUE INDEX table_name_name_uindex ON user_icon (name);

CREATE TABLE user_group
(
  pk_id TINYINT(4) PRIMARY KEY NOT NULL,
  name VARCHAR(16) NOT NULL
);
CREATE UNIQUE INDEX addrass_group_name_uindex ON user_group (name);

CREATE TABLE event_type
(
  pk_id TINYINT(4) PRIMARY KEY NOT NULL,
  name VARCHAR(16) NOT NULL
);
CREATE UNIQUE INDEX event_type_name_uindex ON event_type (name);

CREATE TABLE user_color
(
  pk_id TINYINT(4) PRIMARY KEY NOT NULL,
  color INT(11) NOT NULL -- хранится в int, к примеру цвет FFFFFF = 16777215
);
CREATE UNIQUE INDEX user_color_color_uindex ON user_color (color);

CREATE TABLE user_data
(
  user_login VARCHAR(32),
  user_password VARCHAR(64),
  user_name VARCHAR(64) NOT NULL,
  user_phone_field VARCHAR(15),
  user_email_field VARCHAR(32),
  user_organization_field VARCHAR(32),
  user_address_field VARCHAR(32),
  user_notes_field VARCHAR(64),
  fk_user_photo BIGINT(20),
  pk_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  CONSTRAINT user_data_user_icon_pk_id_fk FOREIGN KEY (fk_user_photo) REFERENCES user_icon (pk_id)
);
CREATE INDEX user_data_user_icon_pk_id_fk ON user_data (fk_user_photo);
CREATE UNIQUE INDEX user_data_user_login_uindex ON user_data (user_login);

CREATE TABLE user_event
(
  pk_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  fk_event_owner BIGINT(20) NOT NULL,
  event_datetime TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
  fk_event_type TINYINT(4) NOT NULL,
  event_coordinate_x DOUBLE,
  event_coordinate_y DOUBLE,
  CONSTRAINT user_event_event_type_pk_id_fk FOREIGN KEY (fk_event_type) REFERENCES event_type (pk_id),
  CONSTRAINT user_event_user_data_pk_id_fk FOREIGN KEY (fk_event_owner) REFERENCES user_data (pk_id)
);
CREATE INDEX user_event_event_type_pk_id_fk ON user_event (fk_event_type);
CREATE INDEX user_event_user_data_pk_id_fk ON user_event (fk_event_owner);

CREATE TABLE friend_list
(
  pk_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  fk_user_main BIGINT(20) NOT NULL,
  fk_user_friend BIGINT(20) NOT NULL,
  black_list TINYINT(1) DEFAULT '0' NOT NULL,
  fk_user_color TINYINT(4) NOT NULL,
  fk_user_group TINYINT(4) NOT NULL,
  CONSTRAINT friend_list_user_color_pk_id_fk FOREIGN KEY (fk_user_color) REFERENCES user_color (pk_id),
  CONSTRAINT friend_list_user_friend_pk_id_fk FOREIGN KEY (fk_user_friend) REFERENCES user_data (pk_id),
  CONSTRAINT friend_list_user_group_pk_id_fk FOREIGN KEY (fk_user_group) REFERENCES user_group (pk_id),
  CONSTRAINT friend_list_user_main__fk FOREIGN KEY (fk_user_main) REFERENCES user_data (pk_id)
);
CREATE INDEX friend_list_user_color_pk_id_fk ON friend_list (fk_user_color);
CREATE INDEX friend_list_user_friend_pk_id_fk ON friend_list (fk_user_friend);
CREATE INDEX friend_list_user_group_pk_id_fk ON friend_list (fk_user_group);
CREATE INDEX friend_list_user_main__fk ON friend_list (fk_user_main);

CREATE TABLE contact_list
(
  pk_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  fk_user_main BIGINT(20) NOT NULL,
  fk_user_friend BIGINT(20) NOT NULL,
  fk_user_color TINYINT(4) NOT NULL,
  CONSTRAINT contact_list_user_color_pk_id_fk FOREIGN KEY (fk_user_color) REFERENCES user_color (pk_id),
  CONSTRAINT contact_list_user_friend__fk FOREIGN KEY (fk_user_friend) REFERENCES user_data (pk_id),
  CONSTRAINT contact_list_user_main_pk_id_fk FOREIGN KEY (fk_user_main) REFERENCES user_data (pk_id)
);
CREATE INDEX contact_list_user_color_pk_id_fk ON contact_list (fk_user_color);
CREATE INDEX contact_list_user_friend__fk ON contact_list (fk_user_friend);
CREATE INDEX contact_list_user_main_pk_id_fk ON contact_list (fk_user_main);

CREATE TABLE event_member
(
  pk_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  fk_event_id BIGINT(20) NOT NULL,
  fk_user_id BIGINT(20) NOT NULL,
  CONSTRAINT event_member_user_data_pk_id_fk FOREIGN KEY (fk_user_id) REFERENCES user_data (pk_id),
  CONSTRAINT event_member_user_event_pk_id_fk FOREIGN KEY (fk_event_id) REFERENCES user_event (pk_id)
);
CREATE INDEX event_member_user_data_pk_id_fk ON event_member (fk_user_id);
CREATE INDEX event_member_user_event_pk_id_fk ON event_member (fk_event_id);