ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS userservice;
CREATE DATABASE IF NOT EXISTS orderservice;
CREATE DATABASE IF NOT EXISTS productservice;