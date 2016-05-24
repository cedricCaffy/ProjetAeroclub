package model.classes.vol;

public enum TypeVol {
	Ecole ("Ecole"),
	Baptême ("Baptême"),
	Solo ("Solo"),
	Navigation("Navigation");
	
	private String typeVol;
	
	TypeVol(String typeVol){
		this.typeVol=typeVol;
	}
	
	public String toString(){
		return this.typeVol;
	}
}
