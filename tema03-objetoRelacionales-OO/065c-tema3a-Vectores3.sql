-- En la base de datos "tema3a", amplía la tabla "clientes", con un 
-- campo "direccion", que será un vector de cadenas de texto.
-- 
-- Añade direcciones a dos de los datos existentes. Uno de ellos 
-- tendrá una única línea de dirección y otro tendrá 2. 
-- 
-- Muestra los datos resultantes. 

-- Adrian Navarro

alter table clientes add column direccion text[];

-- update 1

update clientes
    set direccion='{"Calle baladre","Calle rio"}'
    where codigo = '0001';
        
-- update 2

update clientes
    set direccion='{"Calle luna"}'
    where id = 2;
        
-- Salida de datos :
--    1 -> Calle baladre,Calle rio
--    2 -> Calle luna
