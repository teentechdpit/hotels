CREATE TABLE public.bar
(
    hotel_id bigint,
    room_number bigint,
    no_of_drinks int,

    CONSTRAINT hotel_room FOREIGN KEY (room_number, hotel_id)
                REFERENCES public.rooms (room_number, hotel_id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,

    PRIMARY KEY (room_number, hotel_id)
);