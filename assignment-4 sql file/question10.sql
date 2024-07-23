
CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE assjdbc10 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO assjdbc10 (name, email) VALUES ('rohit sharma', 'rohit@example.com');
INSERT INTO assjdbc10 (name, email) VALUES ('virat', 'virat.jefferson@example.com');
INSERT INTO assjdbc10 (name, email) VALUES ('bumrah', 'bumrah@example.com');
INSERT INTO assjdbc10 (name, email) VALUES ('sky', 'sky@example.com');
select * from assjdbc10;
