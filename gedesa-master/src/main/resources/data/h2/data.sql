INSERT INTO COMERCIALES (ID, NOMBRE, APELLIDO1, APELLIDO2) VALUES 
(1000, 'Anna','Vilchez','Marín'),
(1001, 'Ricardo','Puig','Badimón'),
(1002, 'José Ramón','Gimeno','Quesada');

INSERT INTO PRODUCTOS (CODIGO, NOMBRE, FECHA_ALTA, PRECIO, FAMILIA, DESCATALOGADO) VALUES
(100,'Impresora Laser HP 2P plus','2003-10-25', 250.0,'HARDWARE',true),
(101,'VirusDefender Deluxe v.4','2022-11-18', 46.0,'SOFTWARE',false),
(102,'Alfombrilla Mouse Bob Esponja','2019-04-12', 7.0,'CONSUMIBLES',false),
(103,'Ordenador Portátil 13" LACER 500T ','2019-10-01', 470.0,'HARDWARE',false),
(104,'Ordenador Portátil 15" LADER 720T - PRO','2019-10-01', 840.5,'HARDWARE',false);

INSERT INTO CLIENTES (ID, 
					  NOMBRE_COMERCIAL,
					  NOMBRE,
					  APELLIDO1,
					  APELLIDO2,
					  DIRECCION,
					  POBLACION,
					  CODIGO_POSTAL,
					  PROVINCIA,
					  PAIS,
					  TELEFONO,
					  MOVIL,
					  EMAIL) VALUES
					  
('B46223716','CARPINETERIA METALICA INDUSTRIAL HERMANOS MATA, S.L.', 'José Ramón', 'Mata', 'Francino','Avda. Industria, 234','Santa Perpetua de Mogoda','08045','Barcelona','España','93 220 67 50','+34 6208977','admin@hermanosmata.com'),
('46344938L', null, 'Honorio', 'Martín', 'Salvador','c/San Fernando, 2','Tarragona','28250','Tarragona','España','942209823','+34 6209087','hms233@gmail.com'),
('20059821R','PEK-SOFT', 'Pepín', 'Gálvez', 'Ridruejo','Avenida de los Pájaros, 240 Atico-B', 'Zaragoza','09229','Zaragoza','España',null, '+34 670 33 32','pgrsoft@gmail.com');

INSERT INTO PEDIDOS (CODIGO, FECHA_HORA, ID_COMERCIAL, ID_CLIENTE, OBSERVACIONES, ESTADO) VALUES
(100,'2022-05-11', 1000, 'B46223716', 'Pasan a recoger con furgoneta por almacén. Avisar a Sebas', 'EN_PROCESO'),
(101,'2022-05-12', 1001, '20059821R', null, 'CANCELADO'),
(102,'2022-05-13', 1001, 'B46223716', null, 'CERRADO'),
(103,'2022-05-14', 1000, 'B46223716', null, 'ABIERTO');

INSERT INTO LINEAS_PEDIDO (CODIGO_PEDIDO, CODIGO_PRODUCTO, CANTIDAD) VALUES
(100, 103, 1),
(100, 102, 5),
(101, 100, 1),
(102, 100, 10),
(102, 102, 4),
(102, 104, 1),
(103, 100, 100);
