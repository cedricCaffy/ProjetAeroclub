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
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String nomAeroclub=null;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion,AeroclubDAOImpl.requeteGetNomAeroclub,true,(Object)null);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				nomAeroclub=resultSet.getString("nom");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(resultSet,preparedStatement,connexion);
		}
		return nomAeroclub;
	}
}
