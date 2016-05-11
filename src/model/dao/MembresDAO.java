package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import model.classes.membres.Membre;
import bd.ConnexionBD;

public class MembresDAO {
	private ObservableList<Membre> membres;
	ConnexionBD connexion;
	public MembresDAO(){
		connexion = ConnexionBD.getInstance();
	}
	
	public ObservableList<Membre> getMembres(){
		return this.membres;
	}
	public Membre getMembreById(int idMembre){
		return membres.get(idMembre); 
	}
	
	public Membre getMembreByLogin(String login) throws SQLException{
		Membre membre=null;
		Integer idMembre;
		String nom;
		String prenom;
		String motDePasse;
		List<String> droits;
		ResultSet rs;
		java.sql.Statement statement=connexion.getConnexion().createStatement();
		String sql = "SELECT * FROM MEMBRE WHERE login='"+login+"'";
		rs=statement.executeQuery(sql);
		if(rs.next()){
			idMembre=rs.getInt("idmembre");
			nom=rs.getString("nom");
			prenom=rs.getString("prenom");
			motDePasse=rs.getString("mdp");
			droits=getDroitsByIdMembre(idMembre);
			membre=new Membre(idMembre,nom,prenom,motDePasse,droits);
		}
		return membre;
	}
	
	public List<String> getDroitsByIdMembre(Integer idMembre) throws SQLException{
		List<String> droits;
		ResultSet rs;
		droits=new ArrayList<String>();
		java.sql.Statement statement=connexion.getConnexion().createStatement();
		String sql="SELECT * FROM DROITS WHERE idmembre="+idMembre.toString();
		rs=statement.executeQuery(sql);
		rs.next();
		droits.add(rs.getString("administrateur"));
		droits.add(rs.getString("mecanicien"));
		droits.add(rs.getString("pilote"));
		droits.add(rs.getString("instructeur"));
		return droits;
	}	
}
