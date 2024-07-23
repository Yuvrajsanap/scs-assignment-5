
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc9 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc9 (name, email) VALUES ('bently', 'bently@example.com');
INSERT INTO assjdbc9 (name, email) VALUES ('meni cooper', 'mini cooper@example.com');
INSERT INTO assjdbc9 (name, email) VALUES ('bmw', 'bmw@example.com');
INSERT INTO assjdbc9 (name, email) VALUES ('mazda', 'mazda@example.com');
select * from assjdbc9;