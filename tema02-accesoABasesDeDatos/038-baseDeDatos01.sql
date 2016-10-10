create database comidas;

create table recetas (
    codigo varchar(10) primary key,
    nombre varchar(50),
    minutos numeric(3) 
);

insert into recetas values 
('macqueso', 'Macarrones con queso', 35),
('tortpat', 'Tortilla de patatas', 45);

select * 
from recetas
where minutos < 40;

update recetas
set minutos = 40
where codigo = 'macqueso'
