package model.dao;

import exceptions.DAOException;
import model.classes.vol.Vol;

public interface VolDAO {
	/**
	 * Insere un vol au membre associé à l'idMembre passe en parametre
	 * @param paiement le vol a inserer
	 * @param idMembre
	 * @return l'identifiant du vol insere
	 * @throws DAOException si une erreur de bdd survient
	 */
	Integer insererVol(Vol vol, Integer idmembre) throws DAOException;
}
