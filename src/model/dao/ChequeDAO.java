package model.dao;

import model.classes.paiement.Cheque;

public interface ChequeDAO {
	void enregistrerCheque(Integer idPaiement,Cheque cheque);
}
