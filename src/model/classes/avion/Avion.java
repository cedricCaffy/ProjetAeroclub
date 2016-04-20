package model.classes.avion;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Avion {
	private IntegerProperty id;
	private StringProperty nom;
	private StringProperty type;
	private StringProperty immatriculation;
	private IntegerProperty autonomie;
	private IntegerProperty capaciteReservoir;
	private IntegerProperty nombrePlace;
	private IntegerProperty masseMaximale;
	private DoubleProperty coutHoraire;
	private Disponibilite disponibilite;
	private DoubleProperty vitesseCroisiere;
	private Image photo;
	private Centrage centrage;
	
	public Avion(Integer id,String nom, String type, String immatriculation,Integer autonomie,Integer capaciteReservoir,Integer nombrePlace, Integer masseMaximale,Double coutHoraire,Disponibilite disponibilite,Double vitesseCroisiere,Image photo,Centrage centrage){
		this.id=new SimpleIntegerProperty(id);
		this.nom=new SimpleStringProperty(nom);
		this.type=new SimpleStringProperty(type);
		this.immatriculation=new SimpleStringProperty(immatriculation);
		this.autonomie=new SimpleIntegerProperty(autonomie);
		this.capaciteReservoir=new SimpleIntegerProperty(capaciteReservoir);
		this.nombrePlace=new SimpleIntegerProperty(nombrePlace);
		this.masseMaximale=new SimpleIntegerProperty(masseMaximale);
		this.coutHoraire=new SimpleDoubleProperty(coutHoraire);
		this.disponibilite=disponibilite;
		this.vitesseCroisiere=new SimpleDoubleProperty(vitesseCroisiere);
		this.photo=photo;
		this.centrage=centrage;
	}
	
	public IntegerProperty getIdProperty(){
		return this.id;
	}
	
	public Integer getId(){
		return this.id.get();
	}
	
	public void setId(Integer id){
		this.id.set(id);
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
	public StringProperty getTypeProperty() {
		return type;
	}
	public String getType() {
		return type.get();
	}
	
	public void setType(String type) {
		this.type.set(type);
	}
	public StringProperty getImmatriculationProperty() {
		return immatriculation;
	}
	public String getImmatriculation(){
		return immatriculation.get();
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation.set(immatriculation);
	}
	public IntegerProperty getAutonomieProperty() {
		return autonomie;
	}
	
	public Integer getAutonomie(){
		return autonomie.get();
	}
	public void setAutonomie(Integer autonomie) {
		this.autonomie.set(autonomie);
	}
	
	public IntegerProperty getCapaciteReservoirProperty() {
		return capaciteReservoir;
	}
	
	public Integer getCapaciteReservoir(){
		return capaciteReservoir.get();
	}
	public void setCapaciteReservoir(Integer capaciteReservoir) {
		this.capaciteReservoir.set(capaciteReservoir);
	}
	public IntegerProperty getNombrePlaceProperty() {
		return nombrePlace;
	}
	public Integer getNombrePlace(){
		return nombrePlace.get();
	}
	public void setNombrePlace(Integer nombrePlace) {
		this.nombrePlace.set(nombrePlace);
	}
	public IntegerProperty getMasseMaximaleProperty() {
		return masseMaximale;
	}
	public Integer getMasseMaximale(){
		return masseMaximale.get();
	}
	public void setMasseMaximale(Integer masseMaximale) {
		this.masseMaximale.set(masseMaximale);
	}
	public DoubleProperty getCoutHoraireProperty() {
		return coutHoraire;
	}
	public Double getCoutHoraire(){
		return coutHoraire.get();
	}
	public void setCoutHoraire(Double coutHoraire) {
		this.coutHoraire.set(coutHoraire);
	}
	public Disponibilite getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(Disponibilite disponibilite) {
		this.disponibilite = disponibilite;
	}
	public DoubleProperty getVitesseCroisiereProperty() {
		return vitesseCroisiere;
	}
	public Double getVitesseCroisiere(){
		return vitesseCroisiere.get();
	}
	
	public void setVitesseCroisiere(Double vitesseCroisiere) {
		this.vitesseCroisiere.set(vitesseCroisiere);
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public Centrage getCentrage() {
		return centrage;
	}
	public void setCentrage(Centrage centrage) {
		this.centrage = centrage;
	}
}
