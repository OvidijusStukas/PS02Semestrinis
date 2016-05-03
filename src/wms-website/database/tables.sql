CREATE TABLE IF NOT EXISTS contact_type (
  id    INT           NOT NULL AUTO_INCREMENT,
  name  NVARCHAR(200) NOT NULL,

  CONSTRAINT `pk_contact_type__id`   PRIMARY KEY (`id`),
  CONSTRAINT `uq_contact_type__name` UNIQUE (`name`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS contact_info (
  id    INT           NOT NULL AUTO_INCREMENT,
  type  INT           NOT NULL,
  value NVARCHAR(200) NOT NULL,

  CONSTRAINT `pk_contact_info__id`   PRIMARY KEY (`id`),
  CONSTRAINT `fk_contact_info__type` FOREIGN KEY (`type`) REFERENCES contact_type(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS picture (
  id   INT  NOT NULL AUTO_INCREMENT,
  data BLOB NOT NULL,

  CONSTRAINT `pk_picture_id` PRIMARY KEY (`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS account (
  id        INT           NOT NULL AUTO_INCREMENT,
  picture   INT           NULL,
  active    BIT           NOT NULL DEFAULT 0,
  email     NVARCHAR(200) NOT NULL,
  username  NVARCHAR(100) NOT NULL,
  password  NVARCHAR(100) NOT NULL,
  firstName NVARCHAR(100) NOT NULL,
  lastName  NVARCHAR(100) NOT NULL,
  role      NVARCHAR(100) NOT NULL,

  CONSTRAINT `pk_account__id`      PRIMARY KEY (`id`),
  CONSTRAINT `uq_account__email`   UNIQUE (`email`),
  CONSTRAINT `fk_account__picture` FOREIGN KEY (`picture`) REFERENCES picture(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS asset (
  id          INT           NOT NULL AUTO_INCREMENT,
  account     INT           NOT NULL,
  name        NVARCHAR(100) NOT NULL,
  description LONGTEXT      NOT NULL,
  type        NVARCHAR(100) NOT NULL,
  area        DECIMAL       NOT NULL,

  CONSTRAINT `pk_asset__id`     PRIMARY KEY (`id`),
  CONSTRAINT `fk_asset__acount` FOREIGN KEY (`account`) REFERENCES account(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS asset_contact_info (
  asset_id INT NOT NULL,
  contact_info INT NOT NULL,

  CONSTRAINT `fk_asset_contact_info__asset_id`        FOREIGN KEY (`asset_id`)     REFERENCES asset(`id`),
  CONSTRAINT `fk_asset_contact_info__contact_info_id` FOREIGN KEY (`contact_info`) REFERENCES contact_info(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS item (
  id          INT           NOT NULL AUTO_INCREMENT,
  count       INT           NOT NULL,
  asset       INT           NOT NULL,
  name        NVARCHAR(200) NOT NULL,
  description LONGTEXT      NOT NULL,
  type        NVARCHAR(100) NOT NULL,
  tags        LONGTEXT      NOT NULL,

  CONSTRAINT `pk_item__id`    PRIMARY KEY (`id`),
  CONSTRAINT `fk_item__asset` FOREIGN KEY (`asset`) REFERENCES asset(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS item_picture (
  item_id    INT NOT NULL,
  picture_id INT NOT NULL,

  CONSTRAINT `fk_item_picture__item_id`    FOREIGN KEY (`item_id`)    REFERENCES item(`id`),
  CONSTRAINT `fk_item_picture__picture_id` FOREIGN KEY (`picture_id`) REFERENCES picture(`id`)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;