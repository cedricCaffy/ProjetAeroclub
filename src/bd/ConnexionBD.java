package bd;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnexionBD {
	private String url;
	private String login;
	private String passwd;
	private java.sql.Connection connexion;
	private static ConnexionBD INSTANCE = new ConnexionBD();
	public ConnexionBD(){
		this.url="jdbc:mysql://localhost/aeroclub";
		this.login="root";
		this.passwd="";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connexion=DriverManager.getConnection(url,login,passwd);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
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


	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public java.sql.Connection getConnexion(){
		return this.connexion;
	}
}
