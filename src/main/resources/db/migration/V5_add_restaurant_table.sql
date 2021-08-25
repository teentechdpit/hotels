CREATE TABLE public.restaurant
(
    reservation_id int,
    last_breakfast_date DATE,
    last_lunch_date DATE,
    last_dinner_date DATE,

    CONSTRAINT hotel_room FOREIGN KEY (reservation_id)
                REFERENCES public.reservations (id)
                ON UPDATE NO ACTION,
    PRIMARY KEY (reservation_id)
);