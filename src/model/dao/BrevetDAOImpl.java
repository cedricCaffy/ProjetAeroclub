package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.ConnexionBD;
import exceptions.DAOException;
import model.classes.membres.Brevet;

public class BrevetDAOImpl implements BrevetDAO {

	private static final String AJOUTER_BREVET = "INSERT INTO BREVET (idPilote, nomBrevet, dateValidite, dateObtention) VALUES (?,?,?,?)";
	private static final String SUPPRIMER_BREVET = "DELETE FROM BREVET WHERE idpilote=? AND nomBrevet=?";
	private ConnexionBD connexion;

	public BrevetDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	@Override
	public void ajouterBrevet(Integer idPilote, Brevet brevet) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,BrevetDAOImpl.AJOUTER_BREVET,true,
					idPilote, brevet.getNomBrevet(), brevet.getDateValidite(), brevet.getDateObtention());
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	@Override
	public void supprimerBrevet(Integer idPilote, String nomBrevet) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,BrevetDAOImpl.SUPPRIMER_BREVET,true,idPilote,nomBrevet);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

}
