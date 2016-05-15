package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import view.popup.PopupError;

public class DAOUtilitaire {
	
	/**
	 * Initialise un requete preparee afin d'eviter les injections sql
	 * @param connexion la connexion sur laquelle on effectue la requete preparee
	 * @param sql la requete sql
	 * @param returnGeneratedKeys si on souhaite que la requete retourne l'identifiant de l'objet insere dans la BD
	 * @param objets les objets a inserer dans la requete preparee
	 * @return le preparedStatement afin d'etre execute
	 * @throws SQLException si une erreur sql survient
	 */
	public static PreparedStatement initialiserRequetePreparee(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
	    if(objets[0]!=null){
	    	for ( int i = 0; i < objets.length; i++ ) {
	    		preparedStatement.setObject( i + 1, objets[i] );
	 	    }
	    }
	    return preparedStatement;
	}
	
	/**
	 * Fermeture du resultSet
	 * @param resultSet le resultSet a fermer
	 */
	public static void fermetureSilencieuse( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            new PopupError("Erreur de fermeture du resultSet","Echec","Échec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}

	/**
	 * Fermeture du statement
	 * @param statement le statement a fermer
	 */
	public static void fermetureSilencieuse( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            new PopupError("Erreur de fermeture du Statement","Echec","Échec de la fermeture du Statement : " + e.getMessage());
	        }
	    }
	}

	/**
	 * Fermeture de la connexion
	 * @param connexion la connexion a fermer
	 */
	public static void fermetureSilencieuse( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            new PopupError("Erreur de fermeture de la connexion","Echec", "Échec de la fermeture de la connexion : " + e.getMessage() );
	        }
	    }
	}

	/**
	 * Ferme le statement et la connexion
	 * @param statement le statement a fermer
	 * @param connexion la connexion a fermer
	 */
	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}

	/**
	 * Ferme tout le necessaire a l'execution d'une requete
	 * @param resultSet le resultSet a fermer
	 * @param statement le statement a fermer
	 * @param connexion la connexion a fermer
	 */
	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
	    fermetureSilencieuse( resultSet );
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}
}
