CREATE OR REPLACE FUNCTION CantidadCamarasMP(min NUMERIC, max NUMERIC)
    RETURNS integer AS
$$
DECLARE
    cantidad INTEGER;
BEGIN
    SELECT COUNT(*) INTO cantidad FROM camaras
        WHERE resolucion >= min AND resolucion <= max;
    RETURN cantidad;
END;
$$ LANGUAGE plpgsql;

select CantidadCamarasMP(12.1, 14);
