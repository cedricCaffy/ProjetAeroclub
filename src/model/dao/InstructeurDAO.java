package model.dao;

import exceptions.DAOException;

public interface InstructeurDAO {

	void ajouterInstructeur(Integer idPilote, String numeroInstructeur, Double coutHoraire) throws DAOException;

	void editerInstructeur(Integer idPilote, String numeroInstructeur, Double coutHoraire) throws DAOException;

	void supprimerInstructeur(Integer idPilote) throws DAOException;


}
