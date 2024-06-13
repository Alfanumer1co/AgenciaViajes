-- 1. LO BASICO
create database db_agencia_viajes;
use db_agencia_viajes;
-- drop database db_agencia_viajes;
-- 2. IMPLEMENTACIÓN DE TABLAS
create table Cliente(
id_c int primary key auto_increment,
nombres varchar(40) not null,
apellidos varchar(40) not null,
edad int not null,
dni int(8) not null,
correo varchar(40) not null,
celular int(9) not null
);

create table Destino(
id_d int primary key auto_increment,
nombre varchar(40) not null,
descrip varchar(200) not null,
precio decimal(8,2) not null
);

create table Viaje(
id_vi int primary key auto_increment,
id_dest int not null,
salida date not null,
llegada date not null,

foreign key(id_dest) references Destino(id_d)
);

create table Reserva(
id_r int primary key auto_increment,
id_cli int not null,
id_via int not null,
asientos int not null,
fech_pa date not null,
precio_r decimal(8,2) not null,
est_r bit not null,

foreign key(id_cli) references Cliente(id_c),
foreign key(id_via) references Viaje(id_vi)
);

create table Venta(
id_ve int primary key auto_increment,
id_res int not null,
metodoPag varchar(40) not null,
pago decimal(8,2) not null,
fech_v date not null,

foreign key(id_res) references Reserva(id_r)
);

-- 3. INSERCION DE DATOS
-- marcado como "nulo" el "id" x que es AutoIncrementable,  
-- auque creo que tambien se puede dejar -1 dato en la tabla

insert into Cliente values(null,'Rodrigo','Ramirez Cupe',29,34345267,'roRami@gmail.com',989786756);
insert into Cliente values(null,'Eder','Quispe Quintanilla',31,5344663,'edQuis@email.com',956543421);
insert into Cliente values(null,'Luis','Humani Huamancha',19,56323876,'luHuam@hotmail.com',929922378);
insert into Cliente values(null,'Gladizzz','Villavicencio Castro',45,09298712,'glVilla@idat.com',956745232);
insert into Cliente values(null,'Luis','Muños Escobar',26,75326327,'dotaLover@imail.com',926843088);

insert into Destino values(null,'Ica','Disfruta de sus playas, valles y del increíble oasis de la Huacachina todo el año y atrévete a realizar sandboard sobre las dunas.',455.00);
insert into Destino values(null,'Callao','Si no eres asaltado al ingresar, visita sus puertos.', 50.00);
insert into Destino values(null,'Junin','Cataratas y valles con paisajes en su estado más puro, pueblos con profunda vocación religiosa y una reserva nacional con animales del ande.',800.00);
insert into Destino values(null,'Ucayali','Te invitamos a vivir una experiencia diferente con la naturaleza y sus raí­ces profundas.',650.00);
insert into Destino values(null,'Puno','Podrás ser parte de una atmósfera mágica donde la leyenda, las tradiciones y las fiestas multicolor se respiran todos los dí­as. ',550.00);

insert into Viaje values(null,3,'2023-12-13',current_date());
insert into Viaje values(null,1,'2023-11-12','2023-11-13');
insert into Viaje values(null,2,'2023-10-13','2023-10-14');
insert into Viaje values(null,4,'2023-09-04','2023-09-06');
insert into Viaje values(null,5,'2023-01-02','2023-01-02');

insert into Reserva values(null,3,5,2,current_date(),410.00,1);
insert into Reserva values(null,4,1,4,current_date(),500.00,0);
insert into Reserva values(null,5,2,3,current_date(),500.00,1);
insert into Reserva values(null,2,4,1,current_date(),600.00,1);
insert into Reserva values(null,1,3,5,current_date(),700.00,1);

insert into Venta values(null,5,'Tarjeta',1200.00,'2023-12-14');
insert into Venta values(null,4,'Efectivo',2000.00,'2023-12-12');
insert into Venta values(null,3,'Yape',600.00,'2023-12-11');
insert into Venta values(null,2,'Plin',800.00,'2023-12-10');
insert into Venta values(null,1,'Tarjeta',200.00,'2023-12-15');

select * from Cliente;
select * from Destino;
select * from Viaje;
select * from Reserva;
select * from Venta;

select current_date()

-- Procedimientos
--    LISTAR CLIENTE
DELIMITER //
create procedure sp_mostrar_cliente()
begin
select * from Cliente;
end //
DELIMITER ;

call sp_mostrar_cliente();

--    MOSTRAR POR ID
 Delimiter //
 create procedure sp_cliente_by_codigo(_id_c int)
 begin
    select * from Cliente where id_c = _id_c;
 end//
 call sp_cliente_by_codigo(5);
 
 -- REGISTRAR
DELIMITER //
create procedure sp_registrar_cliente(_nombres varchar(40), _apellidos varchar(40), 
						   _edad int, _dni int, _correo varchar(40), _celular int)
begin
insert into Cliente(nombres, apellidos, edad, dni, correo, celular) 
          values( _nombres,_apellidos, _edad, _dni, _correo, _celular);
end //
DELIMITER ;
call sp_registrar_cliente('Juaito','Movisstar',54,7654321,'juani@mail.com',98787823);

-- MODIFICAR CLIENTE
DELIMITER //
create procedure sp_modificar_cliente(_id_c int,_nombres varchar(40), _apellidos varchar(40),
                                     _edad int, _dni int, _correo varchar(40), _celular int)
begin
update Cliente set nombres = _nombres, apellidos = _apellidos, edad = _edad, dni =_dni,
				   correo = _correo, celular = _celular
where id_c = _id_c;
end //
DELIMITER ;
call sp_modificar_cliente(7,'Macario','Mocanaqui',34,678933,'juany@gmail.com',978654352);

--  ELIMINAR CLIENTE
DELIMITER //
create procedure sp_eliminar_cliente(_id_c int)
begin
delete from Cliente where id_c = _id_c;
end //
DELIMITER ;
call sp_eliminar_cliente(7);

--           DESTINO
--  LISTAR
DELIMITER //
create procedure sp_mostrar_destino()
begin
select * from Destino;
end //
DELIMITER ;
call sp_mostrar_destino();

-- MOSTRAR POR ID DESTINO
 Delimiter //
 create procedure sp_destino_by_codigo(_id_d int)
 begin
    select * from Destino where id_d = _id_d;
 end//
 call sp_destino_by_codigo(3);
 
-- REGISTRAR DESTINO
DELIMITER //
create procedure sp_registrar_destino(_nombre varchar(40),
               _descrip varchar(200),_precio decimal(8,2))
begin
insert into Destino(nombre,descrip,precio) values (_nombre,_descrip,_precio);
end //
DELIMITER ;
call sp_registrar_destino('Piuraa','Disfruta de variedad de sitios turisticos que tenemos para ofreser como tambien comer en los mejores restaurantes',500.00);

-- MODIFICAR DESTINO
DELIMITER //
create procedure sp_modificar_destino(_id_d int,_nombre varchar(40),_descrip varchar(200),_precio decimal(8,2))
begin
update Destino set nombre = _nombre, descrip = _descrip, precio = _precio
where id_d = _id_d;
end //
DELIMITER ;
call sp_modificar_destino(6,'Cuzco','Disfruta de variedad de sitios turisticos que tenemos para ofreser como tambien comer en los mejores restaurantes',50.00);

-- ELIMINAR DESTINO
DELIMITER //
create procedure sp_eliminar_Destino(_id_d int)
begin 
	DELETE FROM Destino where _id_d = id_d;
end //
DELIMITER ;
call sp_eliminar_Destino(6)

--  ************* VIAJES **********
--  TODOS LOS VIAJES
DELIMITER //
create procedure sp_mostrar_viaje()
begin
select * from Viaje;
end //
DELIMITER ;

call sp_mostrar_viaje();

-- BUSCAR POR ID VIAJE
 Delimiter //
 create procedure sp_viaje_by_codigo(_id_vi int)
 begin
    select * from Viaje where id_vi = _id_vi;
 end//
 
 call sp_viaje_by_codigo(5);

-- REGISTRAR VIAJE
DELIMITER //
create procedure sp_registrar_viaje(_id_dest int, _salida date, _llegada date)
begin
insert into Viaje(id_dest, salida, llegada) values (_id_dest, _salida, _llegada);
end //
DELIMITER ;

call sp_registrar_viaje(4,'2023-12-14','2023-12-15');

-- MODIFICAR VIAJE
DELIMITER //
create procedure sp_actualizar_Viaje(_id_vi int, _id_dest int, _salida date, _llegada date)
begin
update Viaje set id_dest = _id_dest, salida=_salida, llegada=_llegada
where id_vi=_id_vi;
end //
DELIMITER ;

call sp_actualizar_Viaje(6,4,'2024-01-14','2024-01-15');

--   ELIMINAR
DELIMITER //
create procedure sp_eliminar_viaje( _id_vi int)
begin 
	DELETE FROM Viaje where id_vi = _id_vi;
end //
DELIMITER ;

call sp_eliminar_viaje(6);

-- ********* RESERVA ***********
DELIMITER //
create procedure sp_mostrar_reserva()
begin
select * from Reserva;
end //
DELIMITER ;

call sp_mostrar_reserva();

-- BUSCAR RESERVA POR ID
 Delimiter //
 create procedure sp_reserva_by_codigo(_id_r int)
 begin
    select * from Reserva where id_r = _id_r;
 end//
 
 call sp_reserva_by_codigo(3);
 
 -- BUSCAR RESERVAS "" ACTIVAS ""
 DELIMITER //
create procedure sp_reservas_activas()
begin
	select * from Reserva where est_r = 1;
end//

call sp_reservas_activas();

-- BUSCAR RESERVAS  "" INACTIVAS ""
 DELIMITER //
create procedure sp_reservas_inactivas()
begin
	select * from Reserva where est_r = 0;
end//

call sp_reservas_inactivas();

-- REGISTRAR RESERVA 
DELIMITER //
create procedure sp_registrar_reserva(_id_cli int, _id_via int, _asientos int, 
                     _fech_pa date, _precio_r decimal(8,2), _est_r bit)
begin
insert into Reserva(id_cli, id_via, asientos, fech_pa, precio_r, est_r) 
        values (_id_cli, _id_via, _asientos, _fech_pa,_precio_r,_est_r);
end //
DELIMITER ;

call sp_registrar_reserva(4,2,10,'2023-12-14',600.00,0);

-- MODIFICAR RESERVA
DELIMITER //
create procedure sp_actualizar_reserva(_id_r int, _id_cli int, _id_via int, _asientos int, 
                     _fech_pa date, _precio_r decimal(8,2), _est_r bit)
begin
update Reserva set id_cli = _id_cli, id_via = _id_via, asientos = _asientos, 
                   fech_pa = _fech_pa, precio_r = _precio_r, est_r = _est_r
where id_r = _id_r;
end //
DELIMITER ;

call sp_actualizar_reserva(10,4,2,7,'2023-12-09',610.00,0);

-- ELIMINAR RESERVA
DELIMITER //
create procedure sp_eliminar_reserva(_id_r int)
begin 
	DELETE FROM Reserva where id_r = _id_r;
end //
DELIMITER ;

call sp_eliminar_reserva(11);

-- ************ VENTA *************
-- LISTA
DELIMITER //
create procedure sp_mostrar_venta()
begin
select * from Venta;
end //
DELIMITER ;

call sp_mostrar_venta();

-- BURCAR VENTA POR ID
 Delimiter //
 create procedure sp_venta_by_codigo(_id_ve int)
 begin
    select * from Venta where id_ve = _id_ve;
 end//
 
 call sp_venta_by_codigo(3);

-- REGISTRAR VENTA
DELIMITER //
create procedure sp_registrar_venta(_id_res int, _metodoPag varchar(40), _pago decimal(8,2), _fech_v date)
begin
insert into Venta(id_res, metodoPag, pago, fech_v) values (_id_res, _metodoPag, _pago,_fech_v);
end //
DELIMITER ;

call sp_registrar_venta(2,'Tulki',242.00,current_date());

-- MODIFICAR VENTA
DELIMITER //
create procedure sp_actualizar_venta(_id_ve int , _id_res int, _metodoPag varchar(40), _pago decimal(8,2), _fech_v date)
begin
update Venta set id_res = _id_res, metodoPag = _metodoPag, pago = _pago, fech_v = _fech_v
where id_ve = _id_ve;
end //
DELIMITER ;

call sp_actualizar_venta(6,3,"Talko",201.22,'2024-11-01');

-- ELIMINAR VENTA
DELIMITER //
create procedure sp_eliminar_venta(_id_ve int)
begin 
	DELETE FROM Venta where id_ve = _id_ve ;
end //
DELIMITER ;

call sp_eliminar_venta(6);

