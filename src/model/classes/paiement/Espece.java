package model.classes.paiement;

import java.time.LocalDate;

public class Espece extends Paiement{
	public Espece(Double montant,LocalDate datePaiement){
		super(montant,datePaiement);
	}
}
