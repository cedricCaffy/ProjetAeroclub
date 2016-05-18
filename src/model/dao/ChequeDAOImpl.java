package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.DAOException;
import bd.ConnexionBD;
import model.classes.paiement.Cheque;

public class ChequeDAOImpl implements ChequeDAO{
	private ConnexionBD connexion;
	private static final String INSERT_CHEQUE="INSERT INTO CHEQUE (idpaiement,nomemetteur,banquedebiteur,numerocheque) VALUES (?,?,?,?)";
	public ChequeDAOImpl(ConnexionBD connexion){
		this.connexion=connexion;
	}
	
	@Override
	public void enregistrerCheque(Integer idPaiement, Cheque cheque) throws DAOException{
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		int statut;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion, INSERT_CHEQUE,false,idPaiement,cheque.getNomEmetteur(),cheque.getBanqueDebiteur(),cheque.getNumeroCheque());
			statut=preparedStatement.executeUpdate();
			if(statut==0){
				throw new DAOException("Erreur lors de l'ajout du chèque, aucune ligne n'a été modifiée dans la base de données");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(preparedStatement,connexion);
		}
	}
	
}
