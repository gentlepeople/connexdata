CREATE DATABASE IF NOT EXISTS sheetbridgedb;
CREATE USER 'gentlepeople'@'%' IDENTIFIED BY '0000';
GRANT ALL PRIVILEGES ON sheetbridgedb.* TO 'gentlepeople'@'%';
FLUSH PRIVILEGES;
