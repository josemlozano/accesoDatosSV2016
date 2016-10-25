CREATE OR REPLACE FUNCTION cantidadcamarasmp(min real, max real) 
RETURNS integer AS
$$
declare
    cantidad integer;
begin
    select count(*) into cantidad from camaras 
        where resolucion <= max and resolucion >= min;
    return cantidad;
end;
$$ LANGUAGE plpgsql;
