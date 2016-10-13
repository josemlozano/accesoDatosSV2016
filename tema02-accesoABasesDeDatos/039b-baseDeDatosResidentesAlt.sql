-- MONICA ESTEVE

-- Forma alternativa de crear las claves primarias y ajenas

create table personaS
( 
cod varchar(10) PRIMARY KEY,
nombre varchar(30),
apell varchar(60)
);

create table paisES
(
cod varchar(2) primary key,
nombre varchar(30)
);

Create table residir
(
cod_pers varchar(10),
cod_pais varchar(4),
desde date,
hasta date
);

ALTER TABLE RESIDIR
ADD CONSTRAINT PK_RESIDIR PRIMARY KEY (COD_PERS, COD_PAIS, DESDE, HASTA);

ALTER TABLE  RESIDIR
ADD CONSTRAINT RESIDIR_PERS
   FOREIGN KEY (COD_PERS)
   REFERENCES PERSONA (PERSONA);

ALTER TABLE  RESIDIR
ADD CONSTRAINT RESIDIR_PAISES
   FOREIGN KEY (COD_PAIS)
   REFERENCES PAISES (PAISES);
