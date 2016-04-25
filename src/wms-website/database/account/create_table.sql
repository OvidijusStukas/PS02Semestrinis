DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id INT NOT NULL AUTO_INCREMENT,
  enabled BIT NOT NULL DEFAULT 1,
  first_name NVARCHAR(100) NOT NULL,
  last_name NVARCHAR(100) NOT NULL,
  email NVARCHAR(200) NOT NULL,
  password NVARCHAR(100) NOT NULL,
  user_role NVARCHAR(100) NOT NULL,

  CONSTRAINT `user_id` PRIMARY KEY (`user_id`),
  CONSTRAINT `user_email_uq` UNIQUE KEY (`email`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;