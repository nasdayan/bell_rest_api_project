CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
    name       VARCHAR(50) NOT NULL     COMMENT 'Название организации',
    full_name  VARCHAR(50) NOT NULL     COMMENT 'Полное название организации',
    inn        VARCHAR(12) NOT NULL     COMMENT 'ИНН организации',
    kpp        VARCHAR(9) NOT NULL      COMMENT 'КПП организации',
    address    VARCHAR(100) NOT NULL    COMMENT 'Адрес организации',
    phone      VARCHAR(20)              COMMENT 'Телефон организации',
    is_active  BOOLEAN                  COMMENT 'Статус активности организации'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
      id         INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
      version    INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
      org_id     INTEGER                  COMMENT 'Внешний ключ к таблице organization',
      name       VARCHAR(100) NOT NULL    COMMENT 'Название офиса',
      phone      VARCHAR(50)              COMMENT 'Телефон офиса',
      address    VARCHAR(100) NOT NULL    COMMENT 'Адрес офиса',
      is_active  BOOLEAN                  COMMENT 'Статус активности офиса'
);
COMMENT ON TABLE Office IS 'Офис';

create table User (
      id            INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
      version       INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
      first_name    VARCHAR(50) NOT NULL     COMMENT 'Имя пользователя',
      second_name   VARCHAR(50)              COMMENT 'Фамилия пользователя',
      middle_name   VARCHAR(50)              COMMENT 'Отчество пользователя',
      position      VARCHAR(50) NOT NULL     COMMENT 'Должность пользователя',
      phone         VARCHAR(20)              COMMENT 'Номер телефона пользователя',
      user_doc_id   INTEGER                  COMMENT 'Внешний ключ к таблице user_doc',
      office_id     INTEGER                  COMMENT 'Внешний ключ к таблице office',
      country_id    INTEGER                  COMMENT 'Внешний ключ к таблице country',
      is_identified BOOLEAN                  COMMENT 'Статус идентификации пользователя'
);
COMMENT ON TABLE Office IS 'Пользователь';

CREATE table Doc (
     id         INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
     name       VARCHAR(50) NOT NULL     COMMENT 'Название документа',
     code       VARCHAR(2)  NOT NULL     COMMENT 'Код документа'
);
COMMENT ON TABLE Doc IS 'Справочник документов';

CREATE table User_doc (
      id           INTEGER             COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
      version      INTEGER NOT NULL    COMMENT 'Служебное поле hibernate',
      doc_id       INTEGER             COMMENT 'Внешний ключ к таблице doc',
      doc_number   VARCHAR(50)         COMMENT 'Номер документа',
      doc_date     DATE                COMMENT 'Дата выдачи документа'
);
COMMENT ON TABLE User_doc IS 'Документ пользователя';

CREATE table Country (
     id                     INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
     citizenship_code       VARCHAR(3) NOT NULL      COMMENT 'Код страны',
     name                   VARCHAR(50) NOT NULL     COMMENT 'Название страны'
);
COMMENT ON TABLE Country IS 'Справочник стран';

ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);
CREATE INDEX IX_Office_org_id on Office(org_id);

ALTER TABLE User add foreign key (user_doc_id) references User_doc(id);
CREATE INDEX IX_User_user_doc_id on User(user_doc_id);

ALTER TABLE User add foreign key (office_id) references Office(id);
CREATE INDEX IX_User_office_id on User(office_id);

ALTER TABLE User add foreign key (country_id) references Country(id);
CREATE INDEX IX_User_country_id on User(country_id);

ALTER TABLE User_doc add foreign key (doc_id) references Doc(id);
CREATE INDEX IX_user_doc_doc_id on User_doc(doc_id);
