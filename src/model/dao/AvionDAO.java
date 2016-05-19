package model.dao;

import exceptions.DAOException;
import javafx.collections.ObservableList;
import model.classes.avion.Avion;

public interface AvionDAO {

	ObservableList<Avion> getAllAvion() throws DAOException;

	Avion getAvionFromId(Integer id) throws DAOException;

	void ajouterAvion(Avion avion) throws DAOException;

	void editerAvion(Integer idAvion, Avion nouvAvion) throws DAOException;

	void supprimerAvion(Integer idAvion) throws DAOException;

}
