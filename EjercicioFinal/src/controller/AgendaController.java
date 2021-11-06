package controller;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Personas;
import model.extras;

public class AgendaController implements Initializable{
	
	public static ObservableList<Personas> personas;
	public static Personas personaModificar;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
	
	public final static int NfirstName =20;
	public final static int NlastName =20;
	public final static int Nstreet =20;
	public final static int Ncity =20;
	public final static int NpostalCode =5;
	public final static int Ncodphone =2;
	public final static int Nphone =9;
	
	@FXML 	private AnchorPane panel;
	
	@FXML	private Label DFirstName;
	@FXML	private Label DLastName;
	@FXML	private Label DStreet;	
	@FXML	private Label DCity;
	@FXML	private Label DPostalCode;
	@FXML	private Label DBirthday;
	@FXML	private Label DPhone;
	
	@FXML	private TableView<Personas> table;
	@FXML	private TableColumn<Personas, String> CFirstName;
	@FXML	private TableColumn<Personas, String> CLastName;
	public static int posicionPersonaEnTabla;
	
	@FXML
	private void actionNew(ActionEvent event) throws IOException {
		crearVentana("/vista/nuevo.fxml", "Agenda");
	}
	
	@FXML
	private void actionEdit(ActionEvent event) throws IOException {
		personaModificar = table.getSelectionModel().getSelectedItem();
		if(personaModificar != null) {
			crearVentana("/vista/modificar.fxml", "Agenda");
		}
	}
	
	private void crearVentana(String vista,String Titulo) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
		
		Stage stage = new Stage();
		Pane root = loader.load();
		
		Scene scene = new Scene(root);
		
		stage.initModality(Modality.APPLICATION_MODAL);
		
		
		stage.setScene(scene);
		stage.showAndWait();
		stage.setTitle(Titulo);
	}
	
	@FXML
	private void actionDelete(ActionEvent event) {
		ObservableList<Personas> selectedItem = table.getSelectionModel().getSelectedItems();
		table.getItems().removeAll(selectedItem);	
	}
	
	@FXML
	private void actionSizeDefault(ActionEvent event) {
		extras.cambiarVentana(1.0,this.panel);
	
	}
	
	@FXML
	private void actionSize1(ActionEvent event) {	
		extras.cambiarVentana(1.5,this.panel);
	}
	
	@FXML
	private void actionSize2(ActionEvent event) {	
		extras.cambiarVentana(2.0,this.panel);
	}
	
	@FXML
	private void actionSize3(ActionEvent event) {	
		extras.cambiarVentana(2.5,this.panel);
	}
	@FXML
	private void actionNuevo(ActionEvent event) throws Exception {
		personas.clear();
		table.getItems().clear();
	}
	
	
	@FXML
	private void actionOpen(ActionEvent event) throws Exception {
		personas.clear();
		table.getItems().clear();
		
		ArrayList<Personas> listapersona = extras.open();
		for (Personas persona : listapersona) {
			personas.add(persona);
		}
	}
	
	@FXML
	private void actionSave(ActionEvent event) throws IOException {
		extras.save(personas);
	}
	
	@FXML
	private void actionSaveAs(ActionEvent event) throws IOException {
		extras.saveAs(personas);
	}
	
	@FXML
	private void actionAbout(ActionEvent event) {
		
	}

	
	
	private final ListChangeListener<Personas> selectorTablaPersonas =
            new ListChangeListener<Personas>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Personas> c) {
                    ponerPersonaseleccionada();
                }
            };

   
    public Personas gettableSeleccionada() {
        if (table != null) {
            List<Personas> tabla = table.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Personas competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }


    private void ponerPersonaseleccionada() {
        final Personas selectedItem = gettableSeleccionada();
        posicionPersonaEnTabla = personas.indexOf(selectedItem);
        
        if (selectedItem != null) {

          
        	DFirstName.setText(selectedItem.getFirstName());
      	    DLastName.setText(selectedItem.getLastName());
      	    DStreet.setText(selectedItem.getStreet());
      	    DCity.setText(selectedItem.getCity());
      	    DPostalCode.setText(selectedItem.toStringPostalCode());
      	    DBirthday.setText(selectedItem.getBirthday().format(formatter));
      	    DPhone.setText(selectedItem.toStringcodmasPhone());

         

        }
        else {
        	DFirstName.setText("");
      	    DLastName.setText("");
      	    DStreet.setText("");
      	    DCity.setText("");
      	    DPostalCode.setText("");
      	    DBirthday.setText("");
      	    DPhone.setText("");
        }

      
    }
	
    private void inicializarTablaPersonas() {

	    CFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	    CLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        personas = FXCollections.observableArrayList();
        table.setItems(personas);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		inicializarTablaPersonas();
		
		final ObservableList<Personas> tablaPersonaSel = table.getSelectionModel().getSelectedItems();
	    tablaPersonaSel.addListener(selectorTablaPersonas);
	
		
	}
	
	public static void fijarTextFiel(final TextField campoTexto, final int tamanoMaximo, final Boolean numero) {

        campoTexto.lengthProperty().addListener(new ChangeListener<Number>() {

           @Override
           public void changed(ObservableValue<? extends Number> observable,
                   Number valorAnterior, Number valorActual) {
        	   Boolean validador = true;
        	   if (numero) {
        		   try {
        			   Integer.parseInt(campoTexto.getText());
					
        		   } catch (NumberFormatException e) {
        			   if (valorActual.intValue() > valorAnterior.intValue())
        				   campoTexto.setText(campoTexto.getText().substring(0, valorAnterior.intValue()));
        			   validador = false;
        		   }
        	   }
        	   if(validador) {
	               if (valorActual.intValue() > valorAnterior.intValue()) {
	                   if (campoTexto.getText().length() >= tamanoMaximo) {
	                       campoTexto.setText(campoTexto.getText().substring(0, tamanoMaximo));
	                   }
	               }
        	   }
           }
       });
	}
}
