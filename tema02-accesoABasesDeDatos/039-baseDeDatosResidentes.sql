-- Crea una base de datos llamada "residentes", con:

-- Una tabla "personas", con código ("cod", clave primaria), nombre ("nombre") 
--   y apellidos ("apell").
-- Una tabla "paises", con código ("cod", clave primaria) y nombre ("nombre").
-- Una relación M:M "residir", con atributos adicionales "desde" y "hasta".

-- Datos:

-- José Pérez, vivió en Alemania del 01-02-2015 al 23-12-2015
-- Juan López, vivió en Francia del 01-03-2016 al 07-03-2016 
--   y en Holanda del 05-05-2016 al 08-09-2016
-- Jesús Martínez, vivió en Alemania del 01-05-2014 al 06-08-2015

-- Consultas:

-- Personas cuyo nombre empieza por J.
-- Personas que han vivido en Alemania.
-- Cantidad de personas que han vivido en países cuyo nombre acabe en "a".
-- Apellido de las personas que han vivido más de un año en el extranjero.
-- Cantidad de personas que han vivido en cada país.

-- ===========================================================

-- Creación de base de datos y de tablas

create database residentes;

create table personas( 
    cod varchar(10) primary key,
    nombre varchar(30),
    apell varchar(60)
);

create table paises (
    cod varchar(2) primary key,
    nombre varchar(30)
);

create table residir (
    codPersona varchar(10) references personas(cod),
    codPais varchar(4) references paises(cod),
    desde date,
    hasta date,
    primary key (codPersona, codPais, desde, hasta)
);

-- Inserción de datos

insert into personas (cod, nombre, apell) values
( '1', 'Jose', 'Perez'),
( '2', 'Juan', 'Lopez'),
( '3', 'Jesus', 'Martinez');

insert into paises (cod, nombre) values
( 'de', 'Alemania'),
( 'fr', 'Francia'),
( 'ne', 'Holanda');

insert into residir (codPersona, codPais, desde, hasta) values
( '1', 'de','2015-02-01','2015-12-23'),
( '2', 'fr','2016-03-01','2016-03-07'),
( '2', 'ne','2016-05-05','2016-09-08'),
( '3', 'de','2014-05-01','2015-08-06');


-- Consultas

-- Personas cuyo nombre empieza por J.

select * 
from personas
where nombre like 'J%';


-- Personas que han vivido en Alemania.

-- Version 1, con el código de país

select nombre, apell 
from personas, residir
where codPais = 'de'
and personas.cod = residir.codPersona;

-- Version 2, con el nombre de país

select personas.nombre, apell 
from personas, residir, paises
where paises.nombre = 'Alemania'
and personas.cod = residir.codPersona
and paises.cod = residir.codPais;


-- Cantidad de personas que han vivido en países cuyo nombre acabe en "a".

-- Version 1, cruzando las tres tablas

select count(*)
from personas, residir, paises
where paises.nombre like '%a'
and personas.cod = residir.codPersona
and paises.cod = residir.codPais;

-- Version 2, sin usar "personas" y sin duplicados

select count(distinct codPersona)
from residir, paises
where paises.nombre like '%a'
and paises.cod = residir.codPais;


-- Apellido de las personas que han vivido más de un año en el extranjero.

select apell 
from personas, residir
where hasta-desde > 365
and personas.cod = residir.codPersona;


-- Cantidad de personas que han vivido en cada país.

select nombre, count(distinct codPersona)
from paises, residir
where paises.cod = residir.codPais
group by nombre;
