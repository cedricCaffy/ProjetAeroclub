package model.classes.membres;

import java.time.LocalDate;

import model.classes.vol.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Pilote extends Membre{
	private ObservableList<Brevet> brevets;
	private ObservableList<Vol> vols;

	public Pilote(){}

	public Pilote(int idMembre,String nom,String prenom,String login,String motDePasse,String email,String numeroTelephone,String numeroMobile,LocalDate dateNaissance,double solde,Image photo,Adresse adresse){
		super(idMembre,nom,prenom,login,motDePasse,email,numeroTelephone,numeroMobile,dateNaissance,solde,photo,adresse);
		vols=FXCollections.observableArrayList();
		brevets=FXCollections.observableArrayList();
	}

	/**
	 * Recupere les brevets du pilote
	 * @return la liste des brevets du pilote
	 */
	public ObservableList<Brevet> getBrevets() {
		return brevets;
	}

	/**
	 * Permet d'ajouter un vol a un pilote
	 * @param vol le vol a ajouter
	 */
	public void ajouterVol(Vol vol){
		this.vols.add(vol);
	}

	/**
	 * Permet d'ajouter un brevet a un pilote
	 * @param brevet le brevet a ajouter
	 */
	public void ajouterBrevet(Brevet brevet){
		this.brevets.add(brevet);
	}

	public void setBrevets(ObservableList<Brevet> brevets) {
		this.brevets = brevets;
	}

	public ObservableList<Vol> getVols() {
		return vols;
	}

	public void setVols(ObservableList<Vol> vols) {
		this.vols = vols;
	}
}
