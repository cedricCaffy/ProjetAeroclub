package model.classes.membres;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;

public class Instructeur extends Pilote{
	private StringProperty numeroInstructeur;
	private FloatProperty coutHoraire;
	public Instructeur(){
		super();
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
