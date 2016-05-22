package model.dao;

import exceptions.DAOException;
import javafx.collections.ObservableList;
import model.classes.paiement.Paiement;

public interface PaiementDAO {

	/**
	 * Retourne la liste des paiements relatifs au client connecte
	 * @param idMembre id du membre connecte
	 * @return la liste de ses paiements
	 * @throws DAOException si une erreur de bdd survient
	 */
	ObservableList<Paiement> getPaiementsFromMembre(Integer idMembre) throws DAOException;

	/**
	 * Predicat de test dutype de paiement
	 * @param idPaiement id du paiement teste
	 * @return true si le paiement est en espece
	 * 		   false si c'est par cheque
	 * @throws DAOException
	 */
	boolean isEspece(Integer idPaiement) throws DAOException;

	/**
	 * Insere un paiement au membre associé à l'idMembre passe en parametre
	 * @param paiement le paiement a inserer
	 * @param idMembre
	 * @return l'identifiant du paiement insere
	 * @throws DAOException si une erreur de bdd survient
	 */
	Integer insererPaiement(Paiement paiement,Integer idMembre) throws DAOException;
}
