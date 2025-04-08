-- Table: public.department

-- DROP TABLE IF EXISTS public.department;

CREATE TABLE IF NOT EXISTS public.department
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp(6) without time zone,
    deleted boolean,
    last_updated_by character varying(255) COLLATE pg_catalog."default",
    last_updated_on timestamp(6) without time zone,
    creation_date date,
    name character varying(255) COLLATE pg_catalog."default",
    department_head_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT department_pkey PRIMARY KEY (id),
    CONSTRAINT fkrfh61ugk9mpwmie0yu6ou0oxq FOREIGN KEY (department_head_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.department
    OWNER to postgres;