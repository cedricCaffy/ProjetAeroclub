package model.dao;

import java.sql.Date;
import java.util.List;

import exceptions.DAOException;
import model.classes.membres.Membre;

public interface MembresDAO {
	/**
	 * Recupere le membre par le login
	 * @param login le login du membre a recuperer
	 * @return le membre correspondant au login passe en parametre
	 * @throws DAOException si une erreur de requete survient
	 */
	Membre getMembreByLogin(String login) throws DAOException;

	/**
	 * Recupere la liste de tous les membres
	 * @return la liste des membres
	 * @throws DAOException si une erreur de requete survient
	 */
	List<Membre> getAllMembre() throws DAOException;

	/**
	 * Renvoie l'id du membre que l'on vient d'insérer
	 * @return l'id du membre
	 * @throws DAOException si une erreur de requete survient
	 */
	Integer getIdDernierMembre() throws DAOException;

	/**
	 * Ajoute un nouveau membre a la bdd
	 * @param membre le mebre que l'on veu ajouter
	 * @throws DAOException si une erreur d'sql survient
	 */
	void ajouterMembre(Membre membre, Date dateVVM) throws DAOException;

	/**
	 * Modifie un membre connu par son idMembre avec un nouveau membre passe en parametre
	 * @param idMembre id membre qui faut modifier
	 * @param nouvMembre nouveau membre pour remplacer l'ancien
	 * @throws DAOException si une erreur d'sql survient
	 */
	void editerMembre(Integer idMembre, Membre nouvMembre, Date nouvDateVVM) throws DAOException;

	/**
	 * Supprime un membre a partir de son idpasse en parametre
	 * @param idMembre id du membre à supprimer
	 * @throws DAOException si une erreur d'sql survient
	 */
	void supprimerMembre(Integer idMembre) throws DAOException;

}
