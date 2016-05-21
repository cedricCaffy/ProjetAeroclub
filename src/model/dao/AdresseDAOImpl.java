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
	private static final String GET_ADR_FROM_ID = "SELECT * FROM ADRESSE a JOIN MEMBRE m ON a.idadr=m.idadr WHERE m.idmembre=?";
	private static final String EDITER_ADRESSE = "UPDATE ADRESSE SET rue=?, ville=?, codepostal=?, numero=? WHERE idadr=?";
	private static final String SUPPRIMER_ADRESSE = "DELETE FROM ADRESSE WHERE idadr=?";
	private ConnexionBD connexion;

	public AdresseDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	/**
	 * Ajoute une nouvelle adresse a la bdd
	 * @param adresse l'adresse a inserer
	 * @throws DAOException si une erreur d'sql survient
	 */
	public Integer ajouterAdresse(Adresse adresse) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer statut = null;
		Integer idAdresse;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AdresseDAOImpl.AJOUTER_ADRESSE,true,
					adresse.getRue(), adresse.getVille(), adresse.getCodePostal(), adresse.getNumero()==0 ? null : adresse.getNumero());
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion de l'adresse, aucune ligne n'a été modifiée");
			}
			resultSet=statement.getGeneratedKeys();
			if(resultSet.next()){
				idAdresse=resultSet.getInt(1);
			}else{
				throw new DAOException("Aucune adresse n'a ete créée en base, ID non récupéré");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return idAdresse;
	}

	/**
	 * Retrouve l'adresse a partir de son identifiant
	 * @param idMembre id ddu membre de l'adresse recherchee
	 * @return adresse recherchee
	 * @throws DAOException
	 */
	public Adresse getAdrFromId(Integer idMembre) throws DAOException {
		Adresse adresse = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer idAdresse = null;
		String rue = null;
		String ville = null;
		String codePostal = null;
		Integer numeroRue = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AdresseDAOImpl.GET_ADR_FROM_ID,true,idMembre);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				idAdresse = resultSet.getInt("a.idadr");
				rue = resultSet.getString("a.rue");
				ville = resultSet.getString("a.ville");
				codePostal = resultSet.getString("a.codepostal");
				numeroRue = resultSet.getInt("a.numero");
				adresse = new Adresse(idAdresse,rue,ville,codePostal,numeroRue);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return adresse;
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
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AdresseDAOImpl.EDITER_ADRESSE,true,
					nouvAdresse.getRue(), nouvAdresse.getVille(), nouvAdresse.getCodePostal(), nouvAdresse.getNumero(), idAdresse);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la modification de l'adresse, aucune ligne n'a été modifiée");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
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
