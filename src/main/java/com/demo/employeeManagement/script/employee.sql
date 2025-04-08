-- Table: public.employee

-- DROP TABLE IF EXISTS public.employee;

CREATE TABLE IF NOT EXISTS public.employee
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp(6) without time zone,
    deleted boolean,
    last_updated_by character varying(255) COLLATE pg_catalog."default",
    last_updated_on timestamp(6) without time zone,
    date_of_birth date,
    joining_date date,
    name character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    salary double precision,
    yearly_bonus_percentage double precision,
    address_id character varying(255) COLLATE pg_catalog."default",
    department_id character varying(255) COLLATE pg_catalog."default",
    reporting_manager_id character varying(255) COLLATE pg_catalog."default",
    manager_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT uk_qrbsk9ljmhfje93me0n7xwdxq UNIQUE (address_id),
    CONSTRAINT fkbejtwvg9bxus2mffsm3swj3u9 FOREIGN KEY (department_id)
        REFERENCES public.department (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkga73hdtpb67twlr9c1i337tyt FOREIGN KEY (address_id)
        REFERENCES public.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkn4ry427p0qpnybw914pylhhco FOREIGN KEY (reporting_manager_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkou6wbxug1d0qf9mabut3xqblo FOREIGN KEY (manager_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employee
    OWNER to postgres;