USE soci;
INSERT INTO users (username, first_name, last_name, password) VALUES ('user', 'Test', 'User', '{noop}password');
INSERT INTO authorities (username, authority) VALUES ('user', 'USER');
