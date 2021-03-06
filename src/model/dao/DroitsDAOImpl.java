package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.ConnexionBD;
import exceptions.DAOException;

public class DroitsDAOImpl implements DroitsDAO {

	private static final String GET_DROITS_BY_IDMEMBRE = "SELECT * FROM DROITS WHERE idmembre=?";
	private static final String AJOUTER_DROITS = "INSERT INTO DROITS (instructeur,administrateur,mecanicien,pilote,idmembre)VALUES (?,?,?,?,?)";
	private static final String EDITER_DROITS = "UPDATE DROITS SET instructeur=?, administrateur=?, mecanicien=?, pilote=? WHERE idmembre=?";
	private static final String SUPPRIMER_DROITS = "DELETE FROM DROITS WHERE idmembre=?";
	private ConnexionBD connexion;

	public DroitsDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	/**
	 * Recupere les droits pour l'identifiant du membre passe en parametre
	 */
	public List<String> getDroitsByIdMembre(Integer idMembre) throws DAOException{
		List<String> droits;
		droits=new ArrayList<String>();
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try{
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,DroitsDAOImpl.GET_DROITS_BY_IDMEMBRE,true,idMembre);
			resultSet=statement.executeQuery();
			if(resultSet.next()){
				droits.add(resultSet.getString("instructeur"));
				droits.add(resultSet.getString("administrateur"));
				droits.add(resultSet.getString("mecanicien"));
				droits.add(resultSet.getString("pilote"));
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return droits;
	}

	@Override
	public void ajouterDroits(Integer idMembre, List<String> droits) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		System.out.println(idMembre);
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,DroitsDAOImpl.AJOUTER_DROITS,true,
					droits.get(0), droits.get(1), droits.get(2), droits.get(3),idMembre);
			setNullIfNecessary(statement,droits);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion des droits, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	@Override
	public void editerDroits(Integer idMembre, List<String> nouvDroits) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,DroitsDAOImpl.EDITER_DROITS,true,
					nouvDroits.get(0), nouvDroits.get(1), nouvDroits.get(2), nouvDroits.get(3), idMembre);
			setNullIfNecessary(statement,nouvDroits);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion des droits, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	private void setNullIfNecessary(PreparedStatement ps,List<String> droits) throws SQLException{
		int i=0;
		for(String droit : droits){
			if(droit.equals("NULL")){
				ps.setNull(i+1,java.sql.Types.VARCHAR);
			}
			i++;
		}
	}
	@Override
	public void supprimerDroits(Integer idMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,DroitsDAOImpl.SUPPRIMER_DROITS,true,idMembre);
			resultSet=statement.executeQuery();
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

}
