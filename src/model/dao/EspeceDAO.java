package model.dao;

import model.classes.paiement.Espece;
import exceptions.DAOException;

public interface EspeceDAO {
	void ajouterEspece(Integer idPaiement) throws DAOException;
}
