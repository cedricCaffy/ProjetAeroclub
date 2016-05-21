package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import bd.ConnexionBD;
import exceptions.DAOException;
import model.classes.membres.Pilote;
import util.DateUtil;

public class PiloteDAOImpl implements PiloteDAO {

	private static final String GET_PILOTE_FROM_ID = "SELECT * FROM PILOTE WHERE idmembre=?";
	private static final String AJOUTER_PILOTE = "INSERT INTO PILOTE (idmembre, datevaliditevisitemedicale) VALUES (?,?)";
	private static final String EDITER_PILOTE = "UPDATE PILOTE SET datevaliditevisitemedicale=? WHERE idmembre=?";
	private static final String SUPPRIMER_PILOTE = "DELETE FROM PILOTE WHERE idmembre=?";
	private ConnexionBD connexion;

	public PiloteDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	@Override
	public Pilote getPiloteFromId(Integer idMembre) throws DAOException {
		Pilote pilote = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer idPilote = null;
		Date dateVVM = null;
		LocalDate localDateVVM = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PiloteDAOImpl.GET_PILOTE_FROM_ID,true,idMembre);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				idPilote = resultSet.getInt("idpilote");
				dateVVM = resultSet.getDate("datevaliditevisitemedicale");
				localDateVVM = DateUtil.parse(dateVVM);
				pilote = new Pilote(idPilote, localDateVVM);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return pilote;
	}

	@Override
	public Integer ajouterPilote(Integer idMembre, Pilote pilote) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer statut = null;
		Integer idPilote = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PiloteDAOImpl.AJOUTER_PILOTE,true,
					idMembre, pilote.getDateVVM());
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion du pilote, aucune ligne n'a été modifiée");
			}
			resultSet=statement.getGeneratedKeys();
			if(resultSet.next()){
				idPilote=resultSet.getInt(1);
			}else{
				throw new DAOException("Aucun pilote n'a ete créée en base, ID non récupéré");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return idPilote;
	}

	@Override
	public Integer editerPilote(Integer idMembre, Pilote nouvPilote) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer idPilote = null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,PiloteDAOImpl.EDITER_PILOTE,true,
					nouvPilote.getDateVVM(), idMembre);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la modification du pilote, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
		return idPilote;
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
