ALTER TABLE public.users
DROP CONSTRAINT user_roles;
ALTER TABLE public.users
ADD CONSTRAINT user_roles FOREIGN KEY (role_id)
    REFERENCES public.roles (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION;