package model.dao;

import exceptions.DAOException;
import javafx.collections.ObservableList;
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
	ObservableList<Membre> getAllMembre() throws DAOException;

	/**
	 * Recupere le membre a partir de son identifiant
	 * @param idMembre id du membre recherche
	 * @return le membre recherche
	 * @throws DAOException
	 */
	Membre getMembreFromId(Integer idMembre) throws DAOException;

	/**
	 * Ajoute un nouveau membre a la bdd
	 * @param membre le mebre que l'on veu ajouter
	 * @param idAdresse id de l'adresse correspondant au membre
	 * @return l'id du membre que l'on vient d'ajouter
	 * @throws DAOException si une erreur d'sql survient
	 */
	Integer ajouterMembre(Membre membre, Integer idAdresse) throws DAOException;

	/**
	 * Modifie un membre connu par son idMembre avec un nouveau membre passe en parametre
	 * @param idMembre id membre qui faut modifier
	 * @param nouvMembre nouveau membre pour remplacer l'ancien
	 * @throws DAOException si une erreur d'sql survient
	 */
	void editerMembre(Integer idMembre, Membre nouvMembre) throws DAOException;

	/**
	 * Supprime un membre a partir de son idpasse en parametre
	 * @param idMembre id du membre à supprimer
	 * @throws DAOException si une erreur d'sql survient
	 */
	void supprimerMembre(Integer idMembre) throws DAOException;

}
