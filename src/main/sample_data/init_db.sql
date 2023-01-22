DROP TABLE IF EXISTS public.products CASCADE;
DROP TABLE IF EXISTS public.suppliers;
DROP TABLE IF EXISTS public.categories;


CREATE TABLE public.products (
                                 id serial NOT NULL PRIMARY KEY,
                                 name text NOT NULL,
                                 default_price decimal(5,3),
                                 currency_string text NOT NULL,
                                 description text,
                                 category_id integer NOT NULL,
                                 supplier_id integer NOT NULL
);

CREATE TABLE public.suppliers (
                                  id serial NOT NULL PRIMARY KEY,
                                  name text NOT NULL,
                                  description text NOT NULL
);

CREATE TABLE public.categories (
                                   id serial NOT NULL PRIMARY KEY,
                                   name text NOT NULL,
                                   department text NOT NULL,
                                   description text NOT NULL
);

