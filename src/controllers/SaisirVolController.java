package controllers;

import model.classes.avion.Avion;
import model.classes.membres.Membre;
import model.classes.vol.TypeVol;
import model.dao.AvionBD;
import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Pair;
import javafx.util.StringConverter;

public class SaisirVolController {
	private Membre membre;
	private AvionBD avionBD;
	private MainApp mainApp;
	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox<Pair> cb_avions;
	
	@FXML
	private DatePicker dp_dateVol;
	
	@FXML
	private ComboBox<TypeVol> cb_typeVol;
	
	public SaisirVolController(){}
	
	@FXML
	private void initialize(){
	}
	
	/**
	 * Action qui suit le click sur le bouton enregistrer
	 */
	@FXML
	private void actionBoutonEnregistrer(){
		/**
		 * Test du combobox (temporaire)
		 */
		if(cb_avions.getSelectionModel().getSelectedItem()!=null){
			System.out.println(cb_avions.getSelectionModel().getSelectedItem().getKey());
		}
	}
	
	/**
	 * Action qui suit le clic sur le bouton annuler
	 */
	@FXML
	private void actionBoutonAnnuler(){
		mainApp.afficherEcranAccueil(this.membre);
	}
	
	/**
	 * Methode qui gere les actions apres
	 * le chargement de la page
	 */
	private void actionChargement(){
		avionBD=new AvionBD();
		ajouterAvionsComboBox();
		ajouterTypeVolComboBox();
	}
	
	/**
	 * Ajoute les types vol dans la 
	 * combobox Type Vol
	 */
	private void ajouterTypeVolComboBox(){
		cb_typeVol.getItems().addAll(TypeVol.values());
	}
	
	/**
	 * Ajoute les avions a la combobox
	 */
	@SuppressWarnings("rawtypes")
	private void ajouterAvionsComboBox(){
		cb_avions.getItems().clear();
		/**
		 * Utilisation de la classe Pair qui permet
		 * de lier une clé et une valeur.
		 * On lie l'identifiant de l'avion avec son immatriculation et son nom
		 * de manière à ne pas afficher l'identifiant de l'avion
		 * (l'utilisateur n'a pas a le savoir)
		 */
		cb_avions.setCellFactory((ListView<Pair> param) -> {
            final ListCell<Pair> cell = new ListCell<Pair>(){
            	/**
            	 * Permet de mettre l'immatriculation et le nom de l'avion
            	 * dans la cellule du combobox
            	 * @param avion
            	 * @param bln
            	 */
                @Override
                protected void updateItem(Pair avion, boolean bln) {
                    super.updateItem(avion, bln);

                    if(avion != null){
                        setText(String.valueOf(avion.getValue()));
                    }else{
                        setText(null);
                    }
                }

            };
            return cell;
        });
		/**
		 * Permet de mettre le nom de l'avion et l'immatriculation
		 * lorsque la cellule a ete selectionnee
		 */
		cb_avions.setConverter(new StringConverter<Pair>() {
            @Override
            public String toString(Pair object) {
                return (String)object.getValue();
            }

            @Override
            public Pair fromString(String string) {
                return null; // No conversion fromString needed.
            }
        });
		
		/**
		 * Ajout des valeurs dans le combobox
		 */
		for(Avion avion : avionBD.getListAvions()){
			cb_avions.getItems().add(new Pair<Integer,String>(avion.getId(),avion.getImmatriculation()+" - "+avion.getNom()));
		}
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void setMembre(Membre membre){
		this.membre=membre;
		actionChargement();
	}
}
