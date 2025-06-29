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
(1,'Mitologia-griega', 0.0, 'mar uno', FALSE),
(2,'Mitologia-Nordica', 150.0, 'mar dos', TRUE),
(3,'Mitologia-Japonesa', 200.0, 'mar tres', TRUE),
(4,'Mitologia-Yoruba', 250.0, 'mar cuatro', TRUE),
(5,'Mitologia-Indu', 300.0, 'mar cinco', TRUE),
(6,'Mitologia-Azteca', 350.0, 'mar seis', TRUE),
(7,'Mitologia-China', 450.0, 'mar siete', TRUE);

INSERT INTO Rareza (id_rareza, nombre, probabilidadAtrapar, valor) VALUES
(1, 'Normal', 80.0, 10),
(2, 'Raro', 50.0, 20),
(3, 'Épico', 20.0, 40);

-- Mar 1 - Mitología griega
INSERT INTO Pez (id_pez, nombre, id_rareza, id_mar) VALUES
                                                        (null, 'Delfín Helénico', 1, 1),
                                                        (null, 'Trucha de Atenea', 1, 1),
                                                        (null, 'Carpa de Apolo', 1, 1),
                                                        (null, 'Sirena Menor', 2, 1),
                                                        (null, 'Hipocampo Menor', 2, 1),
                                                        (null, 'Kraken Mítico', 3, 1);

-- Mar 2 - Mitología Nórdica
INSERT INTO Pez (id_pez, nombre, id_rareza, id_mar) VALUES
                                                        (null, 'Pez Runa', 1, 2),
                                                        (null, 'Salmón de Thor', 1, 2),
                                                        (null, 'Bacalao de Freyja', 1, 2),
                                                        (null, 'Serpiente del Fiordo', 2, 2),
                                                        (null, 'Anguila de Hielo', 2, 2),
                                                       (null, 'Jörmungandr Joven', 3, 2);

--tabla jugadorMar jugador:bart@gmail.com contra:Barto123!
INSERT INTO JugadorMar(id_jugador,id_mar,estadoBloqueado)VALUES
(2,1,false),
(2,2,true),
(2,3,true),
(2,4,true),
(2,5,true),
(2,6,true),
(2,7,true);




-- Mar 3 - Mitología Japonesa
INSERT INTO Pez (id_pez, nombre, id_rareza, id_mar) VALUES
                                                        (null, 'Koi de Amaterasu', 1, 3),
                                                        (null, 'Trucha Fuji', 1, 3),
                                                        (null, 'Medaka Sagrada', 1, 3),
                                                        (null, 'Pez Yokai', 2, 3),
                                                        (null, 'Siluro de Tormenta', 2, 3),
                                                        (null, 'Dragón del Agua', 3, 3);

-- Mar 4 - Mitología Yoruba
INSERT INTO Pez (id_pez, nombre, id_rareza, id_mar) VALUES
                                                        (null, 'Tilapia de Yemayá', 1, 4),
                                                        (null, 'Mojarra Sagrada', 1, 4),
                                                        (null, 'Dorado del Sol', 1, 4),
                                                        (null, 'Pez Orisha', 2, 4),
                                                        (null, 'Bagre Ancestral', 2, 4),
                                                        (null, 'Serpiente Marina Olokun', 3, 4);

-- Mar 5 - Mitología Hindú
INSERT INTO Pez (id_pez, nombre, id_rareza, id_mar) VALUES
                                                        (null, 'Pez Ganges', 1, 5),
                                                        (null, 'Barbo de Vishnu', 1, 5),
                                                        (null, 'Dorado de Shiva', 1, 5),
                                                        (null, 'Carpa Karma', 2, 5),
                                                        (null, 'Pez de Loto', 2, 5),
                                                        (null, 'Makara Legendario', 3, 5);

-- Mar 6 - Mitología Azteca
INSERT INTO Pez (id_pez, nombre, id_rareza, id_mar) VALUES
                                                        (null, 'Axolote Común', 1, 6),
                                                        (null, 'Trucha Tenochtitlan', 1, 6),
                                                        (null, 'Mojarra del Sol', 1, 6),
                                                        (null, 'Ajolote Guerrero', 2, 6),
                                                        (null, 'Bagre Jaguar', 2, 6),
                                                        (null, 'Serpiente Acuática Quetzal', 3, 6);

-- Mar 7 - Mitología China
INSERT INTO Pez (id_pez, nombre, id_rareza, id_mar) VALUES
                                                        (null, 'Koi Imperial', 1, 7),
                                                        (null, 'Carpa Celestial', 1, 7),
                                                        (null, 'Dorado de Jade', 1, 7),
                                                        (null, 'Dragón Pez', 2, 7),
                                                        (null, 'Siluro Dorado', 2, 7),
                                                        (null, 'Longyu, Espíritu del Agua', 3, 7);
