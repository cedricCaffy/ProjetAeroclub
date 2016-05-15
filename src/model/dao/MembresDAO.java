package model.dao;

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
	 * Recupere les droits d'un membre
	 * @param idMembre le membre dont on veut recuperer les droits
	 * @return les droits correspondant au membre dont l'identifiant est passe en parametre
	 * @throws DAOException si une erreur d'sql survient
	 */
	List<String> getDroitsByIdMembre(Integer idMembre) throws DAOException;
}
