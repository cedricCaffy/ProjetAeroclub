--Creation de l'aerodrome--
INSERT INTO AEROCLUB VALUES (1,'Aeroclub Périgueux Bassillac');
--Creation des adresses--
INSERT INTO ADRESSE (rue,ville,codepostal,numero)
VALUES
('Chez Michelet','Mauzens et Miremont',24260),
('rue de maxime','Saint-Flour',15100,3);

--Creation des membre--
INSERT INTO MEMBRE (nom,prenom,idadr,email,numtel,datenaissance,solde,idaeroclub) VALUES
('CAFFY','Cédric',1,'cedric.caffy@hotmail.fr','0553058803','1993-05-24',50,1),
('AUZANNEAU','Maxime',2,'maauzannea@poste.isima.fr','0102030405','1994-12-31',-5,1);

--Creation des avions--
INSERT INTO AVION (nomavion,typeavion,immatriculation,consommation,capacitereservoir,nbplace,massemaximale,couthoraire,disponibilite,vitessecroisiere,idaeroclub)
VALUES
('DR-400','Voyage','F-GIKO',24,110,4,1200,125,'DISPONIBLE',100,1),
('Cessna 152','Ecole','F-GCNP',20,180,2,1000,110,'DISPONIBLE',100,1),
('Cessna 172','Voyage','F-HPGX',30,110,4,1300,165,'DISPONIBLE',110,1);