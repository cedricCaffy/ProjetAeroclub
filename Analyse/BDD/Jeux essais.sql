INSERT INTO AEROCLUB (idaeroclub,nom) VALUES (1,'Aéroclub Périgueux Bassillac');
INSERT INTO ADRESSE (rue,ville,codepostal,numero)
VALUES
('Chez Michelet','Mauzens et Miremont',24260,NULL),
('rue de maxime','Saint-Flour',15100,3),
('rue des instructeurs','Sarlat',24260,NULL);

INSERT INTO MEMBRE (nom,prenom,idadr,email,numtel,nummobile,datenaissance,solde,idaeroclub,login,mdp) VALUES
('CAFFY','Cédric',1,'cedric.caffy@hotmail.fr','0553058803','0612345678','1993-05-24',0,1,'cedric','cedric'),
('AUZANNEAU','Maxime',2,'maauzannea@poste.isima.fr','0102030405','0601020304','1994-12-31',-5,1,'maxime','maxime'),
('DELPECH','Jean-Michel',3,'jm_delpech@hotmail.fr','0102030405','0698765432','1980-10-05',0,1,'DPH','DPH');

INSERT INTO AVION (nomavion,typeavion,immatriculation,consommation,capacitereservoir,nbplace,massemaximale,couthoraire,disponibilite,vitessecroisiere,idaeroclub)
VALUES
('DR-400','Voyage','F-GIKO',24,110,4,1200,125,'DISPONIBLE',100,1),
('Cessna 152','Ecole','F-GCNP',20,180,2,1000,110,'DISPONIBLE',100,1),
('Cessna 172','Voyage','F-HPGX',30,110,4,1300,165,'DISPONIBLE',110,1);

INSERT INTO DROITS (idmembre,instructeur,administrateur,mecanicien,pilote)
VALUES
(1,'INSTRUCTEUR','ADMIN','MECANICIEN','PILOTE'),
(2,NULL,NULL,NULL,'PILOTE'),
(3,'INSTRUCTEUR',NULL,NULL,'PILOTE');

INSERT INTO PILOTE(idmembre,datevaliditevisitemedicale)
VALUES
(1,'2016-08-09'),
(2,'2016-12-24'),
(3,'2017-10-21');

INSERT INTO BREVET (idpilote,nombrevet,datevalidite,dateObtention)
VALUES
(1,'PPL-A','2016-05-24','2013-05-24'),
(2,'BB','2016-08-08','2013-08-08'),
(1,'CPL-A','2016-06-25','1993-06-25'),
(3,'PPL-A','2017-10-21','2000-05-24'),
(3,'CPL-A','2017-12-24','2005-04-21');

INSERT INTO INSTRUCTEUR (numeroinstructeur,couthoraire,idpilote)
VALUES
('FI-000001',20,1),
('FI-000002',30,3);

INSERT INTO VOL (typevol,nbpassagers,datevol,tempsvol,aeroclubdepart,aeroclubarrivee,idavion,idpilote)
VALUES
('SOLO',2,'2016-04-13','1:00','LFBX','LFBX',1,1);

INSERT INTO PAIEMENT (idmembre,montant,datepaiement)
VALUES
(1,20,'2016-04-13'),
(1,50,'2016-05-24');

INSERT INTO ESPECE (idpaiement)
VALUES
(1);

INSERT INTO CHEQUE(idpaiement,nomemetteur,banquedebiteur,numerocheque)
VALUES
(2,'CAFFY','CA',1);
