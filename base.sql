PGDMP                         v        
   Biblioteca    9.6.8    9.6.8 $    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    16396 
   Biblioteca    DATABASE     �   CREATE DATABASE "Biblioteca" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE "Biblioteca";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    37091    autor    TABLE     y   CREATE TABLE public.autor (
    aut_id character varying(20) NOT NULL,
    aut_nombre character varying(100) NOT NULL
);
    DROP TABLE public.autor;
       public         postgres    false    3            �            1259    37143    contador    TABLE     n   CREATE TABLE public.contador (
    id integer NOT NULL,
    valor bigint,
    nombre character varying(50)
);
    DROP TABLE public.contador;
       public         postgres    false    3            �            1259    37096 	   editorial    TABLE     }   CREATE TABLE public.editorial (
    edi_id character varying(20) NOT NULL,
    edi_nombre character varying(100) NOT NULL
);
    DROP TABLE public.editorial;
       public         postgres    false    3            �            1259    36992    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    3            �            1259    37083    material    TABLE     �   CREATE TABLE public.material (
    mat_id character varying(20) NOT NULL,
    mat_cantidad numeric,
    mat_nombre character varying(200)
);
    DROP TABLE public.material;
       public         postgres    false    3            �            1259    37101    libro    TABLE     �   CREATE TABLE public.libro (
    aut_id character varying(20),
    edi_id character varying(20),
    lib_genero character varying(50)
)
INHERITS (public.material);
    DROP TABLE public.libro;
       public         postgres    false    3    186            �            1259    37107 
   multimedia    TABLE     r   CREATE TABLE public.multimedia (
    mul_plataforma character varying(100) NOT NULL
)
INHERITS (public.material);
    DROP TABLE public.multimedia;
       public         postgres    false    3    186            �            1259    37113    prestamo    TABLE     F  CREATE TABLE public.prestamo (
    pre_id character varying(20) NOT NULL,
    usr_id character varying(20),
    mat_id character varying(20),
    pre_fecha_prestamo timestamp without time zone NOT NULL,
    pre_estado character varying(50),
    pre_fecha_devolucion timestamp without time zone,
    pre_multa numeric(10,2)
);
    DROP TABLE public.prestamo;
       public         postgres    false    3            �            1259    37118    usuario    TABLE     �   CREATE TABLE public.usuario (
    usr_id character varying(20) NOT NULL,
    usr_nombre character varying(100) NOT NULL,
    usr_identificacion character varying(20) NOT NULL,
    usr_email character varying(50),
    usr_tipo character varying(20)
);
    DROP TABLE public.usuario;
       public         postgres    false    3            w          0    37091    autor 
   TABLE DATA               3   COPY public.autor (aut_id, aut_nombre) FROM stdin;
    public       postgres    false    187   '       }          0    37143    contador 
   TABLE DATA               5   COPY public.contador (id, valor, nombre) FROM stdin;
    public       postgres    false    193   '       x          0    37096 	   editorial 
   TABLE DATA               7   COPY public.editorial (edi_id, edi_nombre) FROM stdin;
    public       postgres    false    188   G'       �           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);
            public       postgres    false    185            y          0    37101    libro 
   TABLE DATA               ]   COPY public.libro (mat_id, mat_cantidad, mat_nombre, aut_id, edi_id, lib_genero) FROM stdin;
    public       postgres    false    189   d'       v          0    37083    material 
   TABLE DATA               D   COPY public.material (mat_id, mat_cantidad, mat_nombre) FROM stdin;
    public       postgres    false    186   �'       z          0    37107 
   multimedia 
   TABLE DATA               V   COPY public.multimedia (mat_id, mat_cantidad, mat_nombre, mul_plataforma) FROM stdin;
    public       postgres    false    190   �'       {          0    37113    prestamo 
   TABLE DATA               {   COPY public.prestamo (pre_id, usr_id, mat_id, pre_fecha_prestamo, pre_estado, pre_fecha_devolucion, pre_multa) FROM stdin;
    public       postgres    false    191   �'       |          0    37118    usuario 
   TABLE DATA               ^   COPY public.usuario (usr_id, usr_nombre, usr_identificacion, usr_email, usr_tipo) FROM stdin;
    public       postgres    false    192   �'       �           2606    37147    contador contador_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.contador
    ADD CONSTRAINT contador_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.contador DROP CONSTRAINT contador_pkey;
       public         postgres    false    193    193            �           2606    37095    autor pk_autor 
   CONSTRAINT     P   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT pk_autor PRIMARY KEY (aut_id);
 8   ALTER TABLE ONLY public.autor DROP CONSTRAINT pk_autor;
       public         postgres    false    187    187            �           2606    37100    editorial pk_editorial 
   CONSTRAINT     X   ALTER TABLE ONLY public.editorial
    ADD CONSTRAINT pk_editorial PRIMARY KEY (edi_id);
 @   ALTER TABLE ONLY public.editorial DROP CONSTRAINT pk_editorial;
       public         postgres    false    188    188            �           2606    37090    material pk_material 
   CONSTRAINT     V   ALTER TABLE ONLY public.material
    ADD CONSTRAINT pk_material PRIMARY KEY (mat_id);
 >   ALTER TABLE ONLY public.material DROP CONSTRAINT pk_material;
       public         postgres    false    186    186            �           2606    37117    prestamo pk_prestamo 
   CONSTRAINT     V   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT pk_prestamo PRIMARY KEY (pre_id);
 >   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT pk_prestamo;
       public         postgres    false    191    191            �           2606    37122    usuario pk_usuario 
   CONSTRAINT     T   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (usr_id);
 <   ALTER TABLE ONLY public.usuario DROP CONSTRAINT pk_usuario;
       public         postgres    false    192    192            �           2606    37128    libro fk_libro_reference_autor    FK CONSTRAINT     �   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fk_libro_reference_autor FOREIGN KEY (aut_id) REFERENCES public.autor(aut_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 H   ALTER TABLE ONLY public.libro DROP CONSTRAINT fk_libro_reference_autor;
       public       postgres    false    189    2035    187            �           2606    37123 !   libro fk_libro_reference_editoria    FK CONSTRAINT     �   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fk_libro_reference_editoria FOREIGN KEY (edi_id) REFERENCES public.editorial(edi_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 K   ALTER TABLE ONLY public.libro DROP CONSTRAINT fk_libro_reference_editoria;
       public       postgres    false    2037    189    188            �           2606    37138 '   prestamo fk_prestamo_reference_material    FK CONSTRAINT     �   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fk_prestamo_reference_material FOREIGN KEY (mat_id) REFERENCES public.material(mat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 Q   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT fk_prestamo_reference_material;
       public       postgres    false    186    2033    191            �           2606    37133 &   prestamo fk_prestamo_reference_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fk_prestamo_reference_usuario FOREIGN KEY (usr_id) REFERENCES public.usuario(usr_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 P   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT fk_prestamo_reference_usuario;
       public       postgres    false    2041    191    192            w      x������ � �      }      x�3�4�u�������  NU      x      x������ � �      y      x������ � �      v      x������ � �      z      x������ � �      {      x������ � �      |   6   x�3��������������2��/)��4�0B��Ԭ4 �J��J�J����� XVx     