package util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Temps {
	private IntegerProperty heure;
	private IntegerProperty min;

	public Temps(){
		this.heure=new SimpleIntegerProperty(0);
		this.min=new SimpleIntegerProperty(0);
	}
	public Temps(int heure, int min){
		this.heure=new SimpleIntegerProperty(heure);
		this.min=new SimpleIntegerProperty(min);
	}


	/*getters et setters*/
	
	public IntegerProperty getHeureProperty(){
		return heure;
	}
	public int getHeure() {
		return heure.get();
	}

	public void setHeure(int heure) {
		this.heure.set(heure);
	}

	public int getMin() {
		return min.get();
	}
	
	public IntegerProperty getMinProperty(){
		return min;
	}

	public void setMin(int min) {
		this.min.set(min);
	}
	
	public Temps getTemps(){
		return this;
	}
	
	public StringProperty toStringProperty(){
		StringProperty ret=new SimpleStringProperty();
		String zeroMinute="";
		if(this.getMin()<10){
			zeroMinute="0";
		}
		ret.set(this.getHeure()+"h"+zeroMinute+this.getMin());
		return ret;
	}
	
	public void stringToTemps(String temps) throws IllegalArgumentException{
		if(!isValideTemps(temps)){
			throw new IllegalArgumentException("Le temps de vol saisi doit être de la forme \"1h00\"");
		}else{
			this.heure.set(Character.getNumericValue(temps.charAt(0)));
			this.min.set(Integer.parseInt(""+Character.getNumericValue(temps.charAt(2))+Character.getNumericValue(temps.charAt(3))));
		}
	}
	
	public boolean isValideTemps(String temps){
		return temps.matches("[0-9]h[0-5][0-9]");
	}
}
