INSERT INTO AEROCLUB (idaeroclub,nom) VALUES (1,'Aéroclub Périgueux Bassillac');
INSERT INTO ADRESSE (rue,ville,codepostal,numero)
VALUES
('Chez Michelet','Mauzens et Miremont',24260,NULL),
('rue de maxime','Saint-Flour',15100,3);

INSERT INTO MEMBRE (nom,prenom,idadr,email,numtel,datenaissance,solde,idaeroclub) VALUES
('CAFFY','Cédric',1,'cedric.caffy@hotmail.fr','0553058803','1993-05-24',50,1),
('AUZANNEAU','Maxime',2,'maauzannea@poste.isima.fr','0102030405','1994-12-31',-5,1);

INSERT INTO AVION (nomavion,typeavion,immatriculation,consommation,capacitereservoir,nbplace,massemaximale,couthoraire,disponibilite,vitessecroisiere,idaeroclub)
VALUES
('DR-400','Voyage','F-GIKO',24,110,4,1200,125,'DISPONIBLE',100,1),
('Cessna 152','Ecole','F-GCNP',20,180,2,1000,110,'DISPONIBLE',100,1),
('Cessna 172','Voyage','F-HPGX',30,110,4,1300,165,'DISPONIBLE',110,1);

INSERT INTO DROITS (idmembre,instructeur,administrateur,mecanicien,pilote)
VALUES
(1,1,1,1,1),
(2,0,0,0,1);

INSERT INTO PILOTE(idmembre,datevaliditevisitemedicale)
VALUES
(1,'2016-08-09'),
(2,'2016-12-24');

INSERT INTO BREVET (idpilote,nombrevet,datevalidite,dateObtention)
VALUES
(1,'PPL-A','2016-05-24','2013-05-24'),
(2,'BB','2016-08-08','2013-08-08'),
(1,'CPL-A','2016-06-25','1993-06-25');

INSERT INTO INSTRUCTEUR (numeroinstructeur,couthoraire,idpilote)
VALUES('FI-000001',20,1);
