package model.dao;

import bd.ConnexionBD;
import exceptions.DAOException;
import model.classes.vol.Vol;

public class VolDAOImpl implements VolDAO {

	private ConnexionBD connexion;
	private static final String SQL_INSERT_VOL = "INSERT INTO VOL (typevol, datevol, tempsvol, nbpassagers, aeroclubdepart, aeroclubarrivee, idavion) VALUES (?,?,?,?,?,?,?);";

	public VolDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion.getInstance();
	}

	/**
	 * Insere un vol au membre associé à l'idMembre passe en parametre
	 * @param paiement le vol a inserer
	 * @param idAvion
	 * @return l'identifiant du vol insere
	 * @throws DAOException si une erreur de bdd survient
	 */
	@Override
	public Integer insererVol(Vol vol, Integer idAvion) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
