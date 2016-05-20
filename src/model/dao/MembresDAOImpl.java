package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.classes.membres.Instructeur;
import model.classes.membres.Membre;
import model.classes.membres.Pilote;
import bd.ConnexionBD;

public class MembresDAOImpl implements MembresDAO{
	private static final String GET_MEMBRE_BY_LOGIN = "SELECT * FROM MEMBRE WHERE login=?";
	private static final String GET_ALL_MEMBRE= "SELECT idmembre,nom, prenom, numtel, email FROM MEMBRE";
	private static final String AJOUTER_MEMBRE = "INSERT INTO MEMBRE (nom,prenom,idadr,email,numtel,nummobile,datenaissance,idaeroclub,login,mdp) VALUES (?,?,?,?,?,?,1,?,?)";
	private static final String GET_ID_DERNIER_MEMBRE = "SELECT MAX(idmembre) FROM MEMBRE";
	private static final String EDITER_MEMBRE = "UPDATE MEMBRE SET nom=?, prenom=?, idadr=?, email=?, numtel=?, nummobile=?, datenaissance=?, login=?, mdp=? WHERE idmembre=?";
	private static final String SUPPRIMER_MEMBRE = "DELETE FROM MEMBRE WHERE idmembre=?";
	private ConnexionBD connexion;

	public MembresDAOImpl(ConnexionBD connexion){
		this.connexion = ConnexionBD.getInstance();
	}

	/**
	 * Recupere un membre par son login
	 */
	public Membre getMembreByLogin(String login) throws DAOException{
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Membre membre=null;
		Integer idMembre=null;
		String nom=null;;
		String prenom=null;
		String motDePasse=null;
		List<String> droits;
		DroitsDAO droit = new DroitsDAOImpl(this.connexion);
		double solde;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.GET_MEMBRE_BY_LOGIN,true,login);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				idMembre=resultSet.getInt("idmembre");
				nom=resultSet.getString("nom");
				prenom=resultSet.getString("prenom");
				motDePasse=resultSet.getString("mdp");
				droits=droit.getDroitsByIdMembre(idMembre);
				solde=resultSet.getDouble("solde");
				membre=new Membre(idMembre,nom,prenom,motDePasse,droits,solde);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(resultSet,preparedStatement,connexion);
		}
		return membre;
	}



	/**
	 * Recupere la liste de tous les membres
	 * @return la liste des membres
	 * @throws DAOException
	 */
	public ObservableList<Membre> getAllMembre() throws DAOException {
		List<Membre> listMembre;
		listMembre = new ArrayList<Membre>();
		Membre membre = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer idMembre;
		String nom = null;
		String prenom = null;
		String numtel = null;
		String email = null;
		ObservableList<Membre> list = FXCollections.observableList(listMembre);
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.GET_ALL_MEMBRE,true,(Object)null);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				idMembre = resultSet.getInt("idmembre");
				nom = resultSet.getString("nom");
				prenom = resultSet.getString("prenom");
				numtel = resultSet.getString("numtel");
				email = resultSet.getString("email");
				membre = new Membre(idMembre, nom, prenom, numtel, email);
				list.add(membre);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return list;
	}

	/**
	 * Renvoie l'id du membre que l'on vient d'insérer
	 * @return l'id du membre
	 * @throws DAOException si une erreur de requete survient
	 */
	public Integer getIdDernierMembre() throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer idMembre;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.GET_ID_DERNIER_MEMBRE,true);
			resultSet=statement.executeQuery();
			idMembre = resultSet.getInt("idmembre");
		} catch(SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return idMembre;
	}

	/**
	 * Ajoute un nouveau membre a la bdd
	 * @param membre le mebre que l'on veut ajouter
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void ajouterMembre(Membre membre, Date dateVVM) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		AdresseDAO adresse = new AdresseDAOImpl(this.connexion);
		DroitsDAO droit = new DroitsDAOImpl(this.connexion);
		PiloteDAO pilote = new PiloteDAOImpl(this.connexion);
		InstructeurDAO instructeur = new InstructeurDAOImpl(this.connexion);
		Integer idAdresse;
		Integer idMembre;
		Integer idPilote;
		try {
			adresse.ajouterAdresse(membre.getAdresse());
			idAdresse = adresse.getIdDerniereAdresse();
			membre.getAdresse().setIdAdresse(idAdresse);
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.EDITER_MEMBRE,true,
					membre.getNom(),membre.getPrenom(),membre.getAdresse().getIdAdresse(),membre.getEmail(),membre.getNumeroTelephone(),
					membre.getNumeroMobile(),membre.getDateNaissance(),membre.getLogin(),membre.getMotDePasse());
			resultSet=statement.executeQuery();
			idMembre = getIdDernierMembre();
			droit.ajouterDroits(idMembre, membre.getDroits());
			if (membre instanceof Pilote) {
				pilote.ajouterPilote(idMembre, dateVVM);
				if (membre instanceof Instructeur) {
					idPilote = pilote.getIdDernierPilote();
					instructeur.ajouterInstructeur(idPilote, ((Instructeur) membre).getNumeroInstructeur(), ((Instructeur) membre).getCoutHoraire());
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	/**
	 * Modifie un membre connu par son idMembre avec un nouveau membre passe en parametre
	 * @param idMembre id membre qui faut modifier
	 * @param nouvMembre nouveau membre pour remplacer l'ancien
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void editerMembre(Integer idMembre, Membre nouvMembre, Date nouvDateVVM) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		DroitsDAO droit = new DroitsDAOImpl(this.connexion);
		PiloteDAO pilote = new PiloteDAOImpl(this.connexion);
		InstructeurDAO instructeur = new InstructeurDAOImpl(this.connexion);
		Integer idPilote;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.AJOUTER_MEMBRE,true,
					nouvMembre.getNom(),nouvMembre.getPrenom(),nouvMembre.getAdresse().getIdAdresse(),nouvMembre.getEmail(),nouvMembre.getNumeroTelephone(),
					nouvMembre.getNumeroMobile(),nouvMembre.getDateNaissance(),nouvMembre.getLogin(),nouvMembre.getMotDePasse(),idMembre);
			resultSet=statement.executeQuery();
			droit.editerDroits(idMembre, nouvMembre.getDroits());
			if (nouvMembre instanceof Pilote) {
				pilote.editerPilote(idMembre, nouvDateVVM);
				if (nouvMembre instanceof Instructeur) {
					idPilote = pilote.getIdDernierPilote();
					instructeur.editerInstructeur(idPilote, ((Instructeur) nouvMembre).getNumeroInstructeur(), ((Instructeur) nouvMembre).getCoutHoraire());
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}

	/**
	 * Supprime un membre a partir de son idpasse en parametre
	 * @param idMembre id du membre à supprimer
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void supprimerMembre(Integer idMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		DroitsDAO droit = new DroitsDAOImpl(this.connexion);
		try {
			droit.supprimerDroits(idMembre);
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.SUPPRIMER_MEMBRE,true,idMembre);
			resultSet=statement.executeQuery();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
	}
}
