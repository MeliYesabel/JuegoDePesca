INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);


INSERT INTO UsuarioAuth(id, email, password, tipo_usuario) values
(1,'bart@gmail.com','$2a$10$5LyYW4YW5D0TtHSEpVSMAetUh1qdMtmKaqm51nmFCR8sONDAT.cSm','JUGADOR'),
(2,'admin@mail.com', '$2a$10$5LyYW4YW5D0TtHSEpVSMAetUh1qdMtmKaqm51nmFCR8sONDAT.cSm','ADMIN');

INSERT INTO Jugador(id,monedas) VALUES(1,200.0);
INSERT INTO Admin(id) values (2);

