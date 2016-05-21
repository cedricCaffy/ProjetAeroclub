package model.classes.membres;

import java.time.LocalDate;
import java.util.List;

import model.classes.paiement.Paiement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Membre {
	private IntegerProperty idMembre;
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty login;
	private StringProperty motDePasse;
	private StringProperty email;
	private StringProperty numeroTelephone;
	private StringProperty numeroMobile;
	private ObjectProperty<LocalDate> dateNaissance;
	private DoubleProperty solde;
	private Image photo;
	private Adresse adresse;
	private ObservableList<Paiement> paiements;
	private List<String> droits;

	public Membre() {}

	public Membre(int idMembre,String nom, String prenom,String motDePasse, List<String> droits, double solde){
		this.idMembre=new SimpleIntegerProperty(idMembre);
		this.nom=new SimpleStringProperty(nom);
		this.prenom=new SimpleStringProperty(prenom);
		this.motDePasse=new SimpleStringProperty(motDePasse);
		this.droits=droits;
		this.solde=new SimpleDoubleProperty(solde);
	}

	public Membre(int idMembre,String nom,String prenom,String login,String motDePasse,String email,String numeroTelephone,String numeroMobile,LocalDate dateNaissance,double solde,Image photo,Adresse adresse,List<String> droits){
		this.idMembre=new SimpleIntegerProperty(idMembre);
		this.nom=new SimpleStringProperty(nom);
		this.prenom=new SimpleStringProperty(prenom);
		this.login=new SimpleStringProperty(login);
		this.motDePasse=new SimpleStringProperty(motDePasse);
		this.email=new SimpleStringProperty(email);
		this.numeroTelephone=new SimpleStringProperty(numeroTelephone);
		this.numeroMobile=new SimpleStringProperty(numeroMobile);
		this.dateNaissance=new SimpleObjectProperty<LocalDate>(dateNaissance);
		this.solde=new SimpleDoubleProperty(solde);
		this.photo=photo;
		this.adresse=adresse;
		this.paiements=FXCollections.observableArrayList();
		this.droits=droits;
	}
	public Membre(Integer idMembre, String nom, String prenom, String numtel, String email) {
		this.idMembre = new SimpleIntegerProperty(idMembre);
		this.nom = new SimpleStringProperty(nom);
		this.prenom=new SimpleStringProperty(prenom);
		this.numeroTelephone=new SimpleStringProperty(numtel);
		this.email=new SimpleStringProperty(email);
	}

	/*Getters and setters*/
	public IntegerProperty getIdMembreProperty() {
		return idMembre;
	}

	public ObservableList<Paiement> getPaiements(){
		return this.paiements;
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


	public StringProperty getLoginProperty(){
		return this.login;
	}

	public String getLogin(){
		return this.login.get();
	}

	public void setLogin(String login){
		this.login.set(login);
	}

	public String getMotDePasse(){
		return this.motDePasse.get();
	}

	public void setMotDePasse(String motDePasse){
		this.motDePasse.set(motDePasse);
	}

	public StringProperty getMotDePasseProperty(){
		return this.motDePasse;
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

	public String getNumeroTelephone(){
		return numeroTelephone.get();
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone.set(numeroTelephone);
	}

	public StringProperty getNumeroMobileProperty() {
		return numeroMobile;
	}

	public String getNumeroMobile(){
		return numeroMobile.get();
	}

	public void setNumeroMobile(String numeroMobile) {
		this.numeroMobile.set(numeroMobile);
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
	public DoubleProperty getSoldeProperty() {
		return solde;
	}

	public double getSolde(){
		return solde.get();
	}

	public void setSolde(double solde) {
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

	public void setDroits(List<String> droits){
		this.droits=droits;
	}

	public List<String> getDroits(){
		return this.droits;
	}

	public void ajouterDroit(String droit){
		this.droits.add(droit);
	}
	public boolean hasRole(String droit){
		return this.droits.contains(droit);
	}

	/**Autres methodes**/

	public void ajouterPaiement(Paiement paiement){
		this.paiements.add(paiement);
	}
}
