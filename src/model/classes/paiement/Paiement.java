package model.classes.paiement;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Paiement {
	private IntegerProperty idPaiement;
	private DoubleProperty montant;
	private ObjectProperty<LocalDate> datePaiement;
	
	public Paiement(Double montant,LocalDate datePaiement){
		this.idPaiement=new SimpleIntegerProperty();
		this.montant=new SimpleDoubleProperty(montant);
		this.datePaiement=new SimpleObjectProperty<LocalDate>(datePaiement);
	}
	
	public IntegerProperty getIdPaiementProperty(){
		return this.idPaiement;
	}
	
	public Integer getIdPaiement(){
		return this.idPaiement.get();
	}
	
	public void setIdPaiement(Integer idPaiement){
		this.idPaiement.set(idPaiement);
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
