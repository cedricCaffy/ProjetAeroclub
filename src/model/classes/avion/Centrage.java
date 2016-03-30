package model.classes.avion;

import javafx.collections.ObservableList;

public class Centrage {
	private ObservableList<Chargement> chargements;
	public Centrage(){
		
	}
	public ObservableList<Chargement> getChargements() {
		return chargements;
	}
	public void setChargements(ObservableList<Chargement> chargements) {
		this.chargements = chargements;
	}
}
