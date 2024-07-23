
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc11 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc11 (name, email) VALUES ('yuvraj sanap', 'yuvraj@example.com');
INSERT INTO assjdbc11 (name, email) VALUES ('harsh darade', 'harsh@example.com');
select * from assjdbc11;