package bd;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {
	private String url;
	private String login;
	private String passwd;
	private java.sql.Connection connexion;
	private static ConnexionBD INSTANCE = new ConnexionBD();
	public ConnexionBD(){
		this.url="jdbc:mysql://localhost/aeroclub?useSSL=false";
		this.login="root";
		this.passwd="";
	}
	
	public static ConnexionBD getInstance(){
		if(INSTANCE==null){
			INSTANCE = new ConnexionBD();
		}
		return INSTANCE;
	}
	
	public void close() throws SQLException{
		this.connexion.close();
	}

	public java.sql.Connection getConnexion() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connexion=DriverManager.getConnection(url,login,passwd);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return this.connexion;
	}
}
