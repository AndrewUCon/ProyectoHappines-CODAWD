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

-- ESTO HA SIDO TODO POR EL DIA DE HOY, MAÑANA TENGO QUE SEGUIR CON LOS DATOS 