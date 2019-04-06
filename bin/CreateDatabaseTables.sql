SET search_path TO oblig3;



DROP TABLE IF EXISTS avdeling;

create table avdeling (
	ID SERIAL PRIMARY KEY,
	navn varChar(20) NOT NULL,
	sjefID SERIAL /*references ansatt(ID) */
);

INSERT INTO avdeling
(navn, sjefID)
VALUES('NONEAVD', 1);






DROP TABLE IF EXISTS ansatt;

create table ansatt (
	ID SERIAL PRIMARY KEY,
	brukernavn varChar(4) NOT NULL,
	fornavn varChar(20) NOT NULL,
	etternavn varChar(20) NOT NULL,
	ansettelsesdato DATE NOT NULL,
	stilling varChar(20) NOT NULL,
	maanedslonn Int NOT NULL,
	avdeling SERIAL references avdeling(ID)

);

INSERT INTO ansatt
(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn)
VALUES('NONE', 'NONEF', 'NONEE', '0001-01-01', 'NONE', 0);


ALTER TABLE avdeling 
ADD CONSTRAINT fk_avdeling_ansatt FOREIGN KEY (sjefID) REFERENCES ansatt (ID);



DROP TABLE IF EXISTS prosjekt;

create table prosjekt (
	ID SERIAL PRIMARY KEY,
	navn varChar(20) NOT NULL,
	beskrivelse varChar
);

create table prosjektOversikt (
	ID SERIAL PRIMARY KEY,
	ansattID SERIAL references ansatt(ID),
	prosjektID SERIAL references prosjekt(ID),
	ansattRolle varChar,
	ansattArbeidsTimer Int
);