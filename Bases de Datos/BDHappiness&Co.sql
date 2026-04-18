-- ==============================
-- Happines & Co - Base de Datos
-- ==============================


-- Creación de la base de datos
CREATE DATABASE BDHappinessCo;
GO

-- Selección de la base de datos para su uso
USE BDHappinessCo;
GO

-- ==========================
-- TABLAS
-- ==========================


-- Tabla de Usuarios
CREATE TABLE Usuarios (
    ID INT IDENTITY PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
);
GO

-- Tabla de Eventos
CREATE TABLE Eventos (
    ID INT IDENTITY PRIMARY KEY,
    Fecha DATETIME NOT NULL,
    Titulo VARCHAR(255) NOT NULL,
    Ubicacion VARCHAR(255) NOT NULL,
    Descripcion TEXT
);
GO

-- Tabla de Galerías
CREATE TABLE Galerias (
    ID INT IDENTITY PRIMARY KEY,
    Titulo VARCHAR(255) NOT NULL,
    ID_Evento INT NOT NULL,
    FOREIGN KEY (ID_Evento) REFERENCES Eventos(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

-- Table de Imágenes
CREATE TABLE Imagenes (
    ID INT IDENTITY PRIMARY KEY,
    Titulo VARCHAR(255) NOT NULL,
    imagen VARCHAR(255) NOT NULL,
    ID_Galeria INT NOT NULL,
    FOREIGN KEY (ID_Galeria) REFERENCES Galerias(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

-- Tabla de Favoritos
CREATE TABLE Favoritos (
    ID_Usuario INT NOT NULL,
    ID_Evento INT NOT NULL,
    PRIMARY KEY (ID_Usuario, ID_Evento),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ID_Evento) REFERENCES Eventos(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

-- ==========================
-- DATOS
-- ==========================

-- ==========================
-- Insertar Usuarios (Minimo 3)
-- ==========================

INSERT INTO Usuarios (Nombre, Email, Password) VALUES
('Andrés Urrego', 'andres.urrego@example.com', '1234'),
('Anabel Fernandez', 'anabel.fernandez@example.com', '1234'),
('Ana García', 'ana.garcia@example.com', '1234');
GO

-- ==========================
-- Insertar Eventos
-- Historial (anteriores a 28-02-2026): 01-01-2026, 12-01-2026, 24-01-2026
-- Proximos (posteriores a 28-02-2026): 05-06-2026, 15-06-2026, 25-06-2026
-- ==========================

INSERT INTO Eventos (Fecha, Titulo, Ubicacion, Descripcion) VALUES
('2026-01-01', 'Regreso a los 80s Original', 'Teatro Albeniz', 'Disfruta de una noche llena de nostalgia con los mejores éxitos de los años 80s.'),
('2026-01-12', 'MUSCOC: Proyección de Mosquitoes (Les Bambines)', 'Teatro Filarmónica', 'Únete a nosotros para una proyección especial de la película "Mosquitoes" (Les Bambines) como parte del Festival de Cine MUSCOC.'),
('2026-01-24', 'Gasolina Con Capullos', 'Teatro Jovellanos', 'Gasolina con capullos ye una traxedia urbana contemporánea tiñida dhumor agriduz que trata dellos temes dámbitu social'),
('2026-06-05', 'Xixón Folk', 'Parque Fluvial de Viesques', 'Disfruta de una noche de música folclórica en el parque fluvial de Viesques.'),
('2026-06-15', 'Exposición: Dalí y el Surrealismo', 'Museo Barjola, Gijón', 'Retroespectiva de la obra de Salvador Dalí con más de 80 piezas originales procedentes de museos de todo el mundo.'),
('2026-06-25', 'Ciclo de Cine Radar: Los Tortuga', 'Teatro Filarmónica', 'Disfruta de una noche de cine en el teatro Filarmónica.');
GO

-- ==========================
-- Insertar Galerías (Los tres eventos del historial tiene una galeria)
-- ==========================

INSERT INTO Galerias (Titulo, ID_Evento) VALUES
('Galería Regreso a los 80s Original', 1),
('Galería MUSCOC: Proyección de Mosquitoes (Les Bambines)', 2),
('Galería Gasolina Con Capullos', 3);
GO

-- ==========================
-- Insertar Imágenes (Cada galería tiene al menos 3 imágenes)
-- ==========================

-- Galeria 1 - Regreso a los 80s Original
INSERT INTO Imagenes (Titulo, imagen, ID_Galeria) VALUES
('Foto 1 - Regreso a los 80s Original', 'Foto1_Regreso80.jpg', 1),
('Foto 2 - Regreso a los 80s Original', 'Foto2_Regreso80.jpg', 1),
('Foto 3 - Regreso a los 80s Original', 'Foto3_Regreso80.jpg', 1);
GO

-- Galeria 2 - MUSCOC: Proyección de Mosquitoes (Les Bambines)
INSERT INTO Imagenes (Titulo, imagen, ID_Galeria) VALUES
('Foto 1 - MUSCOC: Proyección de Mosquitoes (Les Bambines)', 'Foto1_Mosquitoes.jpg', 2),
('Foto 2 - MUSCOC: Proyección de Mosquitoes (Les Bambines)', 'Foto2_Mosquitoes.jpg', 2),
('Foto 3 - MUSCOC: Proyección de Mosquitoes (Les Bambines)', 'Foto3_Mosquitoes.jpg', 2);
GO

--Galeria 3 - Gasolina Con Capullos
INSERT INTO Imagenes (Titulo, imagen, ID_Galeria) VALUES
('Foto 1 - Gasolina Con Capullos', 'Foto1_Gasolina.jpg', 3),
('Foto 2 - Gasolina Con Capullos', 'Foto2_Gasolina.jpg', 3),
('Foto 3 - Gasolina Con Capullos', 'Foto3_Gasolina.jpg', 3),
('Foto 4 - Gasolina Con Capullos', 'Foto4_Gasolina.jpg', 3);
GO


-- ==========================
-- Insertar Favoritos (Cada usuario tiene al menos 3 eventos favoritos (y al menos 2 eventos del historial))
-- ==========================

-- Usuario 1 - (Andrés) favoritos: 1, 2, 4
INSERT INTO Favoritos (ID_Usuario, ID_Evento) VALUES
(1, 1),
(1, 2),
(1, 4);
GO

-- Usuario 2 - (Anabel) favoritos: 2, 3, 5  
INSERT INTO Favoritos (ID_Usuario, ID_Evento) VALUES
(2, 2),
(2, 3),
(2, 5);
GO

-- Usuario 3 - (Ana) favoritos: 1, 3, 6
INSERT INTO Favoritos (ID_Usuario, ID_Evento) VALUES
(3, 1),
(3, 3),
(3, 6);
GO

-- ==========================
-- VISTAS
-- ==========================

-- Vista 1: Galerías anteriores al 28-02-2026.
CREATE VIEW Galerias_Historial AS
SELECT g.ID, g.Titulo AS titulo_Galeria, e.Titulo AS titulo_Evento, e.Fecha
FROM Galerias g
JOIN Eventos e ON g.ID_Evento = e.ID
WHERE e.Fecha < '2026-02-28';
GO

-- Vista 2: Eventos favoridos del usuario 1 (Andrés).
CREATE VIEW Favoritos_Usuario1 AS
SELECT e.ID, e.Titulo, e.Fecha, e.Ubicacion
FROM Favoritos f
JOIN Eventos e ON f.ID_Evento = e.ID
WHERE f.ID_Usuario = 1;
GO

-- Vista 3: Imágenes de la galeria del evento del 12-01-2026.
CREATE VIEW Imagenes_Evento_2 AS
SELECT i.ID, i.Titulo AS titulo_Imagen, i.imagen, g.Titulo AS titulo_Galeria
FROM Imagenes i
JOIN Galerias g ON i.ID_Galeria = g.ID
WHERE g.ID_Evento = 2;
GO

-- Vista 4: Eventos favoritos del usuario 2 (Anabel) posteriores al 28-02-2026.
CREATE VIEW Favoritos_Usuario2_Proximos AS
SELECT e.ID, e.Titulo, e.Fecha, e.Ubicacion, e.Descripcion
FROM Favoritos f
JOIN Eventos e ON f.ID_Evento = e.ID
WHERE f.ID_Usuario = 2 AND e.Fecha > '2026-02-28';
GO  

