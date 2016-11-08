-- En la base de datos "tema3a", crea un tipo de datos enumerado 
-- "tipoMes", con los nombres de los meses.

-- Crea otro tipo de datos enumerado "tipoFabricacion", con los valores 
-- "Fabricado" y "Distribuido".

-- Amplía la tabla "productos", con un campo fabricacion, de 
-- tipo "tipoFabricacion".

-- Añade ese dato para uno de los registros existentes en "productos". 

CREATE TYPE tipoMes AS ENUM
('Enero','Febrero','Marzo','Abril',
'Mayo','Junio', 'Julio', 'Agosto', 
'Septiembre', 'Octubre', 'Noviembre', 'Diciembre');

CREATE TYPE tipoFabricacion AS ENUM
('Fabricado', 'Distribuido');

ALTER TABLE productos 
ADD COLUMN fabricacion tipoFabricacion;

UPDATE productos 
SET fabricacion = 'Distribuido' 
WHERE codigo = 1;

SELECT * from productos;
