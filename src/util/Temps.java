package util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Temps {
	private IntegerProperty heure;
	private IntegerProperty min;
	
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
	
	public StringProperty toStringProperty(){
		StringProperty ret=new SimpleStringProperty();
		String zeroMinute="";
		if(this.getMin()<10){
			zeroMinute="0";
		}
		ret.set(this.getHeure()+"h"+zeroMinute+this.getMin()+"min");
		return ret;
	}
}
