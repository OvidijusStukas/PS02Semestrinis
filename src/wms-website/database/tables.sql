CREATE TABLE IF NOT EXISTS account (
  id         INT           NOT NULL AUTO_INCREMENT,
  active     BIT           NOT NULL DEFAULT 1,
  email      NVARCHAR(255) NOT NULL,
  password   NVARCHAR(128) NOT NULL,
  first_name NVARCHAR(128) NOT NULL,
  last_name  NVARCHAR(128) NOT NULL,
  role       NVARCHAR(128) NOT NULL,

  UNIQUE INDEX `ix__account__email` (`email`),

  CONSTRAINT `pk_account__id` PRIMARY KEY (`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
