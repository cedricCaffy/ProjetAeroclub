package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.classes.paiement.Paiement;
import util.DateUtil;
import exceptions.DAOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import bd.ConnexionBD;

public class PaiementDAOImpl implements PaiementDAO{
	private ConnexionBD connexion;
	private static final String GET_PAIEMENTS_FROM_MEMBRE = "SELECT idpaiement,datepaiement,montant FROM PAIEMENT "
			+ "WHERE idmembre=? ORDER BY datepaiement DESC";
	private static final String IS_ESPECE = "SELECT * FROM ESPECE WHERE idpaiement=?";
	private static final String SQL_INSERT_PAIEMENT = "INSERT INTO PAIEMENT (idmembre,montant,datepaiement) VALUES (?,?,?);";

	public PaiementDAOImpl(ConnexionBD connexion){
		this.connexion=connexion;
	}

	/**
	 * Retourne la liste des paiements relatifs au client connecte
	 * @param idMembre id du membre connecte
	 * @return la liste de ses paiements
	 * @throws DAOException si une erreur de bdd survient
	 */
	public ObservableList<Paiement> getPaiementsFromMembre(Integer idMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Paiement paiement;
		List<Paiement> listePaiement = new ArrayList<Paiement>();
		Integer idPaiement = null;
		Date datePaiement = null;
		LocalDate localDatePaiement = null;
		Double montant = null;
		String typePaiement = null;
		ObservableList<Paiement> list = FXCollections.observableList(listePaiement);
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PaiementDAOImpl.GET_PAIEMENTS_FROM_MEMBRE,true,idMembre);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				idPaiement = resultSet.getInt("idpaiement");
				datePaiement = resultSet.getDate("datepaiement");
				localDatePaiement = DateUtil.parse(datePaiement);
				montant = resultSet.getDouble("montant");
				if (isEspece(idPaiement)) typePaiement = "Espèce";
				else typePaiement = "Chèque";
				paiement = new Paiement(idPaiement, montant, localDatePaiement, typePaiement);
				list.add(paiement);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return list;
	}

	/**
	 * Predicat de test dutype de paiement
	 * @param idPaiement id du paiement teste
	 * @return true si le paiement est en espece
	 * 		   false si c'est par cheque
	 * @throws DAOException
	 */
	public boolean isEspece(Integer idPaiement) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		boolean isPresent = false;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PaiementDAOImpl.IS_ESPECE,true,idPaiement);
			resultSet = statement.executeQuery();
			if (resultSet.next()) isPresent = true;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return isPresent;
	}

	/**
	 * Insere un paiement au membre associé à l'idMembre passe en parametre
	 * @param paiement le paiement a inserer
	 * @param idMembre
	 * @return l'identifiant du paiement insere
	 * @throws DAOException si une erreur de bdd survient
	 */
	@Override
	public Integer insererPaiement(Paiement paiement, Integer idMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Integer lastInsertId=null;
		Integer statut=null;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion,PaiementDAOImpl.SQL_INSERT_PAIEMENT,true,
					idMembre,paiement.getMontant(),paiement.getDatePaiement().toString());
			statut=preparedStatement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion du paiement, aucune ligne n'a été modifiée");
			}
			resultSet=preparedStatement.getGeneratedKeys();
			if(resultSet.next()){
				lastInsertId=resultSet.getInt(1);
			}else{
				throw new DAOException("Aucun paiement n'a ete crée en base, ID non récupéré");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(resultSet,preparedStatement,connexion);
		}
		return lastInsertId;
	}
}
