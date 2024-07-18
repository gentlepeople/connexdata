CREATE DATABASE IF NOT EXISTS connexdatadb;
CREATE USER 'gentlepeople'@'%' IDENTIFIED BY '0000';
GRANT ALL PRIVILEGES ON connexdatadb.* TO 'gentlepeople'@'%';
FLUSH PRIVILEGES;
