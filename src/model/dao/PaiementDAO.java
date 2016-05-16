package model.dao;

import exceptions.DAOException;
import model.classes.paiement.Paiement;

public interface PaiementDAO {
	/**
	 * Insere un paiement au membre associé à l'idMembre passe en parametre
	 * @param paiement le paiement a inserer
	 * @param idMembre
	 * @return l'identifiant du paiement insere
	 * @throws DAOException si une erreur de bdd survient
	 */
	Integer insererPaiement(Paiement paiement,Integer idMembre) throws DAOException;
}
