-- En la base de datos "tema3a", crea un tipo de datos compuesto 
-- "mesAnyo", formado por dos enteros de 16 bits que representarán 
-- un mes y un año (anyo).

-- Amplía la tabla "clientes", con un campo fechaAlta, de tipo "mesAnyo".

-- Edita los datos existentes, para que tengan una fecha de alta 
-- válida. Muestra los datos resultantes.

-- Chen Chao

create type mesAnyo as (mes int2, anyo int2);

alter table clientes add column fechaAlta mesAnyo;

update clientes 
set fechaalta = (1, 2016)
where codigo = '0001';

update clientes 
set fechaalta.mes = 2, fechaalta.anyo = 2016
where codigo = '0002';

insert into clientes values
('0006', 'DAVID', 15, (09,2016) ); 

select * from clientes;
