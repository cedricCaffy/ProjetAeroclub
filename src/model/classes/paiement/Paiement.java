package model.classes.paiement;

import java.time.LocalDate;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;

public class Paiement {
	private FloatProperty montant;
	private ObjectProperty<LocalDate> datePaiement;
	
	public Paiement(){
		
	}
	public FloatProperty getMontantProperty() {
		return montant;
	}
	
	public float getMontant(){
		return montant.get();
	}
	public void setMontant(float montant) {
		this.montant.set(montant);
	}
	public ObjectProperty<LocalDate> getDatePaiementProperty() {
		return datePaiement;
	}
	
	public LocalDate getDatePaiement(){
		return datePaiement.get();
	}
	public void setDatePaiement(LocalDate datePaiement) {
		this.datePaiement.set(datePaiement);
	}
	
	
}
