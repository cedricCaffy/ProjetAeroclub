package model.classes.avion;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;

public class Chargement {
    private StringProperty nom;
	private FloatProperty masse;
	private FloatProperty brasDeLevier;
	private FloatProperty moment;

	public String getNom() {
		return nom.get();
	}

	public void setNom(StringProperty nom) {
		this.nom = nom;
	}

	public float getMasse() {
		return masse.get();
	}

	public void setMasse(FloatProperty masse) {
		this.masse = masse;
	}

	public float getBrasDeLevier() {
		return brasDeLevier.get();
	}

	public void setBrasDeLevier(FloatProperty brasDeLevier) {
		this.brasDeLevier = brasDeLevier;
	}

	public float getMoment() {
		return moment.get();
	}

	public void setMoment(FloatProperty moment) {
		this.moment = moment;
	}
}
