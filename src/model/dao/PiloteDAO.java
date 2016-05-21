package model.dao;

import exceptions.DAOException;
import model.classes.membres.Pilote;

public interface PiloteDAO {

	Pilote getPiloteFromId(Integer idMembre) throws DAOException;

	Integer ajouterPilote(Integer idMembre, Pilote pilote) throws DAOException;

	Integer editerPilote(Integer idMembre, Pilote nouvPilote) throws DAOException;

	void supprimerPilote(Integer idMembre) throws DAOException;

}
