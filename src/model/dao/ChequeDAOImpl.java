package model.dao;

import bd.ConnexionBD;
import model.classes.paiement.Cheque;

public class ChequeDAOImpl implements ChequeDAO{
	private ConnexionBD connexion;
	private static final String INSERT_CHEQUE="INSERT INTO CHEQUE (idpaiement,)";
	public ChequeDAOImpl(ConnexionBD connexion){
		this.connexion=connexion;
	}
	
	@Override
	public void enregistrerCheque(Integer idPaiement, Cheque cheque) {
		
	}
	
}
