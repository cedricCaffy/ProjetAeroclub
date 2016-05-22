package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.ConnexionBD;
import exceptions.DAOException;
import model.classes.membres.Instructeur;

public class InstructeurDAOImpl implements InstructeurDAO {

	private static final String GET_INSTRUCTEUR_FROM_ID = "SELECT * FROM INSTRUCTEUR WHERE idpilote=?";
	private static final String AJOUTER_INSTRUCTEUR = "INSERT INTO INSTRUCTEUR VALUES (?,?,?)";
	private static final String EDITER_INSTRUCTEUR = "UPDATE INSTRUCTEUR SET numeroinstructeur=?, couthoraire=? WHERE idpilote=?";
	private static final String SUPPRIMER_INSTRUCTEUR = "DELETE FROM INSTRUCTEUR WHERE idpilote=?";
	private ConnexionBD connexion;

	public InstructeurDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	@Override
	public void ajouterInstructeur(Integer idPilote, Instructeur instructeur) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.AJOUTER_INSTRUCTEUR,true,
					instructeur.getNumeroInstructeur(), instructeur.getCoutHoraire(), idPilote);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion de l'instructeur, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	@Override
	public void editerInstructeur(Integer idPilote, Instructeur nouvInstruct) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.EDITER_INSTRUCTEUR,true,
					nouvInstruct.getNumeroInstructeur(), nouvInstruct.getCoutHoraire(), idPilote);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la modification de l'instructeur, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	@Override
	public void supprimerInstructeur(Integer idPilote) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.SUPPRIMER_INSTRUCTEUR,true,idPilote);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la suppression de l'instructeur, aucune ligne n'a été supprimée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	@Override
	public Instructeur getInstructeurFromId(Integer idPilote) throws DAOException {
		Instructeur instructeur = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String numeroInstructeur = null;
		Double coutHoraire = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.GET_INSTRUCTEUR_FROM_ID,true,idPilote);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				numeroInstructeur = resultSet.getString("numeroinstructeur");
				coutHoraire = resultSet.getDouble("couthoraire");
				instructeur = new Instructeur(numeroInstructeur, coutHoraire);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return instructeur;
	}

}
