package model.dao;

import exceptions.DAOException;

public interface AeroclubDAO {
	/**
	 * Recupere le nom d'aeroclub
	 * @return le nom d'aeroclub
	 * @throws DAOException si une erreur de requete survient
	 */
	String getNomAeroclub() throws DAOException;
}
