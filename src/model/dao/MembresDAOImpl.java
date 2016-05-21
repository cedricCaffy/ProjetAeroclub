package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.classes.membres.Adresse;
import model.classes.membres.Membre;
import util.DateUtil;
import bd.ConnexionBD;

public class MembresDAOImpl implements MembresDAO{
	private static final String GET_MEMBRE_BY_LOGIN = "SELECT * FROM MEMBRE WHERE login=?";
	private static final String GET_ALL_MEMBRE= "SELECT idmembre,nom, prenom, numtel, email FROM MEMBRE";
	private static final String GET_MEMBRE_FROM_ID = "SELECT * FROM MEMBRE WHERE idmembre=?";
	private static final String AJOUTER_MEMBRE = "INSERT INTO MEMBRE (nom,prenom,idadr,email,numtel,nummobile,datenaissance,solde,idaeroclub,login,mdp) VALUES (?,?,?,?,?,?,?,?,1,?,?)";
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
	 * Recupere le membre a partir de son identifiant
	 * @param idMembre id du membre recherche
	 * @return le membre recherche
	 * @throws DAOException
	 */
	public Membre getMembreFromId(Integer idMembre) throws DAOException {
		Membre membre = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String nom = null;
		String prenom = null;
		Date dateNaissance = null;
		LocalDate localDateNaissance = null;
		String numTel = null;
		String numMobile = null;
		String email = null;
		String login = null;
		String motDePasse = null;
		Integer idAdresse = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.GET_MEMBRE_FROM_ID,true,idMembre);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				nom = resultSet.getString("nom");
				prenom = resultSet.getString("prenom");
				numTel = resultSet.getString("numtel");
				email = resultSet.getString("email");
				dateNaissance = resultSet.getDate("datenaissance");
				localDateNaissance = DateUtil.parse(dateNaissance);
				numMobile = resultSet.getString("nummobile");
				login = resultSet.getString("login");
				motDePasse = resultSet.getString("mdp");
				idAdresse = resultSet.getInt("idadr");
				membre = new Membre(idMembre,nom,prenom,login,motDePasse,email,numTel,numMobile,localDateNaissance,0,null,new Adresse(idAdresse),null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return membre;
	}

	/**
	 * Ajoute un nouveau membre a la bdd
	 * @param membre le mebre que l'on veut ajouter
	 * @throws DAOException si une erreur d'sql survient
	 */
	public Integer ajouterMembre(Membre membre, Integer idAdresse) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Integer idMembre = null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.AJOUTER_MEMBRE,true,
					membre.getNom(),membre.getPrenom(),idAdresse,membre.getEmail(),membre.getNumeroTelephone(),
					membre.getNumeroMobile(),membre.getDateNaissance(),membre.getSolde(),membre.getLogin(),membre.getMotDePasse());
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion du membre, aucune ligne n'a été modifiée");
			}
			resultSet=statement.getGeneratedKeys();
			if(resultSet.next()){
				idMembre=resultSet.getInt(1);
			}else{
				throw new DAOException("Aucun membre n'a ete crée en base, ID non récupéré");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return idMembre;
	}

	/**
	 * Modifie un membre connu par son idMembre avec un nouveau membre passe en parametre
	 * @param idMembre id membre qui faut modifier
	 * @param nouvMembre nouveau membre pour remplacer l'ancien
	 * @throws DAOException si une erreur d'sql survient
	 */
	public void editerMembre(Integer idMembre, Membre nouvMembre) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,MembresDAOImpl.EDITER_MEMBRE,true,
					nouvMembre.getNom(),nouvMembre.getPrenom(),nouvMembre.getAdresse().getIdAdresse(),nouvMembre.getEmail(),nouvMembre.getNumeroTelephone(),
					nouvMembre.getNumeroMobile(),nouvMembre.getDateNaissance(),nouvMembre.getLogin(),nouvMembre.getMotDePasse(),idMembre);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la modification du membre, aucune ligne n'a été modifiée");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
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
		try {
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
