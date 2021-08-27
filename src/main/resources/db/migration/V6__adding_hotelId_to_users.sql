ALTER TABLE public.users
ADD hotel_id bigint;
UPDATE public.users
SET hotel_id=1;
ALTER TABLE public.users
ALTER COLUMN hotel_id SET NOT NULL;