package controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
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
	

	
	@FXML
	private void actionNext(ActionEvent event) {
	
	}
	
	@FXML
	private void actionBefore(ActionEvent event) {
	
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

	
}
