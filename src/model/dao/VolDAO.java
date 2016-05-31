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
	 * Insere un vol au pilote associe à l'idPilote passe en parametre
	 * @param paiement le vol a inserer
	 * @param idPilote id du pilote qui effectue le vol
	 * @param idAvion id de l'avion utilise pour le vol
	 * @param numeroInstructeur a null s'il n'y a pas d'instructeur
	 * @return l'identifiant du vol insere
	 * @throws DAOException si une erreur de bdd survient
	 */
	Integer insererVol(Vol vol, Integer idPilote, Integer idAvion, String numeroInstructeur) throws DAOException;
}
