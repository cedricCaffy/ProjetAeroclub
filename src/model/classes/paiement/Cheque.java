package model.classes.paiement;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cheque extends Paiement{
	private StringProperty nomEmetteur;
	private StringProperty banqueDebiteur;
	private IntegerProperty numeroCheque;
	
	public Cheque(Double montant,LocalDate datePaiement,String nomEmetteur,String banqueDebiteur,Integer numeroCheque){
		super(montant,datePaiement);
		this.nomEmetteur=new SimpleStringProperty(nomEmetteur);
		this.banqueDebiteur=new SimpleStringProperty(banqueDebiteur);
		this.numeroCheque=new SimpleIntegerProperty(numeroCheque);
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
