CREATE TABLE public.entry_type
(
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE public.menu
(
    id SERIAL PRIMARY KEY,
    hotel_id int NOT NULL,
    name_of_entry varchar(255) NOT NULL,
    entry_type_id int NOT NULL,
    price int NOT NULL,
    currency varchar(20) NOT NULL,

    CONSTRAINT hotel FOREIGN KEY (hotel_id)
                REFERENCES public.hotels (id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,

    CONSTRAINT type FOREIGN KEY (entry_type_id)
                REFERENCES public.entry_type (id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
);

CREATE TABLE public.orders
(
    id SERIAL,
    reservation_id bigint NOT NULL,
    name_of_entry varchar(255) NOT NULL,
    number_of_pieces int NOT NULL,
    order_date TIMESTAMP NOT NULL,
    total_price int NOT NULL,
    currency varchar(15) NOT NULL,

    CONSTRAINT reservation FOREIGN KEY (reservation_id)
                REFERENCES public.reservations (id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,

    PRIMARY KEY (id, reservation_id)
);