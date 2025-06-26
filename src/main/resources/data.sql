INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

--jugador
INSERT INTO UsuarioAuth(id, email, password, tipo_usuario) values
(2,'bart@gmail.com','$2a$10$WvdTAPnQFOp0U0V6gKSSpurW2BHLiu66uLOw8oFdAo1uZL9J8EVCq','JUGADOR'), --Barto123!Add commentMore actions
(3,'admin@gmail.com', '$2a$10$iruGqNF7VsljAlptmW7uv.l1Y/vuLhlEKrEGwAw3h/MjhFEPghs4O','ADMIN');

INSERT INTO Jugador(id,monedas) VALUES
(2,200.0);
INSERT INTO Admin(id) values (3);

--objetos
INSERT INTO Objeto(precioObjeto,nombre)VALUES
(100.0,'CAÑA MADERA'),
(150.0,'CAÑA METAL');

--mares
INSERT INTO Mar (id_mar,nombre,precio,descripcion,estadoBloqueado) VALUES
(1,'Mitologia griega', 0.0, 'mar uno', FALSE),
(2,'Mitologia Nordica', 150.0, 'mar dos', TRUE),
(3,'Mitologia Japonesa', 200.0, 'mar tres', TRUE),
(4,'Mitologia Yoruba', 250.0, 'mar cuatro', TRUE),
(5,'Mitologia Indu', 300.0, 'mar cinco', TRUE),
(6,'Mitologia Azteca', 350.0, 'mar seis', TRUE),
(7,'Mitologia China', 450.0, 'mar siete', TRUE);

--tabla jugadorMar jugador:bart@gmail.com contra:Barto123!
INSERT INTO JugadorMar(id_jugador,id_mar,estadoBloqueado)VALUES
(2,1,false),
(2,2,true),
(2,3,true),
(2,4,true),
(2,5,true),
(2,6,true),
(2,7,true);




