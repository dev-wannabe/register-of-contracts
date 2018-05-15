CREATE  TABLE IF NOT EXISTS users (
  username VARCHAR(45) PRIMARY KEY ,
  password VARCHAR(45) NOT NULL ,
  enabled BOOLEAN NOT NULL DEFAULT FALSE
  );

CREATE TABLE IF NOT EXISTS user_roles (
  user_role_id SERIAL,
  username varchar(45),
  role varchar(45) NOT NULL,
  UNIQUE (username, role),
  FOREIGN KEY (username) REFERENCES users (username)
  );
