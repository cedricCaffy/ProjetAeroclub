package model.dao;

import exceptions.DAOException;
import model.classes.membres.Adresse;

public interface AdresseDAO {

	/**
	 * Ajoute une nouvelle adresse a la bdd
	 * @param adresse l'adresse a inserer
	 * @throws DAOException si une erreur d'sql survient
	 */
	void ajouterAdresse(Adresse adresse) throws DAOException;

	/**
	 * Retrouve l'id le plus recent de l'adresse passee en parametre
	 * @param adresse l'adresse dont on veut l'id
	 * @return l'id le plus recent de l'adresse
	 * @throws DAOException si une erreur d'sql survient
	 */
	Integer getIdFromAdresse(Adresse adresse) throws DAOException;

	/**
	 * Modifie une adresse connu par son id avec une nouvelle adresse
	 * @param idAdresse id de l'adresse a modifier
	 * @param nouvAdresse nouvelle adresse pour remplacer l'ancienne
	 * @throws DAOException si une erreur d'sql survient
	 */
	void editerAdresse(Integer idAdresse, Adresse nouvAdresse) throws DAOException;

	/**
	 * Supprime une adresse a partir de son id
	 * @param idAdresse id de l'adresse a supprimer
	 * @throws DAOException si une erreur d'sql survient
	 */
	void supprimerAdresse(Integer idAdresse) throws DAOException;

}
