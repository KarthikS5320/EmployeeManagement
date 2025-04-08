-- Table: public.address

-- DROP TABLE IF EXISTS public.address;

CREATE TABLE IF NOT EXISTS public.address
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL DEFAULT nextval('address_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp(6) without time zone,
    deleted boolean,
    last_updated_by character varying(255) COLLATE pg_catalog."default",
    last_updated_on timestamp(6) without time zone,
    building character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default",
    country character varying(255) COLLATE pg_catalog."default",
    pincode character varying(255) COLLATE pg_catalog."default",
    state character varying(255) COLLATE pg_catalog."default",
    street character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT address_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.address
    OWNER to postgres;