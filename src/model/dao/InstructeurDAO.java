package model.dao;

import exceptions.DAOException;
import model.classes.membres.Instructeur;

public interface InstructeurDAO {

	Instructeur getInstructeurFromId(Integer idPilote) throws DAOException;

	void ajouterInstructeur(Integer idPilote, Instructeur instructeur) throws DAOException;

	void editerInstructeur(Integer idPilote, Instructeur nouvInstruct) throws DAOException;

	void supprimerInstructeur(Integer idPilote) throws DAOException;


}
