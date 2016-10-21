-- Instala PostgreSQL en Windows.
-- 
-- Crea un base de datos "dia20". 
-- 
-- Crea en ella una tabla "cámaras", con campos "id" (autoincremental), 
-- marca (texto de hasta 20 letras), modelo (hasta 30 letras), resolución 
-- (en megapíxeles: 3 cifras antes de la coma y una después).
-- 
-- Añade dos datos de ejemplo desde PgAdmin.
-- 
-- Crea un programa en Java "GestorCamaras" que te muestre todos los datos 
-- que se han introducido. Los datos deben estar tabulados correctamente 
-- pero sin usar \t (puedes emplear "printf" en su lugar).

create database dia20;

create table camaras
(
    id serial primary key,
    marca varchar(20),
    modelo varchar(30),
    resolucion numeric(4,1)
);

insert into camaras values
(
    default, 'Canon', 'EOS 760 D',  24.5
);

insert into camaras values
(
    default, 'Canon', 'EOS 1100 D',  12.1
);
