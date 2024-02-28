-- DELETE scripts

DELETE FROM user_role;
DELETE FROM role;
DELETE FROM user;



-- role table data

INSERT INTO role
    (id, name)
VALUES (1, 'ROLE_read'),
       (2, 'ROLE_editor'),
       (3, 'ROLE_admin');


-- client table data
-- password = secured

INSERT INTO user
    (id, name, username, password, address)
VALUES (1, 'Krishna Chaitanya', 'kcsurapaneni', '{bcrypt}$2a$10$Gnuih1/NZ36TXu.K3GEc.eseoIVxBgZyd0Xx5rbdP7.c18WwcPbDS', 'India'),
       (2, 'Vamsi Krishna', 'vkkolluri', '{bcrypt}$2a$10$Gnuih1/NZ36TXu.K3GEc.eseoIVxBgZyd0Xx5rbdP7.c18WwcPbDS', 'USA'),
       (3, 'Ramya Krishna', 'rkbodepudi', '{bcrypt}$2a$10$Gnuih1/NZ36TXu.K3GEc.eseoIVxBgZyd0Xx5rbdP7.c18WwcPbDS', 'Canada');


-- client_roles

INSERT INTO user_role
    (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 2),
       (3, 3);
