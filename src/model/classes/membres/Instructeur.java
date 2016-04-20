package model.classes.membres;

import java.time.LocalDate;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Instructeur extends Pilote{
	private StringProperty numeroInstructeur;
	private FloatProperty coutHoraire;
	public Instructeur(int idMembre,String nom,String prenom,String login,String motDePasse,String email,String numeroTelephone,LocalDate dateNaissance,double solde,Image photo,Adresse adresse,String numeroInstructeur,float coutHoraire){
		super(idMembre,nom,prenom,login,motDePasse,email,numeroTelephone,dateNaissance,solde,photo,adresse);
		this.numeroInstructeur=new SimpleStringProperty(numeroInstructeur);
		this.coutHoraire=new SimpleFloatProperty(coutHoraire);
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
	
	public FloatProperty getCoutHoraireProperty() {
		return coutHoraire;
	}
	
	public float getCoutHoraire(){
		return coutHoraire.get();
	}
	public void setCoutHoraire(float coutHoraire) {
		this.coutHoraire.set(coutHoraire);
	}
	
}
