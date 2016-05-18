package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.ConnexionBD;
import exceptions.DAOException;

public class PiloteDAOImpl implements PiloteDAO {

	private static final String AJOUTER_PILOTE = "INSERT INTO PILOTE (idmembre, datevaliditevisitemedicale) VALUES (?,?)";
	private static final String GET_ID_DERNIER_PILOTE = "SELECT MAX(idpilote) FROM PILOTE";
	private static final String EDITER_PILOTE = "UPDATE PILOTE SET datevaliditevisitemedicale=? WHERE idmembre=?";
	private static final String SUPPRIMER_PILOTE = "DELETE FROM PILOTE WHERE idmembre=?";
	private ConnexionBD connexion;

	public PiloteDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	@Override
	public void ajouterPilote(Integer idMembre, Date dateVVM) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PiloteDAOImpl.AJOUTER_PILOTE,true,
					idMembre, dateVVM);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	@Override
	public Integer getIdDernierPilote() throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer idPilote;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PiloteDAOImpl.GET_ID_DERNIER_PILOTE,true);
			resultSet=statement.executeQuery();
			idPilote = resultSet.getInt("idpilote");
		} catch(SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return idPilote;
	}

	@Override
	public void editerPilote(Integer idMembre, Date nouvDateVVM) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PiloteDAOImpl.EDITER_PILOTE,true,
					nouvDateVVM, idMembre);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	@Override
	public void supprimerPilote(Integer idMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PiloteDAOImpl.SUPPRIMER_PILOTE,true,idMembre);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

}
