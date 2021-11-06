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

public class cambioslistaController implements Initializable{
	
	
	private Personas personaM = new Personas();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
	
	@FXML 	private GridPane panel; 
	
	@FXML   public Button ButtonAdd;
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
	private void actionAdd(ActionEvent event) {
		
		Personas persona = new Personas();
		int posicion = -1;
		if(AgendaController.tipo) {
			posicion = AgendaController.posicionPersonaEnTabla;
		}
		
		if(!(boxFirstName.getText().equals("")|| boxLastName.getText().equals("") || boxStreet.getText().equals("") 
			|| boxCity.getText().equals("") || boxPostalCode.getText().equals("") || boxPhone.getText().equals("")
			)) 
		{
			persona.setFirstName(boxFirstName.getText());
			persona.setLastName(boxLastName.getText());
			persona.setStreet(boxStreet.getText());
			persona.setCity(boxCity.getText());
			persona.setPostalCode(Integer.parseInt(boxPostalCode.getText()));
			persona.setPhone(Integer.parseInt(boxPhone.getText()));
			persona.setBirthday(boxBirthday.getValue());
			if(boxCodPhone.getText()!="")
				persona.setCodphone(Integer.parseInt(boxCodPhone.getText()));
			if(extras.compararpersona(AgendaController.personas, persona,posicion))
				if(AgendaController.tipo){
					AgendaController.personas.set(posicion,persona);
					cerrar();
				}
				else {
					AgendaController.personas.add(persona);
					cerrar();
				}
			else {
				extras.ventanaError("No se puede a�adir porque existe su nombre y apellido o su numero telefono o ambas");
			}
		}
		else {
			extras.ventanaError("No puede haber ningun campo vacio");
		}
	
	}

	
	private void cerrar() {
		Stage stage = (Stage) this.ButtonAdd.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void actionExit(ActionEvent event) {
		Stage stage = (Stage) this.ButtonExit.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		if(AgendaController.tipo) {
			ButtonAdd.setText("Edit");
			boxBirthday.setConverter(new LocalDateStringConverter(formatter, null));
			personaM = AgendaController.personaModificar;
			boxFirstName.setText(personaM.getFirstName());
			boxLastName.setText(personaM.getLastName());
			boxStreet.setText(personaM.getStreet());
			boxCity.setText(personaM.getCity());
			boxPostalCode.setText(personaM.toStringPostalCode());
			boxCodPhone.setText(personaM.toStringCodPhone());
			boxPhone.setText(personaM.toStringPhone());
			boxBirthday.setValue(personaM.getBirthday());	
		}
		AgendaController.fijarTextFiel(boxFirstName, AgendaController.NfirstName,false);
		AgendaController.fijarTextFiel(boxLastName, AgendaController.NlastName,false);
		AgendaController.fijarTextFiel(boxStreet, AgendaController.Nstreet,false);
		AgendaController.fijarTextFiel(boxCity, AgendaController.Ncity,false);
		AgendaController.fijarTextFiel(boxPostalCode, AgendaController.NpostalCode,true);
		AgendaController.fijarTextFiel(boxCodPhone, AgendaController.Ncodphone,true);
		AgendaController.fijarTextFiel(boxPhone, AgendaController.Nphone,true);
		
	}

	
}
