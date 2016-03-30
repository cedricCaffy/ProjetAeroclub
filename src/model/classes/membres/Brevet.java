package model.classes.membres;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class Brevet {
	private StringProperty nomBrevet;
	private ObjectProperty<LocalDate> dateValidite;
	private ObjectProperty<LocalDate> dateObtention;
	public StringProperty getNomBrevet() {
		return nomBrevet;
	}
	public void setNomBrevet(String nomBrevet) {
		this.nomBrevet.set(nomBrevet);
	}
	public ObjectProperty<LocalDate> getDateValiditeProperty() {
		return dateValidite;
	}
	public LocalDate getDateValidite(){
		return dateValidite.get();
	}
	public void setDateValidite(LocalDate dateValidite) {
		this.dateValidite.set(dateValidite);
	}
	public ObjectProperty<LocalDate> getDateObtentionProperty() {
		return dateObtention;
	}
	public LocalDate getDateObtention(){
		return dateObtention.get();
	}
	public void setDateObtention(LocalDate dateObtention) {
		this.dateObtention.set(dateObtention);
	}
	
	
}
