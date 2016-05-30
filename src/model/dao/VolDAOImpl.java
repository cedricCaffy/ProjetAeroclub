package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bd.ConnexionBD;
import exceptions.DAOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.classes.vol.TypeVol;
import model.classes.vol.Vol;
import util.DateUtil;

public class VolDAOImpl implements VolDAO {

	private ConnexionBD connexion;
	private static final String GET_VOLS_FROM_MEMBRE = "SELECT datevol, aeroclubdepart,aeroclubarrivee, typevol, tempsvol FROM VOL v "
			+ "JOIN PILOTE p ON v.idpilote = p.idpilote "
			+ "WHERE p.idmembre=? "
			+ "ORDER BY datevol DESC";
	//private static final String SQL_INSERT_VOL = "INSERT INTO VOL (typevol, datevol, tempsvol, nbpassagers, aeroclubdepart, aeroclubarrivee, idavion) VALUES (?,?,?,?,?,?,?);";

	public VolDAOImpl(ConnexionBD connexion) {
		this.connexion = ConnexionBD.getInstance();
	}

	/**
	 * Retourne la liste des vols relatifs au membre connecte
	 * @param idMembre id du membre connecte
	 * @return la liste de ses vols
	 * @throws DAOException
	 */
	public ObservableList<Vol> getVolsFromMembre(Integer idMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Vol vol;
		List<Vol> listeVol = new ArrayList<Vol>();
		Date dateVol = null;
		LocalDate localDateVol = null;
		String aeroclubDepart = null;
		String aeroclubArrivee = null;
		String typeVol = null;
		TypeVol type;
		LocalTime tempsVol = null;
		ObservableList<Vol> list = FXCollections.observableList(listeVol);
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,VolDAOImpl.GET_VOLS_FROM_MEMBRE,true,idMembre);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				dateVol = resultSet.getDate("datevol");
				localDateVol = DateUtil.parse(dateVol);
				aeroclubDepart = resultSet.getString("aeroclubdepart");
				aeroclubArrivee = resultSet.getString("aeroclubarrivee");
				typeVol = resultSet.getString("typevol");
				type=TypeVol.valueOf(typeVol);
				tempsVol = resultSet.getTime("tempsvol").toLocalTime();
				vol = new Vol(localDateVol,tempsVol,aeroclubDepart,aeroclubArrivee,type,0);
				list.add(vol);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return list;
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
