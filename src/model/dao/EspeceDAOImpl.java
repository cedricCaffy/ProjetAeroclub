package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bd.ConnexionBD;
import model.classes.paiement.Espece;
import exceptions.DAOException;

public class EspeceDAOImpl implements EspeceDAO{
	ConnexionBD connexion;
	private static final String INSERT_ESPECE="INSERT INTO ESPECE (idpaiement) VALUES (?)";
	public EspeceDAOImpl(ConnexionBD connexion){
		this.connexion=connexion;
	}
	
	@Override
	public void ajouterEspece(Integer idPaiement) throws DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		int statut;
		try{
			connexion=this.connexion.getConnexion();
			preparedStatement=DAOUtilitaire.initialiserRequetePreparee(connexion, INSERT_ESPECE,false,idPaiement);
			statut=preparedStatement.executeUpdate();
			if(statut==0){
				throw new DAOException("Erreur lors de l'ajout de l'espèce, aucune ligne n'a été modifiée dans la base de données");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtilitaire.fermeturesSilencieuses(preparedStatement,connexion);
		}
	}
	
}
