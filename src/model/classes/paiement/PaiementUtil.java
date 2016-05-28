package model.classes.paiement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaiementUtil {
	public static StringProperty getTypePaiement(Paiement paiement){
		StringProperty typePaiement=new SimpleStringProperty();
		if(paiement instanceof Cheque){
			typePaiement.set(TypePaiement.CHEQUE.toString());
		}else{
			typePaiement.set(TypePaiement.ESPECES.toString());
		}
		return typePaiement;
	}
}
