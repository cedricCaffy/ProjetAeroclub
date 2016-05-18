package model.classes.vol;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import util.Temps;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.classes.membres.Pilote;

public class Vol {
	private ObjectProperty<LocalDate> dateVol;
	private ObjectProperty<Temps> tempsVol;
	private Aerodrome aerodromeDepart;
	private Aerodrome aerodromeArrivee;
	private TypeVol type;
	private IntegerProperty nombrePassager;
	private Pilote pilote;
	public Vol(LocalDate dateVol,Temps tempsVol,Aerodrome aerodromeDepart,Aerodrome aerodromeArrivee,TypeVol type,int nombrePassager){
		this.dateVol=new SimpleObjectProperty<LocalDate>(dateVol);
		this.tempsVol=new SimpleObjectProperty<Temps>(tempsVol);
		this.aerodromeDepart=aerodromeDepart;
		this.aerodromeArrivee=aerodromeArrivee;
		this.type=type;
		this.nombrePassager=new SimpleIntegerProperty(nombrePassager);
	}

	public ObjectProperty<LocalDate> getDateVolProperty() {
		return dateVol;
	}

	public LocalDate getDateVol(){
		return dateVol.get();
	}

	public void setDateVol(LocalDate dateVol) {
		this.dateVol.set(dateVol);
	}

	public ObjectProperty<Temps> getTempsVolProperty() {
		return tempsVol;
	}

	public Temps getTempsVol(){
		return tempsVol.get();
	}

	public void setTempsVol(Temps tempsVol) {
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
	public StringProperty getTypeProperty(){
		StringProperty type=new SimpleStringProperty();
		type.set(this.type.toString());
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

	public Pilote getPilote() {
		return pilote;
	}

	public void setPilote(Pilote pilote) {
		this.pilote = pilote;
	}


}
