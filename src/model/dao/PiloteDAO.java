package model.dao;

import java.sql.Date;

import exceptions.DAOException;

public interface PiloteDAO {

	void ajouterPilote(Integer idMembre, Date dateValiditeVisiteMedicale) throws DAOException;

	Integer getIdDernierPilote() throws DAOException;

	Integer editerPilote(Integer idMembre, Date nouvDateVVM) throws DAOException;

	void supprimerPilote(Integer idMembre) throws DAOException;

}
