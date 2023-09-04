-- public.customer definition

-- Drop table

-- DROP TABLE public.customer;

CREATE TABLE public.customer (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);


-- public.inventory definition

-- Drop table

-- DROP TABLE public.inventory;

CREATE TABLE public.inventory (
	price float8 NOT NULL,
	quantity int4 NOT NULL,
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT inventory_pkey PRIMARY KEY (id)
);


-- public.payment_type definition

-- Drop table

-- DROP TABLE public.payment_type;

CREATE TABLE public.payment_type (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT payment_type_pkey PRIMARY KEY (id)
);


-- public.payment definition

-- Drop table

-- DROP TABLE public.payment;

CREATE TABLE public.payment (
	amount float8 NOT NULL,
	customer_id int8 NULL,
	"date" timestamp(6) NULL,
	id bigserial NOT NULL,
	payment_type_id int8 NULL,
	CONSTRAINT payment_pkey PRIMARY KEY (id),
	CONSTRAINT fkby2skjf3ov608yb6nm16b49lg FOREIGN KEY (customer_id) REFERENCES public.customer(id),
	CONSTRAINT fkkvolsaw3e4jg4ra05vu135cj9 FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id)
);


-- public.payment_inventory definition

-- Drop table

-- DROP TABLE public.payment_inventory;

CREATE TABLE public.payment_inventory (
	quantity int4 NOT NULL,
	total_price float8 NOT NULL,
	id bigserial NOT NULL,
	inventory_id int8 NOT NULL,
	payment_id int8 NOT NULL,
	CONSTRAINT payment_inventory_pkey PRIMARY KEY (id),
	CONSTRAINT fk184ulibie3md9bko612y7jfra FOREIGN KEY (inventory_id) REFERENCES public.inventory(id),
	CONSTRAINT fkh9xkcjs5sad718mcs1ubml0r5 FOREIGN KEY (payment_id) REFERENCES public.payment(id)
);