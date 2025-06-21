INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

INSERT INTO UsuarioAuth(id, email, password, tipo_usuario) values
                                                               (2,'bart@gmail.com','$2a$10$WvdTAPnQFOp0U0V6gKSSpurW2BHLiu66uLOw8oFdAo1uZL9J8EVCq','JUGADOR'), --Barto123!Add commentMore actions
                                                               (3,'admin@gmail.com', '$2a$10$iruGqNF7VsljAlptmW7uv.l1Y/vuLhlEKrEGwAw3h/MjhFEPghs4O','ADMIN');
INSERT INTO Jugador(id,monedas) VALUES(2,200.0);
INSERT INTO Admin(id) values (3);

INSERT INTO Objeto(precioObjeto,nombre)VALUES
                                           (100.0,'CAÑA MADERA'),
                                           (150.0,'CAÑA METAL');
INSERT INTO Usuario(id, email, password, rol, activo)VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

INSERT INTO Mar (id_mar,nombre,precio,descripcion,estadoBloqueado) VALUES
(null,'Mitologia griega', 0.0, 'mar uno', FALSE),
(null,'Mitologia Nordica', 150.0, 'mar dos', TRUE),
(null,'Mitologia Japonesa', 200.0, 'mar tres', TRUE),
(null,'Mitologia Yoruba', 250.0, 'mar cuatro', TRUE),
(null,'Mitologia Indu', 300.0, 'mar cinco', TRUE),
(null,'Mitologia Asteca', 350.0, 'mar seis', TRUE),
(null,'Mitologia China', 450.0, 'mar siete', TRUE);

