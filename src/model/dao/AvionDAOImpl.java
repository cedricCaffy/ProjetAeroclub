package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.ConnexionBD;
import exceptions.DAOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.classes.avion.Avion;
import model.classes.avion.Disponibilite;

public class AvionDAOImpl implements AvionDAO {

    private static final String GET_ALL_AVION = "SELECT idavion, nomavion, immatriculation FROM AVION;";
    private static final String GET_AVION_FROM_ID = "SELECT * FROM AVION WHERE idavion=?";
	private static final String AJOUTER_AVION = "INSERT INTO AVION (nomavion,typeavion,immatriculation,consommation,capacitereservoir,nbplace,massemaximale,couthoraire,disponibilite,vitessecroisiere,idaeroclub) VALUES (?,?,?,?,?,?,?,?,?,?,1)";
	private static final String EDITER_AVION = "UPDATE AVION SET nomavion=?,typeavion=?,immatriculation=?,consommation=?,capacitereservoir=?,nbplace=?,massemaximale=?,couthoraire=?,vitessecroisiere=? WHERE idavion=?";
	private static final String SUPPRIMER_AVION = "DELETE FROM AVION WHERE idavion=?";
	private ConnexionBD connexion;

	public AvionDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	public ObservableList<Avion> getAllAvion() throws DAOException {
		Avion avion;
		List<Avion> listAvion = new ArrayList<Avion>();
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer idAvion = null;
		String nomAvion = null;
		String immatriculation = null;
		ObservableList<Avion> list = FXCollections.observableList(listAvion);
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AvionDAOImpl.GET_ALL_AVION,true,(Object)null);
			resultSet=statement.executeQuery();
			while (resultSet.next()) {
				idAvion = resultSet.getInt("idavion");
				nomAvion = resultSet.getString("nomavion");
				immatriculation = resultSet.getString("immatriculation");
				avion = new Avion(idAvion, nomAvion, immatriculation);
				list.add(avion);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return list;
	}

	public Avion getAvionFromId(Integer id) throws DAOException {
		Avion avion = null;
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer idAvion = null;
		String nom = null;
		String type = null;
		String immatriculation = null;
		Integer consommation = null;
		Integer capaciteReservoir = null;
		Integer nombrePlace = null;
		Integer masseMaximale = null;
		Double coutHoraire = null;
		Double vitesseCroisiere = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AvionDAOImpl.GET_AVION_FROM_ID,true,id);
			resultSet=statement.executeQuery();
			if (resultSet.next()) {
				idAvion = resultSet.getInt("idavion");
				nom = resultSet.getString("nomavion");
				type = resultSet.getString("typeavion");
				immatriculation = resultSet.getString("immatriculation");
				consommation = resultSet.getInt("consommation");
				capaciteReservoir = resultSet.getInt("capacitereservoir");
				nombrePlace = resultSet.getInt("nbplace");
				masseMaximale = resultSet.getInt("massemaximale");
				coutHoraire = resultSet.getDouble("couthoraire");
				vitesseCroisiere = resultSet.getDouble("vitessecroisiere");
				avion = new Avion(idAvion,nom,type,immatriculation,consommation,capaciteReservoir,nombrePlace,
						masseMaximale,coutHoraire,Disponibilite.Disponible,vitesseCroisiere,null,null);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return avion;
	}

	@Override
	public void ajouterAvion(Avion avion) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer statut;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AvionDAOImpl.AJOUTER_AVION,true,
					avion.getNom(),avion.getType(),avion.getImmatriculation(),avion.getConsommation(),avion.getCapaciteReservoir(),
					avion.getNombrePlace(),avion.getMasseMaximale(),avion.getCoutHoraire(),"DISPONIBLE",avion.getVitesseCroisiere());
			statut=statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion de l'avion, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	@Override
	public void editerAvion(Integer idAvion, Avion nouvAvion) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer statut;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AvionDAOImpl.EDITER_AVION,true,
					nouvAvion.getNom(),nouvAvion.getType(),nouvAvion.getImmatriculation(),nouvAvion.getConsommation(),nouvAvion.getCapaciteReservoir(),
					nouvAvion.getNombrePlace(),nouvAvion.getMasseMaximale(),nouvAvion.getCoutHoraire(),nouvAvion.getVitesseCroisiere(),idAvion);
			statut=statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'édition de l'avion, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	@Override
	public void supprimerAvion(Integer idAvion) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer statut;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,AvionDAOImpl.SUPPRIMER_AVION,true,idAvion);
			statut=statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la suppresion de l'avion, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

}
