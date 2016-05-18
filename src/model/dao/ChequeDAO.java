package model.dao;

import exceptions.DAOException;
import model.classes.paiement.Cheque;

public interface ChequeDAO {
	void enregistrerCheque(Integer idPaiement,Cheque cheque) throws DAOException;
}
