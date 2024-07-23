
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc5 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc5 (name, email) VALUES ('jay', 'jay@example.com');
INSERT INTO assjdbc5 (name, email) VALUES ('karan', 'karan@example.com');
INSERT INTO assjdbc5 (name, email) VALUES ('mayank', 'mayank@example.com');
INSERT INTO assjdbc5 (name, email) VALUES ('sanket', 'sanket@example.com');
select * from assjdbc5;