package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.ConnexionBD;
import exceptions.DAOException;
import model.classes.membres.Adresse;

public class AdresseDAOImpl implements AdresseDAO {
	private static final String AJOUTER_ADRESSE="INSERT INTO ADRESSE (rue, ville, codepostal, numero) VALUES (?,?,?,?)";
	private static final String GET_ID_FROM_ADRESSE="SELECT idadresse FROM ADRESSE WHERE rue=? AND ville=? AND codepostal=? AND numero=?";
	private static final String EDITER_ADRESSE = "UPDATE MEMBRE SET rue=?, ville=?, codepostal=?, numero=? WHERE idadresse=?";
	private static final String SUPPRIMER_ADRESSE = "DELETE FROM ADRESSE WHERE idadresse=?";
	private ConnexionBD connexion;

	public AdresseDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	/**
	 * Ajoute une nouvelle adresse a la bdd
	 * @param adresse l'adresse a inserer
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void ajouterAdresse(Adresse adresse) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AdresseDAOImpl.AJOUTER_ADRESSE,true,
					adresse.getRue(), adresse.getVille(), adresse.getCodePostal(), adresse.getNumero());
			resultSet=statement.executeQuery();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}

	}

	/**
	 * Retrouve l'id le plus recent de l'adresse passee en parametre
	 * @param adresse l'adresse dont on veut l'id
	 * @return l'id le plus recent de l'adresse
	 * @throws DAOException si une erreur d'sql survient
	 */
	public Integer getIdFromAdresse(Adresse adresse) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer idAdresse = 0;
		try {
			//ajouterAdresse(membre.getAdresse());
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AdresseDAOImpl.GET_ID_FROM_ADRESSE,true,
					adresse.getRue(), adresse.getVille(), adresse.getCodePostal(), adresse.getNumero());
			resultSet=statement.executeQuery();
			while (resultSet.next()) {
				if (idAdresse < resultSet.getInt("idadresse")) {
					idAdresse = resultSet.getInt("idadresse");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return idAdresse;
	}

	/**
	 * Modifie une adresse connu par son id avec une nouvelle adresse
	 * @param idAdresse id de l'adresse a modifier
	 * @param nouvAdresse nouvelle adresse pour remplacer l'ancienne
	 * @throws DAOException si une erreur d'sql survient
	 */
	@Override
	public void editerAdresse(Integer idAdresse, Adresse nouvAdresse) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AdresseDAOImpl.EDITER_ADRESSE,true,
					nouvAdresse.getRue(), nouvAdresse.getVille(), nouvAdresse.getCodePostal(), nouvAdresse.getNumero(), idAdresse);
			resultSet=statement.executeQuery();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	/**
	 * Supprime une adresse a partir de son id
	 * @param idAdresse id de l'adresse a supprimer
	 * @throws DAOException si une erreur d'sql survient
	 */
	@Override
	public void supprimerAdresse(Integer idAdresse) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AdresseDAOImpl.SUPPRIMER_ADRESSE,true,idAdresse);
			resultSet=statement.executeQuery();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

}
