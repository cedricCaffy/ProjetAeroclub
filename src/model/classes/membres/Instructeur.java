package model.classes.membres;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Instructeur extends Pilote{
	private StringProperty numeroInstructeur;
	private DoubleProperty coutHoraire;

	public Instructeur() {
		super();
	};

	public Instructeur(String numeroInstructeur, double coutHoraire) {
		super();
		this.numeroInstructeur = new SimpleStringProperty(numeroInstructeur);
		this.coutHoraire = new SimpleDoubleProperty(coutHoraire);
	}

	public Instructeur(Integer idPilote,String nom, String prenom,Double coutHoraire){
		super();
		super.setNom(nom);
		super.setPrenom(prenom);
		super.setIdPilote(idPilote);
		this.coutHoraire=new SimpleDoubleProperty(coutHoraire);
	}
	
	public Instructeur(int idMembre,String nom,String prenom,String login,String motDePasse,String email,String numeroTelephone,String numeroMobile,LocalDate dateNaissance,double solde,Image photo,Adresse adresse,List<String> droits,LocalDate dateVVM,String numeroInstructeur,float coutHoraire){
		super(idMembre,nom,prenom,login,motDePasse,email,numeroTelephone,numeroMobile,dateNaissance,solde,photo,adresse,droits,dateVVM);
		this.numeroInstructeur=new SimpleStringProperty(numeroInstructeur);
		this.coutHoraire=new SimpleDoubleProperty(coutHoraire);
	}

	public StringProperty getNumeroInstructeurProperty() {
		return numeroInstructeur;
	}

	public String getNumeroInstructeur(){
		return numeroInstructeur.get();
	}

	public void setNumeroInstructeur(String numeroInstructeur) {
		this.numeroInstructeur.set(numeroInstructeur);
	}

	public DoubleProperty getCoutHoraireProperty() {
		return coutHoraire;
	}

	public Double getCoutHoraire(){
		return coutHoraire.get();
	}
	public void setCoutHoraire(double coutHoraire) {
		this.coutHoraire.set(coutHoraire);
	}

}
