package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import view.popup.PopupError;
import bd.ConnexionBD;
import model.classes.aeroclub.Aeroclub;

public class AeroclubDAO {
	ConnexionBD connexion;
	public AeroclubDAO(){
		connexion=ConnexionBD.getInstance();
	}
	
	public String getNomAeroclub(){
		String nomAeroclub="";
		ResultSet rs;
		try {
			java.sql.Statement statement=connexion.getConnexion().createStatement();
			String sql = "SELECT nom FROM AEROCLUB";
			rs=statement.executeQuery(sql);
			rs.next();
			nomAeroclub=rs.getString("nom");
			connexion.close();
		} catch (SQLException e) {
			new PopupError("Base de données","Erreur de base de données",e.getMessage());
		}
		return nomAeroclub;
	}
}
