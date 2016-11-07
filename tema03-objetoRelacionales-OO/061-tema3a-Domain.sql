-- En la base de datos “tema3a”, crea un dominio “tipoDescuento”, que 
-- será un número positivo del 0.0% al 99.9% (tres cifras, una de ellas 
-- decimal).
-- 
-- Crea un tabla “clientes”, con campos:
-- 
-- * Codigo (exactamente 4 letras, clave primaria)
-- * Nombre (hasta 60 letras)
-- * Descuento (tipoDescuento)
-- 
-- Introduce 2 datos de ejemplo correctos, otro con 2 decimales y 
-- otro negativo. Comprueba los resultados. 

-- Mónica Esteve

CREATE DOMAIN tipoDescuento as numeric(3,1)
    check ( value >= 0);
    
CREATE TABLE DESCUENTOS(
    codigo CHAR(4) PRIMARY KEY,
    nombre VARCHAR(60),
    descuento tipoDescuento
);
        
insert into descuentos values
('0001', 'MONI', 50.0 );
                
insert into descuentos values
('0002', 'INDRA', 40.0 ); 
                
-- redondea
insert into descuentos values
('0003', 'MONIINDRA', 50.55 );
        
 -- no se inserta       
insert into descuentos values
('0005', 'FANFAN', -50.55 );
        
SELECT * FROM DESCUENTOS;
