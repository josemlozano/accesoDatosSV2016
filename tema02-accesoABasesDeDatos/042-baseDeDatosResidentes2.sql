
-- Añade un nuevo país, "Rusia", con código "Ru".

insert into paises values 
('ru', 'Rusia');


-- Muestra los nombres de todos los países, junto con las personas (si es que las hay) que han vivido en ellos.

select paises.nombre, personas.nombre
from paises 
left join residir on (paises.cod = residir.codPais)
left join personas on (residir.codPersona = personas.cod);


-- Añade a la tabla Personas un campo "nacimiento", que sea su año de nacimiento (un número de 4 cifras).

alter table personas 
add nacimiento numeric(4);


-- Muestra los nombres de los países cuyo código contenga una "e", ya sea en mayúsculas o en minúsculas.

select nombre from paises
where cod like '%e%'
or cod like '%E%';

select nombre from paises
where upper(cod) like '%E%';


-- Cambia el nombre del país "Holanda" a "Países bajos".

update paises
set nombre = 'Paises Bajos'
where nombre = 'Holanda';

-- Muestra los nombres de los países en los que haya 
-- vivido más de una persona de la que tengamos constancia.

select p.nombre
from paises p, residir r
where p.cod = r.codpais
group by p.cod
having count(r.codPais) > 1;


-- Muestra los nombres de los personas que han 
-- vivido en países en los que haya vivido más 
-- de una persona de la que tengamos constancia.

select p.nombre
from personas p, residir r
where p.cod = r.codPersona
and r.codPais in
(
    select p.cod
    from paises p, residir r
    where p.cod = r.codpais
    group by p.cod
    having count(r.codPais) > 1
);

