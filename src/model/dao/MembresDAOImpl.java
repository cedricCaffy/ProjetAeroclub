package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import javafx.beans.property.DoubleProperty;
import model.classes.membres.Membre;
import bd.ConnexionBD;

public class MembresDAOImpl implements MembresDAO{
	private static final String GET_MEMBRE_BY_LOGIN = "SELECT * FROM MEMBRE WHERE login=?";
	private static final String GET_DROITS_BY_IDMEMBRE = "SELECT * FROM DROITS WHERE idmembre=?";
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
}
