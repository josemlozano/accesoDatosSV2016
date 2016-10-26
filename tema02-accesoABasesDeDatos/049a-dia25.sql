-- Chen Chao
create database dia25;

create table deportes (
cod varchar(10) primary key,
nombre varchar(30)
);

create table deportistas (
cod varchar(10) primary key,
nombre varchar(50),
codDeporte varchar(10) references deportes(cod)
);

insert into deportes
(cod, nombre)
values
('FB', 'FootBall'),
('BK', 'BasketBall'),
('TN', 'Tenis');

insert into deportistas
(cod, nombre, codDeporte)
values
('MES', 'Messi', 'FB'),
('RA', 'Raúl', 'FB'),
('FIG', 'Figo', 'FB'),
('GAS', 'Paul Gasol', 'BK'),
('ADR', 'Adrián', null);

select * from deportes d where not exists 
(select 1 from deportistas where d.cod = codDeporte);

select dep.nombre, dep.cod, d.nombre from deportistas dep 
left join deportes d on (dep.codDeporte = d.cod);
