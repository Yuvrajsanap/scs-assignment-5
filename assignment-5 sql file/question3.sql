-- Create the testdb database if not exists
CREATE DATABASE IF NOT EXISTS testdb;

-- Use the testdb database
USE testdb;

-- Create the assjdbc4 table
CREATE TABLE assjdbc4 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Insert sample data into the assjdbc4 table
INSERT INTO assjdbc4 (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO assjdbc4 (name, email) VALUES ('Jane Smith', 'jane.smith@example.com');
INSERT INTO assjdbc4 (name, email) VALUES ('Alice Johnson', 'alice.johnson@example.com');
INSERT INTO assjdbc4 (name, email) VALUES ('Bob Brown', 'bob.brown@example.com');
