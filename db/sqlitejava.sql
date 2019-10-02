--
-- File generated with SQLiteStudio v3.2.1 on qui set 26 02:10:26 2019
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: conteudo
DROP TABLE IF EXISTS conteudo;
CREATE TABLE conteudo (concodigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, connome VARCHAR (100) NOT NULL, contempo VARCHAR, condata DATETIME, conrevisao DATE, conmatcodigo INTEGER REFERENCES materia (matcodigo));

-- Table: cronograma
DROP TABLE IF EXISTS cronograma;
CREATE TABLE cronograma (crocodigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, cromateria VARCHAR (100), crodiasemana VARCHAR (100), crosemana VARCHAR (100), croestudodiario VARCHAR (100), cromatcodigo INTEGER REFERENCES materia (matcodigo));

-- Table: cronometro
DROP TABLE IF EXISTS cronometro;
CREATE TABLE cronometro (
    cmtcodigo    INTEGER       PRIMARY KEY AUTOINCREMENT,
    cmttempo     DATETIME NOT NULL,
    cmtalarme     DATETIME null 
);

-- Table: edital
DROP TABLE IF EXISTS edital;
CREATE TABLE edital (edicodigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, edinome VARCHAR (100), edicargo VARCHAR (100), edibanca VARCHAR (70), edidtprova VARCHAR (70));

-- Table: materia
DROP TABLE IF EXISTS materia;
CREATE TABLE materia (matcodigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, matnome VARCHAR (100), matconhecimento BOOLEAN, matedicodigo REFERENCES edital (edicodigo) NOT NULL);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
