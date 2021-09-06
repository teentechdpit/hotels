CREATE TABLE public.refreshToken
(
    id SERIAL PRIMARY KEY,
    username varchar(255) NOT NULL,
    token varchar(255) NOT NULL,
    expire_date TIMESTAMP
);