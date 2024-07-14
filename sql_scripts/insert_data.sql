USE soci;
INSERT INTO users (username, password, enabled) VALUES ('user', '{noop}password', 1);
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
