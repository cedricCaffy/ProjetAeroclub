package model.classes.aeroclub;

import model.classes.avion.Avion;
import model.classes.membres.Membre;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Aeroclub {
	private StringProperty nom;
	private ObservableList<Membre> membres;
	private ObservableList<Avion> avions;
	public Aeroclub(String nom){
		this.nom=new SimpleStringProperty(nom);
		this.membres=FXCollections.observableArrayList();
		this.avions=FXCollections.observableArrayList();
	}
	public ObservableList<Avion> getAvions(){
		return this.avions;
	}
	public void setAvions(ObservableList<Avion> avions){
		this.avions=avions;
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
	public ObservableList<Membre> getMembres() {
		return membres;
	}
	public void setMembres(ObservableList<Membre> membres) {
		this.membres = membres;
	}
	
	public void ajouterAvion(Avion avion){
		this.avions.add(avion);
	}
	
	public void ajouterMembre(Membre membre){
		this.membres.add(membre);
	}
}
