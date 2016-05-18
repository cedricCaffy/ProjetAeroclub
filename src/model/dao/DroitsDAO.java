package model.dao;

import java.util.List;

import exceptions.DAOException;

public interface DroitsDAO {

	List<String> getDroitsByIdMembre(Integer idMembre) throws DAOException;

	void ajouterDroits(Integer idMembre, List<String> droits) throws DAOException;

	void editerDroits(Integer idMembre, List<String> nouvDroits) throws DAOException;

	void supprimerDroits(Integer idMembre) throws DAOException;

}
