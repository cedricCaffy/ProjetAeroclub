package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import model.classes.membres.Membre;
import bd.ConnexionBD;

public class MembresDAOImpl implements MembresDAO{
	private static final String GET_MEMBRE_BY_LOGIN = "SELECT * FROM MEMBRE WHERE login=?";
	private static final String GET_DROITS_BY_IDMEMBRE = "SELECT * FROM DROITS WHERE idmembre=?";
	private static final String AJOUTER_MEMBRE = "INSERT INTO MEMBRE (nom,prenom,idadr,email,numtel,datenaissance,idaeroclub,login,mdp) VALUES (?,?,?,?,?,?,1,?,?)";
	private static final String EDITER_MEMBRE = "UPDATE MEMBRE SET nom=?, prenom=?, idadr=?, email=?, numtel=?, datenaissance=?, login=?, mdp=? WHERE idmembre=?";
	private static final String SUPPRIMER_MEMBRE = "DELETE FROM MEMBRE WHERE idmembre=?";
	private ConnexionBD connexion;

	public MembresDAOImpl(ConnexionBD connexion){
		this.connexion = ConnexionBD.getInstance();
	}

	/**
	 * Recupere un membre par son login
	 */
	public Membre getMembreByLogin(String login) throws DAOException{
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Membre membre=null;
		Integer idMembre=null;
		String nom=null;;
		String prenom=null;
		String motDePasse=null;
		List<String> droits;
		double solde;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.GET_MEMBRE_BY_LOGIN,true,login);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				idMembre=resultSet.getInt("idmembre");
				nom=resultSet.getString("nom");
				prenom=resultSet.getString("prenom");
				motDePasse=resultSet.getString("mdp");
				droits=getDroitsByIdMembre(idMembre);
				solde=resultSet.getDouble("solde");
				membre=new Membre(idMembre,nom,prenom,motDePasse,droits,solde);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(resultSet,preparedStatement,connexion);
		}
		return membre;
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
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.GET_DROITS_BY_IDMEMBRE,true,idMembre);
			resultSet=statement.executeQuery();
			if(resultSet.next()){
				droits.add(resultSet.getString("administrateur"));
				droits.add(resultSet.getString("mecanicien"));
				droits.add(resultSet.getString("pilote"));
				droits.add(resultSet.getString("instructeur"));
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return droits;
	}

	/**
	 * Ajoute un nouveau membre a la bdd
	 * @param membre le mebre que l'on veu ajouter
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void ajouterMembre(Membre membre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.EDITER_MEMBRE,true,
					membre.getNom(),membre.getPrenom(),null,membre.getEmail(),membre.getNumeroTelephone(),
					membre.getDateNaissance(),membre.getLogin(),membre.getMotDePasse());
			resultSet=statement.executeQuery();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	/**
	 * Modifie un membre connu par son idMembre avec un nouveau membre passe en parametre
	 * @param idMembre id membre qui faut modifier
	 * @param nouvMembre nouveau membre pour remplacer l'ancien
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void editerMembre(Integer idMembre, Membre nouvMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.AJOUTER_MEMBRE,true,
					nouvMembre.getNom(),nouvMembre.getPrenom(),null,nouvMembre.getEmail(),nouvMembre.getNumeroTelephone(),
					nouvMembre.getDateNaissance(),nouvMembre.getLogin(),nouvMembre.getMotDePasse(),idMembre);
			resultSet=statement.executeQuery();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	/**
	 * Supprime un membre a partir de son idpasse en parametre
	 * @param idMembre id du membre à supprimer
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void supprimerMembre(Integer idMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.SUPPRIMER_MEMBRE,true,idMembre);
			resultSet=statement.executeQuery();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}
}
