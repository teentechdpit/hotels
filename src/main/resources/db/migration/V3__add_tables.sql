CREATE TABLE public.rooms
(
    room_number bigint,
    hotel_id bigint,
    type varchar(255) NOT NULL,
    room_view varchar(255) NOT NULL,
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
    UUID varchar(255) NOT NULL PRIMARY KEY,
    username varchar(255),
    CONSTRAINT FK_username FOREIGN KEY (username)
            REFERENCES public.users (username) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);