package model.dao;

import javafx.collections.ObservableList;
import model.classes.paiement.Espece;
import exceptions.DAOException;

public interface EspeceDAO {
	void ajouterEspece(Integer idPaiement) throws DAOException;
	ObservableList<Espece> getEspeceFromIdMembre(Integer idMembre) throws DAOException;
}
