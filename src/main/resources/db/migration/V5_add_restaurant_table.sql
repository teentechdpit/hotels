CREATE TABLE public.restaurant
(
    hotel_id bigint,
    room_number bigint,
    last_breakfast_date DATE,
    last_lunch_date DATE,
    last_dinner_date DATE,

    CONSTRAINT hotel_room FOREIGN KEY (room_number, hotel_id)
                REFERENCES public.rooms (room_number, hotel_id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,
    PRIMARY KEY (room_number, hotel_id)
);