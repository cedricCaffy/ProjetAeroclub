package model.dao;

import java.time.LocalDate;
import java.util.Date;

import util.Temps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.classes.membres.Membre;
import model.classes.membres.Pilote;
import model.classes.paiement.Cheque;
import model.classes.paiement.Espece;
import model.classes.paiement.Paiement;
import model.classes.vol.Aerodrome;
import model.classes.vol.TypeVol;
import model.classes.vol.Vol;

public class MembresBD {
	private Pilote membre1;
	private Membre membre2;
	private ObservableList<Membre> membres;
	public MembresBD(){
		/*Creation des membres*/
		membre1=new Pilote(0,"CAFFY","Cédric","cedric","cedric",null,null,null,-5.5,null,null);
		membre1.ajouterVol(new Vol(LocalDate.of(2016, 04, 20),new Temps(2,5),new Aerodrome("Périgueux","LFBX"),new Aerodrome("Périgueux","LFBX"),TypeVol.Ecole,0));
		membre1.ajouterVol(new Vol(LocalDate.of(2016, 02, 25),new Temps(1,0),new Aerodrome("Périgueux","LFBX"),new Aerodrome("Bergerac","LFBE"),TypeVol.Ecole,1));
		membre1.ajouterVol(new Vol(LocalDate.of(2016, 02, 25),new Temps(2,5),new Aerodrome("Bergerac","LFBE"),new Aerodrome("Périgueux","LFBX"),TypeVol.Ecole,1));
		membre1.ajouterVol(new Vol(LocalDate.of(2016, 02, 21),new Temps(0,35),new Aerodrome("Périgueux","LFBX"),new Aerodrome("Périgueux","LFBX"),TypeVol.Navigation,3));
		membre1.ajouterPaiement(new Cheque(150.0,LocalDate.of(2016, 04, 20),"CAFFY","CA",1));
		membre1.ajouterPaiement(new Espece(180.0,LocalDate.of(2016, 04, 12)));
		membre1.ajouterPaiement(new Espece(180.0,LocalDate.of(2016, 04, 10)));
		membre2=new Membre(1,"AUZANNEAU","Maxime","maxime","maxime",null,null,null,0,null,null);
		membres=FXCollections.observableArrayList();
		/*Ajout des membres dans la fausse BD*/
		membres.add(membre1);
		membres.add(membre2);
	}
	
	public Membre getMembreById(int idMembre){
		return membres.get(idMembre); 
	}
	
	public Membre getMembreByLogin(String login){
		for(Membre membre : membres){
			if(membre.getLogin().equals(login)){
				return membre;
			}
		}
		return null;
	}
	
}
