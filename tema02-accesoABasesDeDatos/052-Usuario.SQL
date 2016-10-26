-- En la base de datos "dia25" (en PostgreSQL), crea un usuario 
-- "becario", con contraseña "5678", que sólo tenga permiso para
-- ver datos (SELECT) y para añadir (INSERT). Comprueba que si
-- tu aplicación Java sigue funcionando si accedes con ese usuario.

-- ADRIAN SANCHIS

CREATE USER BECARIO WITH PASSWORD '5678';

GRANT SELECT, INSERT ON DEPORTES,DEPORTISTAS TO BECARIO;
GRANT INSERT ON INSERCIONES TO BECARIO;
