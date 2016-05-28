package model.classes.paiement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaiementUtil {
	
	/**
	 * Retourne le type du paiement en fonction du paiement utilisé
	 * @param paiement
	 * @return
	 */
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
