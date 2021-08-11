
CREATE TABLE public.hotels
(
    id SERIAL PRIMARY KEY,
    city varchar(255) NOT NULL,
    country varchar(255) NOT NULL,
    mail varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    paid boolean NOT NULL,
    phone varchar(255) NOT NULL,
    stars integer NOT NULL
);

CREATE TABLE public.rights
(
    id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL,
    description varchar(255) NOT NULL
);

CREATE TABLE public.roles
(
    id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL,
    description varchar(255) NOT NULL
);

CREATE TABLE public.roles_rights
(
    role_id bigint NOT NULL,
    rights_id bigint NOT NULL,
    CONSTRAINT rights FOREIGN KEY (rights_id)
        REFERENCES public.rights (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT roles FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.users
(
    username varchar(255) PRIMARY KEY,
    language varchar(5) NOT NULL,
    mail varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    roles_id bigint,
    CONSTRAINT user_roles FOREIGN KEY (roles_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.rooms
(
    room_number bigint,
    hotel_id bigint,
    type varchar(255) NOT NULL,
    view varchar(255) NOT NULL,
    no_of_people int NOT NULL,
    CONSTRAINT hotel FOREIGN KEY (hotel_id)
                REFERENCES public.hotels (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,

    PRIMARY KEY (room_number, hotel_id)
);

CREATE TABLE public.reservations
(
    id SERIAL PRIMARY KEY,
    hotel_id bigint,
    room_number bigint,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL,
    passport_id varchar(31) NOT NULL,
    email varchar(255) NOT NULL,
    phone varchar(255) NOT NULL,
    breakfast boolean,
    lunch boolean,
    dinner boolean,

    CONSTRAINT hotel_room FOREIGN KEY (room_number, hotel_id)
            REFERENCES public.rooms (room_number, hotel_id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION

);

CREATE TABLE public.clean
(
    id SERIAL PRIMARY KEY,
    hotel_id bigint,
    room_number bigint,
    last_clean_day DATE NOT NULL,
    last_change_lingerie DATE NOT NULL,
    last_change_towels DATE NOT NULL,

    CONSTRAINT hotel_room FOREIGN KEY (room_number, hotel_id)
                REFERENCES public.rooms (room_number, hotel_id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
);

CREATE TABLE public.registration
(
    id SERIAL PRIMARY KEY,
    username varchar(255),
    UUID varchar(255) NOT NULL,
    CONSTRAINT FK_username FOREIGN KEY (username)
            REFERENCES public.users (username) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);