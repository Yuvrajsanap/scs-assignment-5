
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc8 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc8 (name, email) VALUES ('shambho', 'shambho@example.com');
INSERT INTO assjdbc8 (name, email) VALUES ('David', 'david@example.com');
INSERT INTO assjdbc8 (name, email) VALUES ('cristiano', 'cristiano@example.com');
INSERT INTO assjdbc8 (name, email) VALUES ('zlaton', 'zlaton@example.com');
select * from assjdbc8;