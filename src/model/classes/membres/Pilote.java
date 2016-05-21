package model.classes.membres;

import java.time.LocalDate;
import java.util.List;

import model.classes.vol.Vol;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Pilote extends Membre{
	private IntegerProperty idPilote;
	private ObservableList<Brevet> brevets;
	private ObservableList<Vol> vols;
	private LocalDate dateVVM;

	public Pilote() {
		super();
	}

	public Pilote(LocalDate dateVVM) {
		super();
		this.dateVVM = dateVVM;
	}

	public Pilote(Integer idPilote, LocalDate dateVVM) {
		super();
		this.idPilote = new SimpleIntegerProperty(idPilote);
		this.dateVVM = dateVVM;
	}

	public Pilote(int idMembre,String nom,String prenom,String login,String motDePasse,String email,String numeroTelephone,String numeroMobile,LocalDate dateNaissance,double solde,Image photo,Adresse adresse, List<String> droits, LocalDate dateVVM){
		super(idMembre,nom,prenom,login,motDePasse,email,numeroTelephone,numeroMobile,dateNaissance,solde,photo,adresse,droits);
		vols=FXCollections.observableArrayList();
		brevets=FXCollections.observableArrayList();
		this.dateVVM = dateVVM;
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

	public LocalDate getDateVVM() {
		return dateVVM;
	}

	public void setDateVVM(LocalDate dateVVM) {
		this.dateVVM = dateVVM;
	}

	public Integer getIdPilote() {
		return idPilote.get();
	}

	public void setIdPilote(Integer idPilote) {
		this.idPilote.set(idPilote);
	}
}
