package model.classes.vol;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

public class Vol {
	private ObjectProperty<Date> dateVol;
	private ObjectProperty<Date> tempsVol;
	private Aerodrome aerodromeDepart;
	private Aerodrome aerodromeArrivee;
	private TypeVol type;
	private IntegerProperty nombrePassager;
	public Vol(){}
	
	public ObjectProperty<Date> getDateVolProperty() {
		return dateVol;
	}
	
	public Date getDateVol(){
		return dateVol.get();
	}
	
	public void setDateVol(Date dateVol) {
		this.dateVol.set(dateVol);
	}
	public ObjectProperty<Date> getTempsVolProperty() {
		return tempsVol;
	}
	
	public Date getTempsVol(){
		return tempsVol.get();
	}
	
	public void setTempsVol(Date tempsVol) {
		this.tempsVol.set(tempsVol);
	}
	public Aerodrome getAerodromeDepart() {
		return aerodromeDepart;
	}
	public void setAerodromeDepart(Aerodrome aerodromeDepart) {
		this.aerodromeDepart = aerodromeDepart;
	}
	public Aerodrome getAerodromeArrivee() {
		return aerodromeArrivee;
	}
	public void setAerodromeArrivee(Aerodrome aerodromeArrivee) {
		this.aerodromeArrivee = aerodromeArrivee;
	}
	public TypeVol getType() {
		return type;
	}
	public void setType(TypeVol type) {
		this.type = type;
	}
	public IntegerProperty getNombrePassagerProperty() {
		return nombrePassager;
	}
	
	public Integer getNombrePassager(){
		return nombrePassager.get();
	}
	public void setNombrePassager(Integer nombrePassager) {
		this.nombrePassager.set(nombrePassager);
	}
	
	
}
