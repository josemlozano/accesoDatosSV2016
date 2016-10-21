-- Crea una función en PL/SQL que permita saber cuántas cámaras 
-- hay en la  base de datos. Llámala desde tu programa Java 
-- usando "SELECT".

CREATE OR REPLACE FUNCTION CantidadCamaras ()
RETURNS INTEGER AS $cantidad$
DECLARE
    cantidad INTEGER;
BEGIN
   SELECT COUNT(*) INTO cantidad FROM camaras;
   RETURN cantidad;
END;
$cantidad$ LANGUAGE plpgsql;
