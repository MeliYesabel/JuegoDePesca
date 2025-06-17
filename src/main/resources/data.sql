INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

UPDATE UsuarioAuth SET email = 'maggie@gmail.com', password = '$2a$10$4EDYCcaiSw6cr2Jof3AiiOX3Havc.tFR8GrRZifNR.NGa0jLNPhGC' WHERE (id = 1);
INSERT INTO UsuarioAuth(id, email, password, tipo_usuario) values
(2,'bart@gmail.com','$2a$10$p.KwA3ZiYt1H.sV64X8SOu/WUi4diCbOwfl6S.0VzlWEActSr5BY6','JUGADOR'),
(3,'admin@gmail.com', '$2a$10$IL7Na4ipqDYPWSPigbGh1uGXL2TgVdJp4kocuo2F9bO.Dn4DT.WLC','ADMIN');

INSERT INTO Jugador(id,monedas) VALUES(2,200.0);
INSERT INTO Admin(id) values (3);

INSERT INTO Objeto(precioObjeto,nombre)VALUES
                                           (100.0,'CAÑA MADERA'),
                                           (150.0,'CAÑA METAL');