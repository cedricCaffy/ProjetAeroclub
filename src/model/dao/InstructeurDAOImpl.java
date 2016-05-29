package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import bd.ConnexionBD;
import exceptions.DAOException;
import model.classes.membres.Instructeur;

public class InstructeurDAOImpl implements InstructeurDAO {

	private static final String GET_INSTRUCTEUR_FROM_ID = "SELECT * FROM INSTRUCTEUR WHERE idpilote=?";
	private static final String AJOUTER_INSTRUCTEUR = "INSERT INTO INSTRUCTEUR VALUES (?,?,?)";
	private static final String EDITER_INSTRUCTEUR = "UPDATE INSTRUCTEUR SET numeroinstructeur=?, couthoraire=? WHERE idpilote=?";
	private static final String SUPPRIMER_INSTRUCTEUR = "DELETE FROM INSTRUCTEUR WHERE idpilote=?";
	private static final String GET_ALL_INSTRUCTEUR = "SELECT I.idpilote,nom,prenom,couthoraire FROM INSTRUCTEUR I JOIN PILOTE P "
			+ "ON I.idPilote=P.idPilote JOIN MEMBRE M ON P.idmembre=M.idmembre";
	private ConnexionBD connexion;

	public InstructeurDAOImpl(ConnexionBD connexion) {
		this.connexion = connexion;
	}

	@Override
	public void ajouterInstructeur(Integer idPilote, Instructeur instructeur) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.AJOUTER_INSTRUCTEUR,true,
					instructeur.getNumeroInstructeur(), instructeur.getCoutHoraire(), idPilote);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de l'insertion de l'instructeur, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	@Override
	public void editerInstructeur(Integer idPilote, Instructeur nouvInstruct) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.EDITER_INSTRUCTEUR,true,
					nouvInstruct.getNumeroInstructeur(), nouvInstruct.getCoutHoraire(), idPilote);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la modification de l'instructeur, aucune ligne n'a été modifiée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	@Override
	public void supprimerInstructeur(Integer idPilote) throws DAOException {
		Connection connexion=null;
		PreparedStatement statement=null;
		Integer statut = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.SUPPRIMER_INSTRUCTEUR,true,idPilote);
			statut = statement.executeUpdate();
			if(statut==0){
				throw new DAOException("Echec de la suppression de l'instructeur, aucune ligne n'a été supprimée");
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(statement,connexion);
		}
	}

	@Override
	public Instructeur getInstructeurFromId(Integer idPilote) throws DAOException {
		Instructeur instructeur = null;
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String numeroInstructeur = null;
		Double coutHoraire = null;
		try {
			connexion=this.connexion.getConnexion();
			statement=DAOUtilitaire.initialiserRequetePreparee(connexion,InstructeurDAOImpl.GET_INSTRUCTEUR_FROM_ID,true,idPilote);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				numeroInstructeur = resultSet.getString("numeroinstructeur");
				coutHoraire = resultSet.getDouble("couthoraire");
				instructeur = new Instructeur(numeroInstructeur, coutHoraire);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet,statement,connexion);
		}
		return instructeur;
	}

	@Override
	public ObservableList<Instructeur> getAllInstructeurs() throws DAOException{
		ObservableList<Instructeur> list=FXCollections.observableArrayList();
		Connection connexion=null;
		PreparedStatement ps=null;
		Integer numeroPiloteInstructeur;
		String nom;
		String prenom;
		Double coutHoraire;
		ResultSet rs=null;
		try{
			connexion=this.connexion.getConnexion();
			ps=DAOUtilitaire.initialiserRequetePreparee(connexion,GET_ALL_INSTRUCTEUR,false, (Object) null);
			rs=ps.executeQuery();
			while(rs.next()){
				numeroPiloteInstructeur=rs.getInt("idpilote");
				nom=rs.getString("nom");
				prenom=rs.getString("prenom");
				coutHoraire=rs.getDouble("couthoraire");
				System.out.println(nom);
				System.out.println(prenom);
				System.out.println(coutHoraire);
				list.add(new Instructeur(numeroPiloteInstructeur, nom, prenom, coutHoraire));
			}
		}catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(rs,ps,connexion);
		}
		return list;
	}
}
