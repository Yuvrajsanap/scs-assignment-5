
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc6 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc6 (name, email) VALUES ('yuvraj', 'sanap@example.com');
INSERT INTO assjdbc6 (name, email) VALUES ('sanket', 'sonwane@example.com');
INSERT INTO assjdbc6 (name, email) VALUES ('sahil', 'sahil@example.com');
INSERT INTO assjdbc6 (name, email) VALUES ('yash', 'suryavanshi@example.com');
select * from assjdbc6;