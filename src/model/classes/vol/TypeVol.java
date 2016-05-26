package model.classes.vol;

public enum TypeVol {
	ECOLE ("Ecole"),
	BAPTEME ("Baptême"),
	SOLO ("Solo"),
	NAVIGATION ("Navigation");

	private String typeVol;

	TypeVol(String typeVol){
		this.typeVol=typeVol;
	}

	public String toString(){
		return this.typeVol;
	}
}
