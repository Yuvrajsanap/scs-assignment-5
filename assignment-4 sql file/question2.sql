
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc2 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc2 (name, email) VALUES ('Yuvraj sanap', 'yuvraj.doe@example.com');
INSERT INTO assjdbc2 (name, email) VALUES ('aniket ugale', 'aniket.smith@example.com');
INSERT INTO assjdbc2 (name, email) VALUES ('jay ', 'jay.johnson@example.com');
INSERT INTO assjdbc2 (name, email) VALUES ('pankaj', 'pankaj.brown@example.com');
select * from assjdbc2;