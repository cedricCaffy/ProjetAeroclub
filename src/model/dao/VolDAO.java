package model.dao;

import exceptions.DAOException;
import javafx.collections.ObservableList;
import model.classes.vol.Vol;

public interface VolDAO {

	/**
	 * Retourne la liste des vols relatifs au membre connecte
	 * @param idMembre id du membre connecte
	 * @return la liste de ses vols
	 * @throws DAOException
	 */
	ObservableList<Vol> getVolsFromMembre(Integer idMembre) throws DAOException;

	/**
	 * Insere un vol au membre associé à l'idMembre passe en parametre
	 * @param paiement le vol a inserer
	 * @param idMembre
	 * @return l'identifiant du vol insere
	 * @throws DAOException si une erreur de bdd survient
	 */
	Integer insererVol(Vol vol, Integer idmembre) throws DAOException;
}
