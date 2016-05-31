package model.classes.vol;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.classes.membres.Pilote;

public class Vol {
	private ObjectProperty<LocalDate> dateVol;
	private ObjectProperty<LocalTime> tempsVol;
	private StringProperty aerodromeDepart;
	private StringProperty aerodromeArrivee;
	private TypeVol type;
	private IntegerProperty nombrePassager;
	private Pilote pilote;
	private DoubleProperty coutTotal;
	public Vol(LocalDate dateVol,LocalTime tempsVol,String aerodromeDepart,String aerodromeArrivee,TypeVol type,int nombrePassager, double coutTotal){
		this.dateVol=new SimpleObjectProperty<LocalDate>(dateVol);
		this.tempsVol=new SimpleObjectProperty<LocalTime>(tempsVol);
		this.aerodromeDepart=new SimpleStringProperty(aerodromeDepart);
		this.aerodromeArrivee=new SimpleStringProperty(aerodromeArrivee);
		this.type=type;
		this.nombrePassager=new SimpleIntegerProperty(nombrePassager);
		this.coutTotal=new SimpleDoubleProperty(coutTotal);
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

	public ObjectProperty<LocalTime> getTempsVolProperty() {
		return tempsVol;
	}

	public LocalTime getTempsVol(){
		return tempsVol.get();
	}

	public void setTempsVol(LocalTime tempsVol) {
		this.tempsVol.set(tempsVol);
	}

	public StringProperty getTempsVolStringProperty(){
		return new SimpleStringProperty(this.getTempsVol().getHour()+":"+this.getTempsVol().getMinute());
	}

	public String getTempsVolString(){
		return this.getTempsVol().getHour()+":"+this.getTempsVol().getMinute();
	}
	public String getAerodromeDepart() {
		return aerodromeDepart.get();
	}
	public StringProperty getAerodromeDepartProperty(){
		return this.aerodromeDepart;
	}
	public void setAerodromeDepart(String aerodromeDepart) {
		this.aerodromeDepart.set(aerodromeDepart);
	}
	public String getAerodromeArrivee() {
		return aerodromeArrivee.get();
	}

	public StringProperty getAerodromeArriveeProperty(){
		return aerodromeArrivee;
	}
	public void setAerodromeArrivee(String aerodromeArrivee) {
		this.aerodromeArrivee.set(aerodromeArrivee);
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
		this.type=type;
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

	public double getCoutTotal() {
		return coutTotal.get();
	}

	public void setCoutTotal(DoubleProperty coutTotal) {
		this.coutTotal = coutTotal;
	}


}
