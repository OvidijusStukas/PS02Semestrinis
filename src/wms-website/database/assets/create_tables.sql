CREATE TABLE Asset_Types
(
	TypeNr int NOT NULL,
	TypeName varchar(200) NOT NULL,
	PRIMARY KEY (TypeNr)
);

CREATE TABLE Assets_Assets
(
	AssetNr int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name varchar(200) NOT NULL DEFAULT '',
	TypeNr int NOT NULL,
	Adress varchar(800) NOT NULL DEFAULT '',
	FOREIGN KEY (TypeNr) REFERENCES Asset_Types(TypeNr)
);