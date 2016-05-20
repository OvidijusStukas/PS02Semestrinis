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

CREATE TABLE IF NOT EXISTS item (
  id          INT NOT NULL AUTO_INCREMENT,
  asset_id    INT NOT NULL,
  group_id    INT NULL,
  count       INT NULL DEFAULT 0,
  name        NVARCHAR(255) NOT NULL,
  code        NVARCHAR(255) NOT NULL,
  description TEXT NULL,

  INDEX `ix__item__name` (`name`),
  INDEX `ix__item__asset_id` (`asset_id`),
  INDEX `ix__item__group_id` (`group_id`),

  CONSTRAINT `pk__item__id`       PRIMARY KEY (`id`),
  CONSTRAINT `fk__item__asset_id` FOREIGN KEY (`asset_id`) REFERENCES asset(`id`),
  CONSTRAINT `fk__item__group_id` FOREIGN KEY (`group_id`) REFERENCES item_group(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS item_picture (
  id       INT  NOT NULL AUTO_INCREMENT,
  item_id  INT  NOT NULL,
  data     TEXT NOT NULL,

  CONSTRAINT `pk__item_picture__id`      PRIMARY KEY (`id`),
  CONSTRAINT `fk__item_picture__item_id` FOREIGN KEY (`item_id`) REFERENCES item(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS asset_account_group (
  asset_id   INT NOT NULL,
  account_id INT NOT NULL,
  role       NVARCHAR(100) NOT NULL,

  UNIQUE INDEX `ix__asset_account_group` (`asset_id`, `account_id`),

  CONSTRAINT `fk__asset_account_group__asset_id` FOREIGN KEY (`asset_id`) REFERENCES asset(`id`),
  CONSTRAINT `fk__asset_account_group__account_id` FOREIGN KEY (`account_id`) REFERENCES account(`id`),
  CONSTRAINT `ck__asset_account_group__role` CHECK (`role` IN ('ROLE_ASSET_ADMIN', 'ROLE_ASSET_MODERATOR', 'ROLE _ASSET_USER'))
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

