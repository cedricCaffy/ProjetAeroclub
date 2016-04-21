package controllers;

import util.Temps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Pair;
import javafx.util.StringConverter;
import model.classes.avion.Avion;
import model.classes.membres.Membre;
import model.classes.vol.Aerodrome;
import model.classes.vol.TypeVol;
import model.classes.vol.Vol;
import model.dao.AvionBD;
import application.MainApp;

public class SaisirVolController {
	private ObservableList<Vol> listVols;
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
	
	@FXML
	private Button b_ajouterEtape;
	
	@FXML
	private TableView<Vol> tv_etapes;
	
	@FXML
	private TableColumn<Vol,String> colonne_aerodromeDepart;
	
	@FXML
	private TableColumn<Vol,String> colonne_aerodromeArrivee;
	
	@FXML
	private TableColumn<Vol,String> colonne_tempsVol;
	
	public SaisirVolController(){}
	
	@FXML
	private void initialize(){
		this.listVols=FXCollections.observableArrayList();
		tv_etapes.setEditable(true);
		/*colonne_aerodromeDepart.setCellFactory(TextFieldTableCell.<Vol>forTableColumn());*/
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
	 * Action qui suit le click sur le bouton Ajouter Etape
	 */
	@FXML
	private void actionAjouterEtape(){
		int tailleTableVols=tv_etapes.getItems().size();
		if(tailleTableVols==0){
			this.listVols.add(new Vol(null,new Temps(0,0),new Aerodrome("",""),new Aerodrome("",""),null,0));
		}else{
			listVols.add(new Vol(null,tv_etapes.getItems().get(tailleTableVols-1).getTempsVol(),tv_etapes.getItems().get(tailleTableVols-1).getAerodromeDepart(),tv_etapes.getItems().get(tailleTableVols-1).getAerodromeArrivee(),null,0));
		}
		tv_etapes.setItems(listVols);
	}
	
	/**
	 * Action qui suit le clic sur le bouton annuler
	 */
	@FXML
	private void actionBoutonAnnuler(){
		mainApp.afficherEcranAccueil(this.membre);
	}
	
	private void initialiserTableVols(){
		colonne_aerodromeDepart.setCellValueFactory((cellData)->cellData.getValue().getAerodromeDepart().getIdentifiantProperty());
		colonne_aerodromeDepart.setCellFactory(TextFieldTableCell.<Vol>forTableColumn());
		colonne_aerodromeDepart.setOnEditCommit(
	            (CellEditEvent<Vol, String> t) -> {
	                ((Vol) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setAerodromeDepart(new Aerodrome("",t.getNewValue()));
	        });
		colonne_aerodromeArrivee.setCellValueFactory((cellData)->cellData.getValue().getAerodromeArrivee().getIdentifiantProperty());
		colonne_aerodromeArrivee.setCellFactory(TextFieldTableCell.<Vol>forTableColumn());
		colonne_aerodromeArrivee.setOnEditCommit(
	            (CellEditEvent<Vol, String> t) -> {
	                ((Vol) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setAerodromeArrivee(new Aerodrome("",t.getNewValue()));
	        });
		colonne_tempsVol.setCellValueFactory((cellData)->cellData.getValue().getTempsVol().toStringProperty());
		colonne_tempsVol.setCellFactory(TextFieldTableCell.<Vol>forTableColumn());
		colonne_tempsVol.setOnEditCommit(
	            (CellEditEvent<Vol, String> t) -> {
	                ((Vol) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setTempsVol(new Temps(0,Integer.parseInt(t.getNewValue())));
	        });
	}
	/**
	 * Methode qui gere les actions apres
	 * le chargement de la page
	 */
	private void actionChargement(){
		avionBD=new AvionBD();
		ajouterAvionsComboBox();
		ajouterTypeVolComboBox();
		initialiserTableVols();
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
