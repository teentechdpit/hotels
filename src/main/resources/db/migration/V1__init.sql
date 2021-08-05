
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