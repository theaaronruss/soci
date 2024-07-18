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
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (username, role),
    FOREIGN KEY (username) REFERENCES users(username)
);
CREATE TABLE posts (
	id BIGINT NOT NULL AUTO_INCREMENT,
	owner VARCHAR(50) NOT NULL,
    content VARCHAR(300) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner) REFERENCES users(username)
);
CREATE TABLE followers (
	username VARCHAR(50) NOT NULL,
    following VARCHAR(50) NOT NULL,
    PRIMARY KEY (username, following)
);
