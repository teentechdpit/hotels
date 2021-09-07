ALTER TABLE public.reservations
ADD checkout bigint DEFAULT 0;

ALTER TABLE public.reservations
ADD currency varchar(15);

ALTER TABLE public.reservations
ADD checkout_completed boolean;