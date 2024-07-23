
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc7 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc7 (name, email) VALUES ('yuvraj', 'yuvraj@example.com');
INSERT INTO assjdbc7 (name, email) VALUES ('karan', 'karan@example.com');
INSERT INTO assjdbc7 (name, email) VALUES ('om', 'om@example.com');

select * from assjdbc7;