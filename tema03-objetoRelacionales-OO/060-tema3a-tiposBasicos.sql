-- Crea una base de datos llamada "tema3a". Crea en ella una tabla 
-- "productos". Los campos serán:

-- Codigo (clave primaria, autonumérico)
-- Referencia (exactamente 5 letras)
-- Nombre (hasta 100 letras en total)
-- StockMinimo (número de 9 cifras)
-- FechaAlta
-- Activo (booleano)
-- Comentarios (texto grande)

-- Introduce 5 datos de ejemplo en la tabla anterior y comprueba que se 
-- han guardado correctamente. 

CREATE TABLE productos(
    codigo SERIAL PRIMARY KEY,
    referencia CHAR(5),
    nombre VARCHAR(100),
    stockminimo NUMERIC(9),
    fechaalta DATE,
    activo BOOLEAN,
    comentarios TEXT
);

INSERT INTO productos VALUES 
(default, '00001', 'EJEMPLO1', 1, '2016-11-07', true,
    'EJEMPLO1 COMENTARIO'),
(default, '00002', 'EJEMPLO2', 2, '2016-11-07', true,
    'EJEMPLO2 COMENTARIO'),
(default, '00003', 'EJEMPLO3', 3, '2016-11-07', true,
    'EJEMPLO3 COMENTARIO'),
(default, '00004', 'EJEMPLO4', 4, '2016-11-07', true,
    'EJEMPLO4 COMENTARIO'),
(default, '00005', 'EJEMPLO5', 5, '2016-11-07', true,
    'EJEMPLO5 COMENTARIO');
