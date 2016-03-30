package model.classes.vol;

import javafx.beans.property.StringProperty;

public class Aerodrome {
	private StringProperty nom;
	private StringProperty identifiant;
	public Aerodrome(){
		
	}
	
	public StringProperty getNomProperty() {
		return nom;
	}
	
	public String getNom(){
		return nom.get();
	}
	
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public StringProperty getIdentifiantProperty() {
		return identifiant;
	}
	
	public String getIdentifiant(){
		return identifiant.get();
	}
	
	public void setIdentifiant(String identifiant) {
		this.identifiant.set(identifiant);
	}
}
