package model.classes.paiement;

public enum TypePaiement {
	ESPECES("Espèces"),
	CHEQUE("Chèque");
	
	private String typePaiement;
	
	TypePaiement(String typePaiement){
		this.typePaiement=typePaiement;
	}
	
	public String toString(){
		return this.typePaiement;
	}
}
