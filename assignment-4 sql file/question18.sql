
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc18 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT
);
select * from assjdbc18;