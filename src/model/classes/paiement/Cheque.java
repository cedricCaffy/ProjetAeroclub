package model.classes.paiement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Cheque extends Paiement{
	private StringProperty nomEmetteur;
	private StringProperty banqueDebiteur;
	private IntegerProperty numeroCheque;
	
	public Cheque(){
		super();
	}

	public StringProperty getNomEmetteurProperty() {
		return nomEmetteur;
	}
	
	public String getNomEmetteur(){
		return nomEmetteur.get();
	}

	public void setNomEmetteur(String nomEmetteur) {
		this.nomEmetteur.set(nomEmetteur);
	}

	public StringProperty getBanqueDebiteurProperty() {
		return banqueDebiteur;
	}
	
	public String getBanqueDebiteur(){
		return banqueDebiteur.get();
	}
	
	public void setBanqueDebiteur(String banqueDebiteur) {
		this.banqueDebiteur.set(banqueDebiteur);
	}

	public IntegerProperty getNumeroChequeProperty() {
		return numeroCheque;
	}
	
	public Integer getNumeroCheque(){
		return numeroCheque.get();
	}

	public void setNumeroCheque(Integer numeroCheque) {
		this.numeroCheque.set(numeroCheque);
	}
	
	
}
