--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.8
-- Dumped by pg_dump version 9.6.8

-- Started on 2018-04-03 18:26:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 37091)
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autor (
    aut_id character varying(20) NOT NULL,
    aut_nombre character varying(100) NOT NULL
);


ALTER TABLE public.autor OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 37143)
-- Name: contador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contador (
    id integer NOT NULL,
    valor bigint,
    nombre character varying(50)
);


ALTER TABLE public.contador OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 37096)
-- Name: editorial; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.editorial (
    edi_id character varying(20) NOT NULL,
    edi_nombre character varying(100) NOT NULL
);


ALTER TABLE public.editorial OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 36992)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 37083)
-- Name: material; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.material (
    mat_id character varying(20) NOT NULL,
    mat_cantidad numeric,
    mat_nombre character varying(200)
);


ALTER TABLE public.material OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 37101)
-- Name: libro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libro (
    aut_id character varying(20),
    edi_id character varying(20),
    lib_genero character varying(50)
)
INHERITS (public.material);


ALTER TABLE public.libro OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 37107)
-- Name: multimedia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.multimedia (
    mul_plataforma character varying(100) NOT NULL
)
INHERITS (public.material);


ALTER TABLE public.multimedia OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 37113)
-- Name: prestamo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prestamo (
    pre_id character varying(20) NOT NULL,
    usr_id character varying(20),
    mat_id character varying(20),
    pre_fecha_prestamo timestamp without time zone NOT NULL,
    pre_estado character varying(50),
    pre_fecha_devolucion timestamp without time zone,
    pre_multa numeric(10,2)
);


ALTER TABLE public.prestamo OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 37118)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    usr_id character varying(20) NOT NULL,
    usr_nombre character varying(100) NOT NULL,
    usr_identificacion character varying(20) NOT NULL,
    usr_email character varying(50),
    usr_tipo character varying(20)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 2167 (class 0 OID 37091)
-- Dependencies: 187
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2173 (class 0 OID 37143)
-- Dependencies: 193
-- Data for Name: contador; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contador VALUES (1, 8, 'USUARIO');


--
-- TOC entry 2168 (class 0 OID 37096)
-- Dependencies: 188
-- Data for Name: editorial; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 185
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 2169 (class 0 OID 37101)
-- Dependencies: 189
-- Data for Name: libro; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2166 (class 0 OID 37083)
-- Dependencies: 186
-- Data for Name: material; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2170 (class 0 OID 37107)
-- Dependencies: 190
-- Data for Name: multimedia; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2171 (class 0 OID 37113)
-- Dependencies: 191
-- Data for Name: prestamo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2172 (class 0 OID 37118)
-- Dependencies: 192
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario VALUES ('5', 'oo', 'iii', 'll', 'kk');
INSERT INTO public.usuario VALUES ('6', 'otro', '182828', 'eejfjfjjf', 'jdjd');
INSERT INTO public.usuario VALUES ('8', 'jkckkhc', 'kjsdkjfskj', 'kjkjfskhs', 'kjsdkjsfkj');


--
-- TOC entry 2043 (class 2606 OID 37147)
-- Name: contador contador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contador
    ADD CONSTRAINT contador_pkey PRIMARY KEY (id);


--
-- TOC entry 2035 (class 2606 OID 37095)
-- Name: autor pk_autor; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT pk_autor PRIMARY KEY (aut_id);


--
-- TOC entry 2037 (class 2606 OID 37100)
-- Name: editorial pk_editorial; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editorial
    ADD CONSTRAINT pk_editorial PRIMARY KEY (edi_id);


--
-- TOC entry 2033 (class 2606 OID 37090)
-- Name: material pk_material; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.material
    ADD CONSTRAINT pk_material PRIMARY KEY (mat_id);


--
-- TOC entry 2039 (class 2606 OID 37117)
-- Name: prestamo pk_prestamo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT pk_prestamo PRIMARY KEY (pre_id);


--
-- TOC entry 2041 (class 2606 OID 37122)
-- Name: usuario pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (usr_id);


--
-- TOC entry 2045 (class 2606 OID 37128)
-- Name: libro fk_libro_reference_autor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fk_libro_reference_autor FOREIGN KEY (aut_id) REFERENCES public.autor(aut_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2044 (class 2606 OID 37123)
-- Name: libro fk_libro_reference_editoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fk_libro_reference_editoria FOREIGN KEY (edi_id) REFERENCES public.editorial(edi_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2047 (class 2606 OID 37138)
-- Name: prestamo fk_prestamo_reference_material; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fk_prestamo_reference_material FOREIGN KEY (mat_id) REFERENCES public.material(mat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2046 (class 2606 OID 37133)
-- Name: prestamo fk_prestamo_reference_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fk_prestamo_reference_usuario FOREIGN KEY (usr_id) REFERENCES public.usuario(usr_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2018-04-03 18:26:36

--
-- PostgreSQL database dump complete
--

