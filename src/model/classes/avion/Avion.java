package model.classes.avion;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Avion {
	private StringProperty nom;
	private StringProperty type;
	private StringProperty immatriculation;
	private IntegerProperty autonomie;
	private IntegerProperty capaciteReservoir;
	private IntegerProperty nombrePlace;
	private IntegerProperty masseMaximale;
	private FloatProperty coutHoraire;
	private Disponibilite disponibilite;
	private FloatProperty vitesseCroisiere;
	private Image photo;
	private Centrage centrage;
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
	public FloatProperty getCoutHoraireProperty() {
		return coutHoraire;
	}
	public float getCoutHoraire(){
		return coutHoraire.get();
	}
	public void setCoutHoraire(float coutHoraire) {
		this.coutHoraire.set(coutHoraire);
	}
	public Disponibilite getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(Disponibilite disponibilite) {
		this.disponibilite = disponibilite;
	}
	public FloatProperty getVitesseCroisiereProperty() {
		return vitesseCroisiere;
	}
	public float getVitesseCroisiere(){
		return vitesseCroisiere.get();
	}
	
	public void setVitesseCroisiere(float vitesseCroisiere) {
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
