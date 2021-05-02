delete from `User`;
delete from `User_doc`;
delete from `Country`;
delete from `Doc`;
delete from `Office`;
delete from `Organization`;

INSERT into Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (1, 0, 'Башнефть', 'ОАО "Башнефть"', '123456789012', '123456789', 'Уфа, Бакалинская 32', '834523451', TRUE);

INSERT into Organization (id, version, name, full_name, inn, kpp, address, is_active)
VALUES (2, 0, 'Лукойл', 'ОАО "Лукойл"', '123456789012', '123455555', 'Уфа, Менделеева 35', TRUE);

INSERT into Office (id, version, org_id, name, phone, address, is_active)
VALUES (1, 0, 1, 'АЗС Башнефть №1', '3423525235', 'Уфа, Сочинская 33/2', TRUE);

INSERT into Office (id, version, org_id, name, address)
VALUES (2, 0, 1, 'АЗС Башнефть №2', 'Уфа, Коммунистическая 77');

INSERT into Office (id, version, org_id, name, address)
VALUES (3, 0, 2, 'АЗС Лукойл №51', 'Пр.Октября 88');

INSERT into Office (id, version, org_id, name, address)
VALUES (4, 0, 2, 'АЗС Лукойл №34', 'Цурюпы 281');

INSERT into Office (id, version, org_id, name, address)
VALUES (5, 0, 2, 'АЗС Лукойл №483', 'Ленина 40');

INSERT into Doc (id, name, code) VALUES (1, 'Паспорт', '23');

INSERT into Doc (id, name, code) VALUES (2, 'Военный билет', '13');

INSERT into Doc (id, name, code) VALUES (3, 'Водительское удостоверение', '55');

INSERT into Country (id, citizenship_code, name) VALUES (1, '643', 'Российская Федерация');

INSERT into Country (id, citizenship_code, name) VALUES (2, '014', 'Украина');

INSERT into Country (id, citizenship_code, name) VALUES (3, '120', 'Франция');

INSERT into Country (id, citizenship_code, name) VALUES (4, '333', 'США');

INSERT into Country (id, citizenship_code, name) VALUES (5, '355', 'Финляндия');

INSERT into User_doc(id, version, doc_id, doc_number, doc_date) VALUES (1, 0, 1, '3451341', DATE '2004-12-31');

INSERT into User_doc(id, version, doc_id, doc_number, doc_date) VALUES (2, 0, 2, '342525', DATE '2005-10-04');

INSERT into User_doc(id, version, doc_id, doc_number, doc_date) VALUES (3, 0, 3, '123658', DATE '2006-11-07');

INSERT into User (id, version, first_name, second_name, middle_name, position, phone, user_doc_id, office_id, country_id, is_identified)
VALUES (1, 0, 'Олег', 'Иванов', 'Иванович', 'CEO', '3243423434', 1, 1, 1, TRUE);

INSERT into User (id, version, first_name, second_name, middle_name, position, phone, user_doc_id, office_id, country_id, is_identified)
VALUES (2, 0, 'Ivan', 'Olegov', 'Petrovich', 'manager', '2345212', 2, 2, 3, TRUE);

INSERT into User (id, version, first_name, second_name, middle_name, position, phone, user_doc_id, office_id, country_id, is_identified)
VALUES (3, 0, 'Antonina', 'Kozlova', 'Ivanovna', 'cleaner', '7777777', 3, 2, 2, TRUE);