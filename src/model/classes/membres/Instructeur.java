package model.classes.membres;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Instructeur extends Pilote{
	private StringProperty numeroInstructeur;
	private DoubleProperty coutHoraire;
	public Instructeur(int idMembre,String nom,String prenom,String login,String motDePasse,String email,String numeroTelephone,String numeroMobile,LocalDate dateNaissance,double solde,Image photo,Adresse adresse,String numeroInstructeur,float coutHoraire){
		super(idMembre,nom,prenom,login,motDePasse,email,numeroTelephone,numeroMobile,dateNaissance,solde,photo,adresse);
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

	public double getCoutHoraire(){
		return coutHoraire.get();
	}
	public void setCoutHoraire(double coutHoraire) {
		this.coutHoraire.set(coutHoraire);
	}

}
