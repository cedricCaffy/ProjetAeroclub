package model.classes.aeroclub;

import model.classes.avion.Avion;
import model.classes.membres.Membre;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Aeroclub {
	private StringProperty nom;
	private ObservableList<Membre> membres;
	private ObservableList<Avion> avions;
	public StringProperty getNomProperty() {
		return nom;
	}
	public String getNom(){
		return nom.get();
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public ObservableList<Membre> getMembres() {
		return membres;
	}
	public void setMembres(ObservableList<Membre> membres) {
		this.membres = membres;
	}
}
