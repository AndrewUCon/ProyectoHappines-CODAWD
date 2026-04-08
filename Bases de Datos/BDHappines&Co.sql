-- ==============================
-- Happines & Co - Base de Datos
-- ==============================


-- Creación de la base de datos
CREATE DATABASE BDHappinesCo;
GO

-- Selección de la base de datos para su uso
USE BDHappinesCo;
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
('2026-06-25', 'Cliclo de Cine Radar: La Tortuga', 'Teatro Campoamos', 'Disfruta de una noche de cine en el teatro Campoamor.');
GO