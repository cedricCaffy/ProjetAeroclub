package model.dao;

import model.classes.aeroclub.Aeroclub;

public class AeroclubBD {
	private Aeroclub aeroclub;
	public AeroclubBD(){
		this.aeroclub=new Aeroclub("Aéroclub Périgueux Bassillac");
		this.aeroclub.setAvions(new AvionBD().getListAvions());
		this.aeroclub.setMembres(new MembresBD().getMembres());
	}
	
	public Aeroclub getAeroclub(){
		return this.aeroclub;
	}
}
