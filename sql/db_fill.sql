insert into manager (name, office) values ('Dzmitry Prus', 'Mazyr' );
insert into client (name, data, type_id) values ('Isaak Newton', '+375295669575', 1 );
insert into client (name, data, type_id) values ('Blez Paskal', '+375296515575', 1 );
insert into client (name, data, type_id) values ('Nicola Tesla', '+375295112275', 1 );
insert into client (name, data, type_id) values ('Maria Kyuri', '+375296998732', 1 );
insert into client (name, data, type_id) values ('OOO "Raduga Vkusa"', '+3752973805569', 2 );
insert into client (name, data, type_id) values ('Homel Office', '+375332335689', 3 );

insert into user (login,password,email,role, client_id, manager_id) values ('rewq','4321','prusco@mail.ru','MANAGER',null, 1);
insert into user (login,password,email,role, client_id, manager_id) values ('qwer','1234','newton@mail.ru','CLIENT',1, null);
insert into user (login,password,email,role, client_id, manager_id) values ('newton','newton','newton@mail.ru','CLIENT',1, null);
insert into user (login,password,email,role, client_id, manager_id) values ('pascal','pascal','pascal@mail.ru','CLIENT',2, null);
insert into user (login,password,email,role, client_id, manager_id) values ('tesla','tesla','tesla@mail.ru','CLIENT',3, null);
insert into user (login,password,email,role, client_id, manager_id) values ('kyuri','kyuri','kyuri@mail.ru','CLIENT',4, null);
insert into user (login,password,email,role, client_id, manager_id) values ('raduga','raduga','raduga@mail.ru','CLIENT',5, null);
insert into user (login,password,email,role, client_id, manager_id) values ('homel','homel','homel@sdek.by','CLIENT',6, null);

insert into `order` (`pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm, weight_kg, date, isactive, price, ordercol, truck_id, manager_id, client_id)
        values ( 'Krasnoarmeyskaya 4', 'HOMEL',   'MAZYR',      'Druczhby 5',       100,       120,      80,        120,    2021-02-02, true,  114.12, null,     null,     null,      1);
insert into `order` (`pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm, weight_kg, date, isactive, price, ordercol, truck_id, manager_id, client_id)
        values ( 'Pushkina 6',         'BREST',   'GORKY',      'Sovetskaya 4',     115,       65,       25,        55,     2021-01-25, true,  56.12, null,     null,     null,       2);
insert into `order` (`pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm, weight_kg, date, isactive, price, ordercol, truck_id, manager_id, client_id)
        values ( 'Pobedy 12',         'VITEBSK',  'HOMEL',      'Druczhby 5',       56,        45,       31,        115,    2021-01-29, true,  34.19, null,     null,     null,       1);
insert into `order` (`pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm, weight_kg, date, isactive, price, ordercol, truck_id, manager_id, client_id)
        values ( 'Olshevskogo 7/2',   'MINSK',    'BREST',      'Druczhby 5',       99,        66,       50,        114.4,  2021-01-11, true,  120.33,null,     null,     null,       4);
insert into `order` (`pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm, weight_kg, date, isactive, price, ordercol, truck_id, manager_id, client_id)
        values ( null,                'SLUTSK',   'MINSK',        null,             150,       114,      15,        113.2,  2021-01-13, true,  115.22, null,    null,     null,       3);
