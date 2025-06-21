INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

INSERT INTO UsuarioAuth(id, email, password, tipo_usuario) values
                                                               (2,'bart@gmail.com','$2a$10$WvdTAPnQFOp0U0V6gKSSpurW2BHLiu66uLOw8oFdAo1uZL9J8EVCq','JUGADOR'), --Barto123!Add commentMore actions
                                                               (3,'admin@gmail.com', '$2a$10$iruGqNF7VsljAlptmW7uv.l1Y/vuLhlEKrEGwAw3h/MjhFEPghs4O','ADMIN');
INSERT INTO Jugador(id,monedas) VALUES(2,200.0);
INSERT INTO Admin(id) values (3);

INSERT INTO Objeto(precioObjeto,nombre)VALUES
                                           (100.0,'CAÑA MADERA'),
                                           (150.0,'CAÑA METAL');