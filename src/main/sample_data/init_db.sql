DROP TABLE IF EXISTS public.supplier CASCADE ;
CREATE TABLE public.supplier (
                                 id serial NOT NULL PRIMARY KEY,
                                 supplier_name text,
                                 supplier_description text
);


DROP TABLE IF EXISTS public.category CASCADE ;
CREATE TABLE public.category (
                                 id serial NOT NULL PRIMARY KEY,
                                 category_name text,
                                 department text,
                                 category_description text
);



ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
DROP TABLE IF EXISTS public.products CASCADE;
CREATE TABLE public.products (
                                 id serial NOT NULL PRIMARY KEY,
                                 product_name text,
                                 price numeric,
                                 currency text,
                                 product_description text,
                                 category_id integer NOT NULL,
                                 supplier_id integer NOT NULL
);


DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users (
                              id serial NOT NULL PRIMARY KEY,
                              name text,
                              email varchar(100),
                              password varchar (100)
);


DROP TABLE IF EXISTS public.order CASCADE;
CREATE TABLE public.order (
                              id serial NOT NULL PRIMARY KEY,
                              order_id text,
                              first_name text,
                              last_name text,
                              email text,
                              address text,
                              country text,
                              city text,
                              total_amount numeric,
                              order_complete boolean
);

ALTER TABLE IF EXISTS ONLY public.shopping_cart DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.shopping_cart DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
DROP TABLE IF EXISTS public.shopping_cart CASCADE;
CREATE TABLE public.shopping_cart (
                                      id serial NOT NULL PRIMARY KEY,
                                      user_id integer NOT NULL,
                                      product_id integer NOT NULL,
                                      amount integer
);


ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category(id);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);

ALTER TABLE ONLY public.shopping_cart
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.shopping_cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.products(id);