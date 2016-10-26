-- En la base de datos "dia25" (en PostgreSQL), crea un "trigger", que, 
-- cuando se añada un nuevo deporte desde la aplicación, indique el texto 
-- "deporte" y la fecha y hora actual en una tabla "inserciones". 

-- Chen Chao

CREATE TABLE inserciones
(
  id serial primary key,
  tabla varchar(20),
  fecha timestamp
);

create or replace function actualizarInsercion() 
    returns trigger as
$$
begin
    insert into inserciones
    values (default, 'deportes', now());
    return null;
end;
$$ language plpgsql;

-- Alternativo:

-- create or replace function actualizarInsercion() 
--     returns void as
-- $$
-- begin
--     insert into inserciones
--     values (default, 'deportes', now());
-- end;
-- $$ language plpgsql;


create trigger trig_deportes
after insert on deportes
execute procedure actualizarinsercion();

-- Más redundante:

-- create trigger trig_deportes
-- after insert on deportes
-- for each row
-- execute procedure actualizarinsercion();

