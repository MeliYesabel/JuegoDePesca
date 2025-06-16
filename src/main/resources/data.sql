INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);


INSERT INTO UsuarioAuth(id, email, password, tipo_usuario) values
(1,'bart@gmail.com','$2a$10$p.KwA3ZiYt1H.sV64X8SOu/WUi4diCbOwfl6S.0VzlWEActSr5BY6','JUGADOR'),
(2,'admin@mail.com', '$2a$10$IL7Na4ipqDYPWSPigbGh1uGXL2TgVdJp4kocuo2F9bO.Dn4DT.WLC','ADMIN');

INSERT INTO Jugador(id,monedas) VALUES(1,200.0);
INSERT INTO Admin(id) values (2);

