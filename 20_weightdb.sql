/* must be dropped in this order to avoid constraint violations */
DROP VIEW IF EXISTS `vejning`;
DROP VIEW IF EXISTS `mad`;
DROP TABLE IF EXISTS mad;
DROP TABLE IF EXISTS vejning;
DROP TABLE IF EXISTS produktbatchkomponent;
DROP TABLE IF EXISTS produktbatch;
DROP TABLE IF EXISTS operatoer;
DROP TABLE IF EXISTS receptkomponent;
DROP TABLE IF EXISTS recept;
DROP TABLE IF EXISTS raavarebatch;
DROP TABLE IF EXISTS raavare;

CREATE TABLE operatoer(opr_id INT PRIMARY KEY, opr_navn TEXT, ini TEXT, cpr TEXT, password TEXT) ENGINE=innoDB;
 
CREATE TABLE raavare(raavare_id INT PRIMARY KEY, raavare_navn TEXT, leverandoer TEXT) ENGINE=innoDB;

CREATE TABLE raavarebatch(rb_id INT PRIMARY KEY, raavare_id INT, maengde REAL, 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE recept(recept_id INT PRIMARY KEY, recept_navn TEXT) ENGINE=innoDB;

CREATE TABLE receptkomponent(recept_id INT, raavare_id INT, nom_netto REAL, tolerance REAL, 
   PRIMARY KEY (recept_id, raavare_id), 
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id), 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE produktbatch(pb_id INT PRIMARY KEY, status INT, recept_id INT, 
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id)) ENGINE=innoDB;

CREATE TABLE produktbatchkomponent(pb_id INT, rb_id INT, tara REAL, netto REAL, opr_id INT, 
   PRIMARY KEY (pb_id, rb_id), 
   FOREIGN KEY (pb_id) REFERENCES produktbatch(pb_id), 
   FOREIGN KEY (rb_id) REFERENCES raavarebatch(rb_id), 
   FOREIGN KEY (opr_id) REFERENCES operatoer(opr_id)) ENGINE=innoDB;

/*Views*/
/*Kan tilgås med SELECT * FROM 'mad' WHERE 1;*/
CREATE VIEW mad
AS
SELECT rk.recept_id, rk.raavare_id, re.recept_navn, ra.raavare_navn, ra.leverandoer 
FROM receptkomponent rk 
	NATURAL JOIN recept re 
	NATURAL JOIN raavare ra;

/*Kan tilgås med SELECT * FROM 'vejning' WHERE 1;*/
CREATE VIEW vejning 
AS
SELECT opr.opr_id, opr.opr_navn, pbk.tara, pbk.netto, ra.raavare_id, ra.raavare_navn, rab.maengde
FROM operatoer opr 
	NATURAL JOIN produktbatchkomponent pbk
	NATURAL JOIN raavare ra
	NATURAL JOIN raavarebatch rab;

/*Procedures*/
delimiter //
CREATE PROCEDURE ReceptUdenRaavare (IN raavare varchar(20))
BEGIN
SELECT recept.recept_navn as "Recept Navn" FROM recept
WHERE recept.recept_id NOT IN
(SELECT receptkomponent.recept_id FROM receptkomponent
WHERE receptkomponent.raavare_id IN
(SELECT raavare.raavare_id FROM raavare                            
WHERE raavare.raavare_navn = raavare)
);
END //
 
delimiter ;

/*Inserts*/
INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES
(1, 'Angelo A', 'AA', '070770-7007', 'lKje4fa'),
(2, 'Antonella B', 'AB', '080880-8008', 'atoJ21v'),
(3, 'Luigi C', 'LC', '090990-9009', 'jEfm5aQ');

INSERT INTO raavare(raavare_id, raavare_navn, leverandoer) VALUES
(1, 'dej', 'Wawelka'),
(2, 'tomat', 'Knoor'),
(3, 'tomat', 'Veaubais'),
(4, 'tomat', 'Franz'),
(5, 'ost', 'Ost og Skinke A/S'),
(6, 'skinke', 'Ost og Skinke A/S'),
(7, 'champignon', 'Igloo Frostvarer');

INSERT INTO raavarebatch(rb_id, raavare_id, maengde) VALUES
(1, 1, 1000),
(2, 2, 300),
(3, 3, 300),
(4, 5, 100),
(5, 5, 100), 
(6, 6, 100),
(7, 7, 100);

INSERT INTO recept(recept_id, recept_navn) VALUES
(1, 'margherita'),
(2, 'prosciutto'),
(3, 'capricciosa');


INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES
(1, 1, 10.0, 0.1),
(1, 2, 2.0, 0.1),
(1, 5, 2.0, 0.1),

(2, 1, 10.0, 0.1),
(2, 3, 2.0, 0.1),  
(2, 5, 1.5, 0.1),
(2, 6, 1.5, 0.1),

(3, 1, 10.0, 0.1),
(3, 4, 1.5, 0.1),
(3, 5, 1.5, 0.1),
(3, 6, 1.0, 0.1),
(3, 7, 1.0, 0.1);

INSERT INTO produktbatch(pb_id, recept_id, status) VALUES
(1, 1, 2), 
(2, 1, 2),
(3, 2, 2),
(4, 3, 1),
(5, 3, 0);


INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES
(1, 1, 0.5, 10.05, 1),
(1, 2, 0.5, 2.03, 1),
(1, 4, 0.5, 1.98, 1),

(2, 1, 0.5, 10.01, 2),
(2, 2, 0.5, 1.99, 2),
(2, 5, 0.5, 1.47, 2),

(3, 1, 0.5, 10.07, 1),
(3, 3, 0.5, 2.06, 2),
(3, 4, 0.5, 1.55, 1),
(3, 6, 0.5, 1.53, 2),

(4, 1, 0.5, 10.02, 3),
(4, 5, 0.5, 1.57, 3),
(4, 6, 0.5, 1.03, 3),
(4, 7, 0.5, 0.99, 3);


 