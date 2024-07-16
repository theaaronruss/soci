USE soci;
INSERT INTO users (username, first_name, last_name, password) VALUES ('mjones', 'Michael', 'Jones', '{noop}password');
INSERT INTO users (username, first_name, last_name, password) VALUES ('pwhite', 'Peggy', 'White', '{noop}password');
INSERT INTO roles (username, role) VALUES ('mjones', 'USER');
INSERT INTO roles (username, role) VALUES ('pwhite', 'USER');
INSERT INTO posts (owner, content) VALUES ('mjones', 'This is my first post!');
INSERT INTO posts (owner, content) VALUES ('mjones', 'This is my second post, still going!');
INSERT INTO posts (owner, content) VALUES ('pwhite', 'Who saw the game on Friday?');
