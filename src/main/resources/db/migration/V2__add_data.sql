INSERT INTO public.rights(name, description)
   	VALUES ('hotels', 'This is used for add or update a hotel details in application');
INSERT INTO public.rights(name, description)
   	VALUES ('add_users', 'This is used to add a user and his role in application');
INSERT INTO public.rights(name, description)
   	VALUES ('rooms', 'This is used to add or update the room details in a hotel');
INSERT INTO public.rights(name, description)
	VALUES ('reception', 'This is used for the one that made reservation for the new guests');
INSERT INTO public.rights(name, description)
	VALUES ('restaurant', 'This is used for the one that check the rooms numbers at the restaurant');
INSERT INTO public.rights(name, description)
   	VALUES ('clean', 'This is used for the one that clean the rooms');

INSERT INTO public.roles(name, description)
    VALUES ('Owner', 'This is used by the owner of the application and contains hotels and add_users rights');
INSERT INTO public.roles(name, description)
    VALUES ('Manager', 'This is used by the managers of a hotel and contains all rights except hotels right');
INSERT INTO public.roles(name, description)
    VALUES ('Reception member', 'This is used by the reception stuff of a hotel and contains reception right');
INSERT INTO public.roles(name, description)
    VALUES ('Restaurant member', 'This is used by the restaurant stuff of a hotel and contains restaurant right');
INSERT INTO public.roles(name, description)
    VALUES ('Clean personal', 'This is used by the clean stuff of a hotel and contains clean right');

INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (1, 1);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (1, 2);

INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 2);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 3);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 4);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 5);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (2, 6);

INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (3, 4);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (4, 5);
INSERT INTO public.roles_rights(role_id, rights_id)
    VALUES (5, 6);

INSERT INTO public.users(
	username, language, mail, password, roles_id)
	VALUES ('test', 'en', 'test@test.ro', '$2a$10$ntbZseyI0dHjzjagM5z7D.XBinX.nm1A3TrSfvvwrgI.eUEph38mS', 1);
