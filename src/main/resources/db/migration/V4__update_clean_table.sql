DROP table public.clean;
CREATE TABLE public.clean
(
    hotel_id bigint,
    room_number bigint,
    last_clean_day DATE NOT NULL,
    last_change_lingerie DATE NOT NULL,
    last_change_towels DATE NOT NULL,

    CONSTRAINT hotel_room FOREIGN KEY (room_number, hotel_id)
                REFERENCES public.rooms (room_number, hotel_id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,
    PRIMARY KEY (room_number, hotel_id)
);