package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import exceptions.DAOException;
import bd.ConnexionBD;
import model.classes.paiement.Cheque;

public class ChequeDAOImpl implements ChequeDAO{
	private ConnexionBD connexion;
	private static final String INSERT_CHEQUE="INSERT INTO CHEQUE (idpaiement,nomemetteur,banquedebiteur,numerocheque) VALUES (?,?,?,?)";
	private static final String SELECT_CHEQUE_BY_IDMEMBRE="SELECT * FROM PAIEMENT P JOIN CHEQUE CH ON P.idpaiement=CH.idpaiement WHERE idmembre=?";
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

	@Override
	public ObservableList<Cheque> getListeChequeByIdMembre(Integer idMembre) throws DAOException {
		ObservableList<Cheque> listeCheque=FXCollections.observableArrayList();
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		Double montant;
		Date datePaiement;
		LocalDate localDatePaiement;
		String nomEmetteur;
		String banqueDebiteur;
		Integer numeroCheque;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion, SELECT_CHEQUE_BY_IDMEMBRE, false,idMembre);
			rs=preparedStatement.executeQuery();
			while(rs.next()){
				montant=rs.getDouble("montant");
				datePaiement=rs.getDate("datepaiement");
				localDatePaiement=DateUtil.parse(datePaiement);
				nomEmetteur=rs.getString("nomemetteur");
				banqueDebiteur=rs.getString("banquedebiteur");
				numeroCheque=rs.getInt("numerocheque");
				listeCheque.add(new Cheque(montant, localDatePaiement, nomEmetteur, banqueDebiteur, numeroCheque));
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(preparedStatement,connexion);
		}
		return listeCheque;
	}
	
}
