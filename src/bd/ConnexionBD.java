package bd;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;

import model.dao.AeroclubDAO;
import model.dao.AeroclubDAOImpl;
import model.dao.MembresDAO;
import model.dao.MembresDAOImpl;
import exceptions.DAOConfigurationException;

public class ConnexionBD {
	private String url;
	private String login;
	private String passwd;
	private static final String FICHIER_PROPERTIES="properties/DAOPropertie";
	private static final String PROPERTY_URL="url";
	private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "login";
    private static final String PROPERTY_MOT_DE_PASSE    = "password";
	private java.sql.Connection connexion;
	
	private ConnexionBD(String url,String login, String passwd){
		this.url=url;
		this.login=login;
		this.passwd=passwd;
	}
	
	/**
	 * Recupere l'instance de la classe connexionBD
	 * @return l'instance de la connexion à la BD
	 * @throws DAOConfigurationException si pb de chemin d'acces etc...
	 */
	public static ConnexionBD getInstance() throws DAOConfigurationException{
		Properties properties=new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //Chargement du fichier propertie contenant les informations de connexion
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }
        
        try {
        	/*Chargement des donnees contenues dans le propertie*/
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
        	/*Chargement du driver*/
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e);
        }

        ConnexionBD instance = new ConnexionBD( url, nomUtilisateur, motDePasse );
        return instance;
    }

	public Connection getConnexion() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connexion=DriverManager.getConnection(url,login,passwd);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return this.connexion;
	}
	
	/**
	 * Retourne le dao d'aeroclub
	 * @return le dao d'aeroclub
	 */
	public AeroclubDAO getAeroclubDAO(){
		return new AeroclubDAOImpl(this);
	}
	
	/**
	 * Retourne le dao de membre
	 * @return le dao de membre
	 */
	public MembresDAO getMembreDAO(){
		return new MembresDAOImpl(this);
	}
}
