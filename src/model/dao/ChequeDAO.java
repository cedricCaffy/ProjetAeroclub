package model.dao;

import javafx.collections.ObservableList;
import exceptions.DAOException;
import model.classes.paiement.Cheque;

public interface ChequeDAO {
	void enregistrerCheque(Integer idPaiement,Cheque cheque) throws DAOException;
	ObservableList<Cheque> getListeChequeByIdMembre(Integer idMembre) throws DAOException;
}
