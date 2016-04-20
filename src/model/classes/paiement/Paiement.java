package model.classes.paiement;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Paiement {
	private DoubleProperty montant;
	private ObjectProperty<LocalDate> datePaiement;
	
	public Paiement(Double montant,LocalDate datePaiement){
		this.montant=new SimpleDoubleProperty(montant);
		this.datePaiement=new SimpleObjectProperty<LocalDate>(datePaiement);
	}
	public DoubleProperty getMontantProperty() {
		return montant;
	}
	
	public double getMontant(){
		return montant.get();
	}
	
	public void setMontant(Double montant) {
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
