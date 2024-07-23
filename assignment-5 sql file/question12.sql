
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc12 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);
select * from assjdbc12;