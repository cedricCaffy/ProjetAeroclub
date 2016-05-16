package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.classes.paiement.Paiement;
import exceptions.DAOException;
import bd.ConnexionBD;

public class PaiementDAOImpl implements PaiementDAO{
	private ConnexionBD connexion;
	private static final String SQL_INSERT_PAIEMENT = "INSERT INTO PAIEMENT (idmembre,montant,datepaiement) VALUES (?,?,?);";
	
	public PaiementDAOImpl(ConnexionBD connexion){
		this.connexion=connexion;
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
				throw new DAOException("Aucun utilisateur n'a ete crée en base, ID non récupéré");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(resultSet,preparedStatement,connexion);
		}
		return lastInsertId;
	}
}
