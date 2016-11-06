-- Creación de tablas

create table usuarios (
    cod serial primary key,
    nombre varchar(50)
);

create table quejas (
    cod serial primary key,
    descripcion text,
    fecha date,
    cod_usuarios integer references usuarios (cod)
);

-- Datos de ejemplo

insert into usuarios values 
(default,'Adrian'),
(default,'Bruno'),
(default,'David');


insert into quejas values 
    (default,'Esto es una queja de adrian',current_date,1),
    (default,'Otra queja es una queja de adrian',current_date,1),
    (default,'Esto es una queja de bruno',current_date,2);

-- Usuarios y su cantidad de quejas
    
select nombre, count(q.cod) 
from usuarios 
left join quejas as q 
ON q.cod_usuarios = usuarios.cod group by nombre
order by nombre;

-- Quejas entre dos fechas

select descripcion,fecha,usuarios.nombre 
from quejas 
inner join usuarios 
ON cod_usuarios=usuarios.cod 
where fecha between '2016-11-01' and '2016-12-02';

-- Previo para la función

select count(q.cod) 
from usuarios 
left join quejas as q 
ON q.cod_usuarios = usuarios.cod 
where usuarios.cod=1 
group by usuarios.cod;

-- Función: quejas de un usuario
 
create or replace function CantidadQuejas(nombre varchar ) 
RETURNS INTEGER AS
$$
    DECLARE
        result INTEGER ;
        BEGIN
        select into result count(q.cod) 
            from usuarios 
            left join quejas as q 
            ON q.cod_usuarios = usuarios.cod 
            where usuarios.nombre = $1 
            group by usuarios.cod ;
        return result;
        END;
$$ LANGUAGE plpgsql;

select CantidadQuejas('adrian');

-- Trigger: guardar sin espacios a principio y final

create or replace function procesarEspacios() RETURNS TRIGGER AS
$$
BEGIN
    NEW.descripcion := trim(NEW.descripcion);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER EliminarEspacios
BEFORE INSERT OR UPDATE OR DELETE ON quejas
    FOR EACH ROW EXECUTE PROCEDURE procesarEspacios();

insert into quejas values 
    (default,'  Queja con espacios   ',current_date,1);
