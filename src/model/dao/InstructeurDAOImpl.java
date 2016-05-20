package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.ConnexionBD;
import exceptions.DAOException;

public class InstructeurDAOImpl implements InstructeurDAO {

	private static final String AJOUTER_INSTRUCTEUR = "INSERT INTO INSTRUCTEUR VALUES (?,?,?)";
	private static final String EDITER_INSTRUCTEUR = "UPDATE INSTRUCTEUR SET numeroinstructeur=?, couthoraire=? WHERE idpilote=?";
	private static final String SUPPRIMER_INSTRUCTEUR = "DELETE FROM INSTRUCTEUR WHERE idpilote=?";
	private ConnexionBD connexion;

	public InstructeurDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	@Override
	public void ajouterInstructeur(Integer idPilote, String numeroInstructeur, Double coutHoraire) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.AJOUTER_INSTRUCTEUR,true,
					numeroInstructeur, coutHoraire, idPilote);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	@Override
	public void editerInstructeur(Integer idPilote, String numeroInstructeur, Double coutHoraire) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.EDITER_INSTRUCTEUR,true,
					numeroInstructeur, coutHoraire, idPilote);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	@Override
	public void supprimerInstructeur(Integer idPilote) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.SUPPRIMER_INSTRUCTEUR,true,idPilote);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

}
