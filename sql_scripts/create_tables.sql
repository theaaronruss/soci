CREATE DATABASE soci;
USE soci;
CREATE TABLE users (
	username VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    password VARCHAR(500) NOT NULL,
    PRIMARY KEY (username)
);
CREATE TABLE roles (
	username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);
CREATE UNIQUE INDEX ix_auth_username ON roles (username, role);
CREATE TABLE posts (
	id BIGINT NOT NULL AUTO_INCREMENT,
	owner VARCHAR(50) NOT NULL,
    content VARCHAR(300) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner) REFERENCES users(username)
);
