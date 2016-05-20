CREATE TABLE IF NOT EXISTS account (
  id         INT           NOT NULL AUTO_INCREMENT,
  active     BIT           NULL DEFAULT 1,
  email      NVARCHAR(255) NOT NULL,
  password   NVARCHAR(128) NOT NULL,
  first_name NVARCHAR(128) NOT NULL,
  last_name  NVARCHAR(128) NOT NULL,
  role       NVARCHAR(128) NULL DEFAULT 'ROLE_USER',

  UNIQUE INDEX `ix__account__email` (`email`),

  CONSTRAINT `pk_account__id` PRIMARY KEY (`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS persistent_logins (
  series    NVARCHAR(64) NOT NULL,
  token     NVARCHAR(64) NOT NULL,
  username  NVARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,


  CONSTRAINT `pk__persistent_logins__series` PRIMARY KEY (`series`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS asset (
  id          INT           NOT NULL AUTO_INCREMENT,
  account_id  INT           NOT NULL,
  name        NVARCHAR(255) NOT NULL,
  code        NVARCHAR(6)   NOT NULL,
  description TEXT          NULL,
  address     NVARCHAR(512) NOT NULL,

  CONSTRAINT `pk__asset__id`         PRIMARY KEY (`id`),
  CONSTRAINT `fk__asset__account_id` FOREIGN KEY (`account_id`) REFERENCES account(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS item_group (
  id       INT           NOT NULL AUTO_INCREMENT,
  asset_id INT           NOT NULL,
  name     NVARCHAR(255) NOT NULL,

  CONSTRAINT `pk__item_group__id`       PRIMARY KEY (`id`),
  CONSTRAINT `fk__item_group__asset_id` FOREIGN KEY (`asset_id`) REFERENCES asset(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

