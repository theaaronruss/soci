USE soci;
INSERT INTO users (username, first_name, last_name, password) VALUES ('user', 'Test', 'User', '{noop}password');
INSERT INTO roles (username, role) VALUES ('user', 'USER');
INSERT INTO posts (owner, content) values ('user', 'This is my first post!');
