package model.classes.membres;

import java.time.LocalDate;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Membre {
	private IntegerProperty idMembre;
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty email;
	private StringProperty numeroTelephone;
	private ObjectProperty<LocalDate> dateNaissance;
	private FloatProperty solde;
	private Image photo;
	private Adresse adresse;
	/*private ObservableList<Vol> vols;*/
	public Membre(){
		
	}
	/*Getters and setters*/
	public IntegerProperty getIdMembreProperty() {
		return idMembre;
	}
	
	public Integer getIdMembre(){
		return idMembre.get();
	}
	
	public void setIdMembre(Integer idMembre) {
		this.idMembre.set(idMembre);
	}
	
	public StringProperty getNomProperty() {
		return nom;
	}
	
	public String getNom(){
		return nom.get();
	}
	
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	
	public StringProperty getPrenomProperty() {
		return prenom;
	}
	
	public String getPrenom(){
		return prenom.get();
	}
	
	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}
	
	public StringProperty getEmailProperty() {
		return email;
	}
	
	public String getEmail(){
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public StringProperty getNumeroTelephoneProperty() {
		return numeroTelephone;
	}
	
	public String getNumeroTelephon(){
		return numeroTelephone.get();
	}
	
	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone.set(numeroTelephone);
	}
	
	public ObjectProperty<LocalDate> getDateNaissanceProperty() {
		return dateNaissance;
	}
	
	public LocalDate getDateNaissance(){
		return dateNaissance.get();
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance.set(dateNaissance);
	}
	public FloatProperty getSoldeProperty() {
		return solde;
	}
	
	public float getSolde(){
		return solde.get();
	}
	
	public void setSolde(float solde) {
		this.solde.set(solde);
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
}
