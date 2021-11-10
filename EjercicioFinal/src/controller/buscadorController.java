package controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.Personas;
import model.extras;

public class buscadorController implements Initializable{
	

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
	
	@FXML 	private GridPane panel; 
	
	@FXML   public Button ButtonNext;
	@FXML   public Button ButtonBefore;
	@FXML	private Button ButtonExit;
	
	@FXML 	private TextField boxFirstName;
	@FXML 	private TextField boxLastName;
	@FXML 	private TextField boxStreet;
	@FXML 	private TextField boxCity;
	@FXML 	private TextField boxPostalCode;
	@FXML 	private TextField boxCodPhone;
	@FXML 	private TextField boxPhone;
	@FXML 	private DatePicker boxBirthday;

	private int valor = 0;
	

	
	@FXML
	private void actionNext(ActionEvent event) {
		
		for (int i = this.valor ; i < AgendaController.personas.size(); i++) {
			if (compararpersona(i,AgendaController.personas)) {
				AgendaController.table.requestFocus();
				AgendaController.table.getSelectionModel().clearSelection();
				AgendaController.table.getSelectionModel().select(i);
				AgendaController.table.getFocusModel().focus(i);
				valor = i;
				break;
				
			}
		}
		
		
		
	}
	
	

	@FXML
	private void actionBefore(ActionEvent event) {
		
		for (int i = this.valor ; i <= 0; i--) {
			if (compararpersona(i,AgendaController.personas)) {
				AgendaController.table.requestFocus();
				AgendaController.table.getSelectionModel().clearSelection();
				AgendaController.table.getSelectionModel().select(i);
				AgendaController.table.getFocusModel().focus(i);
				this.valor = i;
				break;
				
			}
		}
	
	}

	
	@FXML
	private void actionExit(ActionEvent event) {
		Stage stage = (Stage) this.ButtonExit.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		int NfirstName =20;
		int NlastName =20;
		int Nstreet =20;
		int Ncity =20;
		int NpostalCode =5;
		int Ncodphone =2;
		int Nphone =9;
		
		extras.fijarTextFiel(boxFirstName,NfirstName,false);
		extras.fijarTextFiel(boxLastName, NlastName,false);
		extras.fijarTextFiel(boxStreet, Nstreet,false);
		extras.fijarTextFiel(boxCity, Ncity,false);
		extras.fijarTextFiel(boxPostalCode,NpostalCode,true);
		extras.fijarTextFiel(boxCodPhone, Ncodphone,true);
		extras.fijarTextFiel(boxPhone, Nphone,true);
		
	}
	
	private boolean compararpersona(int posicion, List<Personas> personas) {
		Boolean validador  =false;
		if(!(boxFirstName.getText().equals("")))
			if(boxFirstName.getText().equals(personas.get(posicion).getFirstName())) validador = true;
			else validador = false;
		if(!(boxLastName.getText().equals("")))
			if(boxLastName.getText().equals(personas.get(posicion).getLastName())) validador = true;
			else validador = false;
		if(!(boxStreet.getText().equals("")))
			if(boxStreet.getText().equals(personas.get(posicion).getStreet())) validador = true;
			else validador = false;
		if(!(boxCity.getText().equals("")))
			if(boxCity.getText().equals(personas.get(posicion).getCity())) validador = true;
			else validador = false;
		if(!(boxPostalCode.getText().equals("")))
			if(boxPostalCode.getText().equals(String.valueOf(personas.get(posicion).getPostalCode()))) validador = true;
			else validador = false;
		if(!(boxCodPhone.getText().equals("")))
			if(boxCodPhone.getText().equals(String.valueOf(personas.get(posicion).getCodphone()))) validador = true;
			else validador = false;
		if(!(boxPhone.getText().equals("")))
			if(boxPhone.getText().equals(String.valueOf(personas.get(posicion).getPhone()))) validador = true;
			else validador = false;
		
	
			
		return validador;
	}

	
}
