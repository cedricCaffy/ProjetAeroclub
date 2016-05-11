--Creation de l'aerodrome--
INSERT INTO AEROCLUB(1,'Aéroclub Périgueux Bassillac')
--Creation des adresses--
INSERT INTO ADRESSE (rue,ville,codepostal,numero)
VALUES
('Chez Michelet','Mauzens et Miremont',24260)
('rue de maxime','Saint-Flour',15100,3)

--Creation des membre--
INSERT INTO MEMBRE (nom,prenom,idadr,email,numtel,datenaissance,solde) VALUES
('CAFFY','Cédric',1,'cedric.caffy@hotmail.fr','0553058803','1993-05-24',50),
('AUZANNEAU','Maxime',2,'maauzannea@poste.isima.fr','0102030405','1994-12-31',-5)

--Creation des avions--
INSERT INTO AVION (nomavion,typeavion,immatriculation,consommation,capacitereservoir,nbplace,massemaximale,couthoraire,disponibilite,vitessecroisiere)
VALUES
('DR-400','Voyage','F-GIKO',24,110,4,1200,125,'DISPONIBLE',100)