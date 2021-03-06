package controllers;

import java.time.LocalTime;

import exceptions.DAOConfigurationException;
import exceptions.DAOException;
import exceptions.FormulaireException;
import bd.ConnexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import model.classes.avion.Avion;
import model.classes.membres.Instructeur;
import model.classes.membres.Membre;
import model.classes.vol.TypeVol;
import model.classes.vol.Vol;
import model.dao.AvionDAO;
import model.dao.AvionDAOImpl;
import model.dao.InstructeurDAO;
import model.dao.InstructeurDAOImpl;
import model.dao.MembresDAO;
import model.dao.PiloteDAO;
import model.dao.PiloteDAOImpl;
import model.dao.VolDAO;
import model.dao.VolDAOImpl;
import util.AcceptOnExitTableCell;
import view.popup.PopupError;
import view.popup.PopupException;
import view.popup.PopupInfo;
import view.popup.PopupInfoConfirmation;
import application.MainApp;

public class SaisirVolController {
	private ObservableList<Vol> listVols;
	private Vol etapeSelec;
	private Integer nbEtapes = 0;
	private Membre membre;
	private MainApp mainApp;
	@FXML
	private ComboBox<Avion> cb_avions;

	@FXML
	private DatePicker dp_dateVol;

	@FXML
	private ComboBox<TypeVol> cb_typeVol;

	@FXML
	private ComboBox<Instructeur> cb_instructeur;

	@FXML
	private ComboBox<Integer> cb_nbPassagers;

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
	}

	/**
	 * Action qui suit le click sur le bouton enregistrer
	 */
	@FXML
	private void actionBoutonEnregistrer(){
		try {
			controlerAvion();
			controlerDateVol();
			controlerTypeVol();
			if (cb_typeVol.getValue() == TypeVol.ECOLE) {
				controlerInstructeur();
			}
			controlerNbPassagers();
			controlerEtapes();
			enregistrerVol();
			reinitialiserMembre();
			new PopupInfo().afficherPopup("Confirmation", "Confirmation d'ajout","Le vol a bien été ajouté, merci.");
			mainApp.afficherEcranAccueil(membre);
		} catch (FormulaireException e) {
			new PopupError("Erreur de saisie du formulaire","",e.getMessage());
		} catch (DAOException e) {
			new PopupException(e);
		} catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de configuration",e.getMessage());
		}
	}

	private void actionChangementTypeVol(){
		cb_typeVol.setOnAction((event)->{
			setNbPlacesComboBox();
			setComboBoxInstructeur();
		});
	}

	private void setComboBoxInstructeur(){
		TypeVol typeVol=null;
		typeVol=cb_typeVol.getSelectionModel().getSelectedItem();
		if(typeVol!=null){
			cb_instructeur.getSelectionModel().clearSelection();
			if(typeVol.equals(TypeVol.ECOLE)){
				cb_instructeur.setDisable(false);
			}else{
				initialiserComboBoxInstructeur();
			}
		}
	}

	private void initialiserComboBoxInstructeur() {
		cb_instructeur.setDisable(true);
	}

	private void actionChangementAvion(){
		cb_avions.setOnAction((event)->{
			setNbPlacesComboBox();
		});
	}

	private void setNbPlacesComboBox(){
		cb_nbPassagers.getItems().clear();
		Avion selectedAvion=cb_avions.getSelectionModel().getSelectedItem();
		TypeVol typeVol=null;
		if(selectedAvion!=null){
			Integer nbPlaces=selectedAvion.getNombrePlace()-1;
			typeVol=cb_typeVol.getSelectionModel().getSelectedItem();
			if(typeVol!=null){
				if(typeVol.equals(TypeVol.ECOLE)){
					ajouterNbPlacesComboBox(nbPlaces-1);
				}else{
					ajouterNbPlacesComboBox(nbPlaces);
				}
			}
		}
	}


	private void ajouterNbPlacesComboBox(Integer nbPlaces){
		for(Integer i=0;i<=nbPlaces;i++){
			cb_nbPassagers.getItems().add(i);
		}
	}

	/**
	 * Action qui suit le click sur le bouton Ajouter Etape
	 */
	@FXML
	private void actionAjouterEtape(){
		this.listVols.add(new Vol(null,LocalTime.of(0, 0), "", "", null, 0,0));
		tv_etapes.setItems(listVols);
		this.nbEtapes++;
	}

	@FXML
	private void actionSupprimerEtape() {
		if (etapeSelec != null) {
			this.listVols.remove(this.etapeSelec);
			tv_etapes.setItems(listVols);
			this.nbEtapes--;
		} else {
			new PopupError("Etape non sélectionnée","","Veuillez sélectionner une étape pour pouvoir la supprimer");
		}
	}

	/**
	 * Action qui suit le clic sur le bouton annuler
	 */
	@FXML
	private void actionBoutonAnnuler(){
		PopupInfoConfirmation popup=new PopupInfoConfirmation();
		popup.afficherPopup("Confirmation", "Annulation", "Attention, vous allez perdre toute votre saisie si"
				+ " vous annulez, voulez-vous vraiment continuer ?");
		if(popup.getButtonClicked().get()==ButtonType.OK){
			mainApp.afficherEcranAccueil(this.membre);
		}
	}

	//TextFieldTableCell
	//AcceptOnExitTableCell
	private void initialiserTableVols(){
		//On rentre le type de donnee qu'il y aura dans la colonne
		colonne_aerodromeDepart.setCellValueFactory((cellData)->cellData.getValue().getAerodromeDepartProperty());
		//On change le type chaque cellule dans la colonne (cellule editable)
		colonne_aerodromeDepart.setCellFactory(AcceptOnExitTableCell.<Vol>forTableColumn());
		//Apres validation de ce qui a ete rentre, on met a jour l'objet contenu dans la tableview
		colonne_aerodromeDepart.setOnEditCommit(
	            (CellEditEvent<Vol, String> t) -> {
	                ((Vol) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setAerodromeDepart(t.getNewValue());
	        });
		colonne_aerodromeArrivee.setCellValueFactory((cellData)->cellData.getValue().getAerodromeArriveeProperty());
		colonne_aerodromeArrivee.setCellFactory(AcceptOnExitTableCell.<Vol>forTableColumn());
		colonne_aerodromeArrivee.setOnEditCommit(
	            (CellEditEvent<Vol, String> t) -> {
	                ((Vol) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setAerodromeArrivee(t.getNewValue());
	        });
		colonne_tempsVol.setCellValueFactory((cellData)->cellData.getValue().getTempsVolStringProperty());
		colonne_tempsVol.setCellFactory(AcceptOnExitTableCell.<Vol>forTableColumn());
		colonne_tempsVol.setOnEditCommit(
	            (CellEditEvent<Vol, String> t) -> {
	            	LocalTime temps=null;
	            	try{
	            		temps=LocalTime.of(Integer.parseInt(t.getNewValue().split(":")[0]),Integer.parseInt(t.getNewValue().split(":")[1]));
	            	}catch(IllegalArgumentException e){
	            		temps=LocalTime.of(0,0);
	            		new PopupError("Erreur de saisie", null, e.getMessage());
	            	}finally{
	            		((Vol) t.getTableView().getItems().get(
		                        t.getTablePosition().getRow())
		                        ).setTempsVol(temps);
	            	}
	            });
		tv_etapes.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> setEtapeSelec(newValue));
	}

	/**
	 * Methode qui gere les actions apres
	 * le chargement de la page
	 */
	private void actionChargement(){
		ajouterAvionsComboBox();
		ajouterInstructeurComboBox();
		ajouterTypeVolComboBox();
		initialiserComboBoxInstructeur();
		initialiserTableVols();
		actionChangementTypeVol();
		actionChangementAvion();
		/*aeroclubBD=new AeroclubDAOImpl();
		ajouterAvionsComboBox();
		initialiserTableVols();*/
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
	private void ajouterAvionsComboBox(){
		cb_avions.getItems().clear();
		/**
		 * Utilisation de la classe Pair qui permet
		 * de lier une clé et une valeur.
		 * On lie l'identifiant de l'avion avec son immatriculation et son nom
		 * de manière à ne pas afficher l'identifiant de l'avion
		 * (l'utilisateur n'a pas a le savoir)
		 */
		cb_avions.setCellFactory((ListView<Avion> param) -> {
            final ListCell<Avion> cell = new ListCell<Avion>(){
            	/**
            	 * Permet de mettre l'immatriculation et le nom de l'avion
            	 * dans la cellule du combobox
            	 * @param avion
            	 * @param bln
            	 */
                @Override
                protected void updateItem(Avion avion, boolean bln) {
                    super.updateItem(avion, bln);

                    if(avion != null){
                        setText(String.valueOf(avion.getImmatriculation()+" - "+avion.getNom()));
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
		cb_avions.setConverter(new StringConverter<Avion>() {
            @Override
            public String toString(Avion object) {
                return (String)object.getImmatriculation()+" - "+object.getNom();
            }

            @Override
            public Avion fromString(String string) {
                return null; // No conversion fromString needed.
            }
        });

		/**
		 * Ajout des valeurs dans le combobox
		 */
		try{
			ajouterAvions();
		}catch (DAOException e){
			new PopupException(e);
		} catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de configuration",e.getMessage());
		}
	}

	private void ajouterAvions() throws DAOException,DAOConfigurationException{
		ConnexionBD connexion=ConnexionBD.getInstance();
		AvionDAO avionDAO=new AvionDAOImpl(connexion);
		for(Avion avion : avionDAO.getAllAvion()){
			cb_avions.getItems().add(avion);
		}
	}

	private void ajouterInstructeurComboBox(){
		cb_instructeur.getItems().clear();
		/**
		 * Utilisation de la classe Pair qui permet
		 * de lier une clé et une valeur.
		 * On lie l'identifiant de l'instructeur avec son nom et son prénom
		 * de manière à ne pas afficher l'identifiant de l'avion
		 * (l'utilisateur n'a pas a le savoir)
		 */
		cb_instructeur.setCellFactory((ListView<Instructeur> param) -> {
            final ListCell<Instructeur> cell = new ListCell<Instructeur>(){
            	/**
            	 * Permet de mettre l'immatriculation et le nom de l'avion
            	 * dans la cellule du combobox
            	 * @param avion
            	 * @param bln
            	 */
                @Override
                protected void updateItem(Instructeur instructeur, boolean bln) {
                    super.updateItem(instructeur, bln);

                    if(instructeur != null){
                        setText(String.valueOf(instructeur.getNom()+" "+instructeur.getPrenom()));
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
		cb_instructeur.setConverter(new StringConverter<Instructeur>() {
            @Override
            public String toString(Instructeur instructeur) {
                return (String)instructeur.getNom()+" "+instructeur.getPrenom();
            }

            @Override
            public Instructeur fromString(String string) {
                return null; // No conversion fromString needed.
            }
        });
		try{
			ajouterInstructeurs();
		}catch (DAOException e){
			new PopupException(e);
		} catch(DAOConfigurationException e){
			new PopupError("Erreur","Erreur de configuration",e.getMessage());
		}
	}

	private void ajouterInstructeurs() throws DAOException,DAOConfigurationException{
		ConnexionBD connexion=ConnexionBD.getInstance();
		InstructeurDAO instructeurDAO=new InstructeurDAOImpl(connexion);
		for(Instructeur instructeur : instructeurDAO.getAllInstructeurs()){
			cb_instructeur.getItems().add(instructeur);
		}
	}

	private void controlerAvion() throws FormulaireException {
		if(cb_avions.getSelectionModel().getSelectedItem()==null){
			throw new FormulaireException("Veuillez saisir un avion");
		}
	}

	private void controlerDateVol() throws FormulaireException{
		if(dp_dateVol.getValue() == null){
			throw new FormulaireException("Veuillez saisir la date du vol");
		}
	}

	private void controlerTypeVol() throws FormulaireException {
		if(cb_typeVol.getSelectionModel().getSelectedItem()==null){
			throw new FormulaireException("Veuillez saisir un type de vol");
		}
	}

	private void controlerInstructeur() throws FormulaireException {
		if(cb_instructeur.getSelectionModel().getSelectedItem()==null){
			throw new FormulaireException("Veuillez saisir un instructeur");
		}
	}

	private void controlerNbPassagers() throws FormulaireException {
		if(cb_nbPassagers.getSelectionModel().getSelectedItem()==null){
			throw new FormulaireException("Veuillez saisir un nombre de passagers");
		}
	}

	private void controlerEtapes() throws FormulaireException{
		int numeroEtape;
		if (nbEtapes == 0) {
			throw new FormulaireException("Veuillez saisir des étapes pour le vol");
		}
		for (int i = 0; i < nbEtapes ; i++) {
			numeroEtape = i+1;
			if (colonne_aerodromeDepart.getCellData(i).length() == 0) {
				throw new FormulaireException("Aérodrome de départ non spécifié à l'étape n°" + numeroEtape);
			}
			if (colonne_aerodromeDepart.getCellData(i).length() > 6) {
				throw new FormulaireException("L'aérodrome de départ de l'étape n°" + numeroEtape + " ne doit pas dépasser 6 caractères");
			}
			if (colonne_aerodromeArrivee.getCellData(i).length() == 0) {
				throw new FormulaireException("Aérodrome d'arrivée non spécifié à l'étape n°" + numeroEtape);
			}
			if (colonne_aerodromeArrivee.getCellData(i).length() > 6) {
				throw new FormulaireException("L'aérodrome d'arrivée de l'étape n°" + numeroEtape + " ne doit pas dépasser 6 caractères");
			}
			if (colonne_tempsVol.getCellData(i).equals("0:0")) {
				throw new FormulaireException("Le temps de vol de l'étape n°" + numeroEtape + " ne doit pas être nul");
			}
		}
	}

	private void enregistrerVol() throws DAOException, DAOConfigurationException {
		Integer idPilote;
		LocalTime tempsVol;
		Vol volAAjouter;
		VolDAO volDao;
		PiloteDAO piloteDao;
		Double coutTotal;
		ConnexionBD connexion = ConnexionBD.getInstance();
		volDao = new VolDAOImpl(connexion);
		piloteDao = new PiloteDAOImpl(connexion);

		idPilote = piloteDao.getPiloteFromId(this.membre.getIdMembre()).getIdPilote();

		for (int i = 0 ; i < nbEtapes ; i++) {
			tempsVol = LocalTime.of(Integer.parseInt(colonne_tempsVol.getCellData(i).split(":")[0]),
					Integer.parseInt(colonne_tempsVol.getCellData(i).split(":")[1]));
			coutTotal = calculerCoutVol(tempsVol);
			volAAjouter = new Vol(dp_dateVol.getValue(), tempsVol, colonne_aerodromeDepart.getCellData(i),
					colonne_aerodromeArrivee.getCellData(i), cb_typeVol.getValue(), cb_nbPassagers.getValue(), coutTotal);
			volDao.insererVol(volAAjouter, idPilote, cb_avions.getValue().getId(), cb_instructeur.getValue() != null ? cb_instructeur.getValue().getNumeroInstructeur() : null);
		}
	}

	private Double calculerCoutVol(LocalTime tempsVol) {
		Double cout = null;
		cout = cb_avions.getValue().getCoutHoraire()*(tempsVol.getHour()+(tempsVol.getMinute())/60);
		if (cb_instructeur.getValue() != null) {
			cout += cb_instructeur.getValue().getCoutHoraire()*(tempsVol.getHour()+(tempsVol.getMinute())/60);
		}
		return cout;
	}

	private void reinitialiserMembre() throws DAOException,DAOConfigurationException {
		ConnexionBD connexion=ConnexionBD.getInstance();
		MembresDAO membresDAO=connexion.getMembreDAO();
		this.membre=membresDAO.getMembreByLogin(this.membre.getLogin());
	}

	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

	public void setMembre(Membre membre){
		this.membre=membre;
		actionChargement();
	}

	private void setEtapeSelec(Vol newValue) {
		this.etapeSelec = newValue;
	}

}
