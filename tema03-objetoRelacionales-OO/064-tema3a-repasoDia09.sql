CREATE DOMAIN impuesto AS NUMERIC(3,1)
CHECK (VALUE >= 0);

CREATE TABLE productos (
  nombre VARCHAR(50),
  preciobase FLOAT4,
  iva impuesto);
  
