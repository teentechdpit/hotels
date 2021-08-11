INSERT INTO public.rights(name, description)
	VALUES ('reception', 'This is used for the one that made reservation for the new guests');
INSERT INTO public.rights(name, description)
   	VALUES ('clean', 'This is used for the one that clean the rooms');
INSERT INTO public.rights(name, description)
	VALUES ('restaurant', 'This is used for the one that check the rooms numbers at the restaurant');
INSERT INTO public.rights(name, description)
   	VALUES ('rooms', 'This is used to add or update the room details in a hotel');
INSERT INTO public.rights(name, description)
   	VALUES ('hotels', 'This is used for add or update a hotel details in application');

INSERT INTO public.roles(name, description)
    VALUES ('Owner', 'This is used by the owner of the application and contains all available rights');
INSERT INTO public.roles(name, description)
    VALUES ('Manager', 'This is used by the managers of a hotel');
INSERT INTO public.roles(name, description)
    VALUES ('Clean personal', 'This is used by the clean stuff of a hotel');
INSERT INTO public.roles(name, description)
    VALUES ('Reception member', 'This is used by the reception stuff of a hotel');
INSERT INTO public.roles(name, description)
    VALUES ('Restaurant member', 'This is used by the restaurant stuff of a hotel');

INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (1, 1);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (1, 2);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (1, 3);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (1, 4);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (1, 5);

INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 1);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 2);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 3);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 4);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (3, 2);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (4, 1);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (5, 3);

INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (4, 1);

INSERT INTO public.hotels(city, country, mail, name, paid, phone, stars)
   VALUES ('Cluj-Napoca', 'Romania', 'test@test.ro', 'test', true, '0700000000', 5);

INSERT INTO public.rooms(room_number, hotel_id, type, view, no_of_people)
    VALUES (243, 1, 'double', 'sea', 2);

INSERT INTO public.users(
	username, language, mail, password, roles_id)
	VALUES ('test', 'en', 'test@test.ro', '$2a$10$ntbZseyI0dHjzjagM5z7D.XBinX.nm1A3TrSfvvwrgI.eUEph38mS', 1);
