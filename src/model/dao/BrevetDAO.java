package model.dao;

import exceptions.DAOException;
import model.classes.membres.Brevet;

public interface BrevetDAO {

	void ajouterBrevet(Integer idPilote, Brevet brevet) throws DAOException;

	void supprimerBrevet(Integer idPilote, String nomBrevet) throws DAOException;

}
