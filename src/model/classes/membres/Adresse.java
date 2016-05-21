package model.classes.membres;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adresse {
	private IntegerProperty idAdresse;
	private StringProperty rue;
	private StringProperty ville;
	private StringProperty codePostal;
	private IntegerProperty numero;

	public Adresse(){}

	public Adresse(Integer idAdresse) {
		this.idAdresse = new SimpleIntegerProperty(idAdresse);
	}

	public Adresse(String rue, String ville, String codePostal, Integer numero) {
		this.rue = new SimpleStringProperty(rue);
		this.ville = new SimpleStringProperty(ville);
		this.codePostal = new SimpleStringProperty(codePostal);
		this.numero = new SimpleIntegerProperty(numero);
	}

	public Adresse(Integer idAdresse, String rue, String ville, String codePostal, Integer numero) {
		this.idAdresse = new SimpleIntegerProperty(idAdresse);
		this.rue = new SimpleStringProperty(rue);
		this.ville = new SimpleStringProperty(ville);
		this.codePostal = new SimpleStringProperty(codePostal);
		this.numero = new SimpleIntegerProperty(numero);
	}

	public IntegerProperty getIdAdresseProperty() {
		return idAdresse;
	}

	public Integer getIdAdresse() {
		return idAdresse.get();
	}

	public void setIdAdresse(Integer idAdresse) {
		this.idAdresse.set(idAdresse);
	}

	public StringProperty getRueProperty() {
		return rue;
	}
	public String getRue(){
		return rue.get();
	}
	public void setRue(String rue) {
		this.rue.set(rue);
	}

	public StringProperty getVilleProperty() {
		return ville;
	}
	public String getVille(){
		return ville.get();
	}
	public void setVille(String ville) {
		this.ville.set(ville);
	}
	public StringProperty getCodePostalProperty() {
		return codePostal;
	}
	public String getCodePostal(){
		return codePostal.get();
	}
	public void setCodePostal(String codePostal) {
		this.codePostal.set(codePostal);
	}
	public IntegerProperty getNumeroProperty() {
		return numero;
	}
	public Integer getNumero(){
		return numero.get();
	}
	public void setNumero(Integer numero) {
		this.numero.set(numero);
	}
}
