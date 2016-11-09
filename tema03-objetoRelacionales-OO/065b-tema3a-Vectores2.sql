-- En la base de datos "tema3a", amplía la tabla "clientes", con un 
-- campo "direccion", que será un vector de cadenas de texto.
-- 
-- Añade direcciones a dos de los datos existentes. Uno de ellos 
-- tendrá una única línea de dirección y otro tendrá 2. 
-- 
-- Muestra los datos resultantes. 

-- Chen Chao

alter table clientes add column direccion text[];

update clientes
   set direccion = array['Calle La Huerta 01, bajo']
 where codigo = '1234';

update clientes
   set direccion = '{"Calle San Vicente 12, 3D", "Alicante"}'
 where codigo = '0001';

select * from clientes where direccion is not null;

"{"Calle La Huerta 01, bajo"}"
"{"Calle San Vicente 12, 3D",Alicante}"
