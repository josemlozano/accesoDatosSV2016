-- Ruben Blanco

-- HERENCIA

-- crea la tabla clientesMayoristas que herede de clientes con
-- un campo adicional "volumenParaDescuento" y otro "descuentoAdicional"

CREATE TABLE clientesMayoristas(
    volumenParaDescuento numeric(10),
    descuentoAdicional tipoDescuento)
INHERITS (clientes);

ALTER TABLE clientesMayoristas
    ADD CONSTRAINT codigo_Mayo PRIMARY KEY (codigo); 


-- Añade un clienteMayorista adicional con un 5% de descuento
-- y volumen de 1000 unidades

INSERT INTO clientesMayoristas VALUES
('0011','Distribuciones San Vicente S.L.',
10.0,'{"poligono canastell"}',
2500,15.5);

-- Muestra los datos resultantes, tanto de clientes 
-- como (sólo) de mayoristas.

select * from clientes;
select * from clientesMayoristas;
select * from only clientes;
