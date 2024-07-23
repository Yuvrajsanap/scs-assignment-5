
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc4 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc4 (name, email) VALUES ('yuvraj sanap', 'yuvraj@example.com');
INSERT INTO assjdbc4 (name, email) VALUES ('aniket', 'aniket@example.com');
INSERT INTO assjdbc4 (name, email) VALUES ('shubham', 'shubham@example.com');
INSERT INTO assjdbc4 (name, email) VALUES ('yash', 'yash@example.com');
select * from assjdbc4;