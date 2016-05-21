package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DAOException;
import bd.ConnexionBD;

public class AeroclubDAOImpl implements AeroclubDAO{
	private ConnexionBD connexion;
	private static String requeteGetNomAeroclub="SELECT nom FROM Aeroclub";
	
	/**
	 * Constructeur
	 * @param connexion la connexion a la BDD
	 */
	public AeroclubDAOImpl(ConnexionBD connexion){
		this.connexion=connexion;
	}
	
	/**
	 * Recupere le nom de l'aeroclub
	 */
	@Override
	public String getNomAeroclub() throws DAOException{
		Connection connexion=null;//Contient la connexion a la BDD
		PreparedStatement preparedStatement=null; //contiendra la requete preparee SQL
		ResultSet resultSet=null; //Contient le resultat de la requete
		String nomAeroclub=null;  // Contient le nom de l'aeroclub
		try{
			connexion=this.connexion.getConnexion();//Connexion a la BDD
			//Initialisation de la requete preparee
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion,AeroclubDAOImpl.requeteGetNomAeroclub,true,(Object)null);
			//Execution de la requete
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				//Resultat trouve
				nomAeroclub=resultSet.getString("nom");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			//Fermeture propre de la connexion, du resultSet et du preparedStatement
			DAOUtilitaire.fermeturesSilencieuses(resultSet,preparedStatement,connexion);
		}
		return nomAeroclub;
	}
}
