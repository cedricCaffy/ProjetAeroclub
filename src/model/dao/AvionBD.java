package model.dao;

import model.classes.avion.Avion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AvionBD {
	private ObservableList<Avion> avions;
	public AvionBD(){
		this.avions=FXCollections.observableArrayList();
		this.avions.addAll(
			new Avion(1,"DR-400","Voyage", "F-GLDG", 0, 0, 4, 1000, 145.0, null, 180.0, null, null),
			new Avion(2,"Cessna 152","Ecole", "F-GCNP", 0, 0, 2, 1000, 110.0, null, 180.0, null, null),
			new Avion(3,"DR-400","Voyage", "F-GIKO", 0, 0, 4, 1000, 125.0, null, 180.0, null, null),
			new Avion(4,"Cessna 172","Voyage", "F-HPGX", 0, 0, 4, 1000, 150.0, null, 200.0, null, null)
		);
	}
	
	public ObservableList<Avion> getListAvions(){
		return this.avions;
	}
}
