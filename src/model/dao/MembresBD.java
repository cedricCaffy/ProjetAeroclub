package model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.classes.membres.Membre;

public class MembresBD {
	private Membre membre1;
	private Membre membre2;
	private ObservableList<Membre> membres;
	public MembresBD(){
		/*Creation des membres*/
		membre1=new Membre(0,"CAFFY","Cédric","cedric","cedric",null,null,null,0,null,null);
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
