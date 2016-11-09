-- En la base de datos "tema3a", amplía la tabla "clientes", con un 
-- campo "direccion", que será un vector de cadenas de texto.
-- 
-- Añade direcciones a dos de los datos existentes. Uno de ellos 
-- tendrá una única línea de dirección y otro tendrá 2. 
-- 
-- Muestra los datos resultantes. 

-- Bruno

alter table clientes 
add column direccion varchar[];

update clientes
set direccion = ARRAY['Direccion1', 'Direccion2']
where codigo = '0001';

update clientes
set direccion = '{"Direccion 1", "dir2", dir3}'
where codigo = '0002';

select direccion, direccion[1], direccion[2], 
direccion[1:2] from clientes;

--Resultado
--"{Direccion1,Direccion2}" | Direccion1 | Direccion2 |"{Direccion1,Direccion2}"
--"{""Direccion 1"",dir2,dir3}" | Direccion 1 | dir2 | "{""Direccion 1"",dir2}"
