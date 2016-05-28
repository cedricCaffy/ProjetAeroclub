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
import bd.ConnexionBD;
import model.classes.paiement.Cheque;
import model.classes.paiement.Espece;
import exceptions.DAOException;

public class EspeceDAOImpl implements EspeceDAO{
	ConnexionBD connexion;
	private static final String INSERT_ESPECE="INSERT INTO ESPECE (idpaiement) VALUES (?)";
	private static final String GET_ESPECE_FROM_IDMEMBRE="SELECT * FROM PAIEMENT P JOIN ESPECE E ON P.idpaiement=E.idpaiement WHERE idmembre=?";
	public EspeceDAOImpl(ConnexionBD connexion){
		this.connexion=connexion;
	}
	
	@Override
	public void ajouterEspece(Integer idPaiement) throws DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		int statut;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion, INSERT_ESPECE,false,idPaiement);
			statut=preparedStatement.executeUpdate();
			if(statut==0){
				throw new DAOException("Erreur lors de l'ajout de l'espèce, aucune ligne n'a été modifiée dans la base de données");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(preparedStatement,connexion);
		}
	}

	@Override
	public ObservableList<Espece> getEspeceFromIdMembre(Integer idMembre) throws DAOException {
		ObservableList<Espece> listEspece=FXCollections.observableArrayList();
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		Double montant;
		Date datePaiement;
		LocalDate localDatePaiement;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion, GET_ESPECE_FROM_IDMEMBRE, false, idMembre);
			rs=preparedStatement.executeQuery();
			while(rs.next()){
				montant=rs.getDouble("montant");
				datePaiement=rs.getDate("datepaiement");
				localDatePaiement=DateUtil.parse(datePaiement);
				listEspece.add(new Espece(montant, localDatePaiement));
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}
		return listEspece;
	}
	
}
