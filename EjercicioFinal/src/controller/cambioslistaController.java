package controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.Personas;
import model.extras;

public class cambioslistaController implements Initializable{
	

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
		
		persona.setFirstName(boxFirstName.getText());
		persona.setLastName(boxLastName.getText());
		persona.setStreet(boxStreet.getText());
		persona.setCity(boxCity.getText());
		persona.setPostalCode(Integer.parseInt(boxPostalCode.getText()));
		persona.setPhone(Integer.parseInt(boxPhone.getText()));
		persona.setBirthday(boxBirthday.getValue());
		if(boxCodPhone.getText()!="") persona.setCodphone(Integer.parseInt(boxCodPhone.getText()));

		
		if(persona.validarcampos())
		{
			
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
				extras.ventanaError("No se puede añadir porque existe su nombre y apellido o su numero telefono o ambas");
			}
		}
		else {
			extras.ventanaError("Tienes  campos mal rellenados");
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
		int NfirstName =20;
		int NlastName =20;
		int Nstreet =20;
		int Ncity =20;
		int NpostalCode =5;
		int Ncodphone =2;
		int Nphone =9;
		
		Personas personaM = new Personas();

		if(AgendaController.tipo) {
			ButtonAdd.setText("Edit");
			boxBirthday.setConverter(new LocalDateStringConverter(formatter, null));
			personaM = AgendaController.personas.get(AgendaController.posicionPersonaEnTabla);
			boxFirstName.setText(personaM.getFirstName());
			boxLastName.setText(personaM.getLastName());
			boxStreet.setText(personaM.getStreet());
			boxCity.setText(personaM.getCity());
			boxPostalCode.setText(personaM.toStringPostalCode());
			boxCodPhone.setText(personaM.toStringCodPhone());
			boxPhone.setText(personaM.toStringPhone());
			boxBirthday.setValue(personaM.getBirthday());	
		}
		extras.fijarTextFiel(boxFirstName,NfirstName,false);
		extras.fijarTextFiel(boxLastName, NlastName,false);
		extras.fijarTextFiel(boxStreet, Nstreet,false);
		extras.fijarTextFiel(boxCity, Ncity,false);
		extras.fijarTextFiel(boxPostalCode,NpostalCode,true);
		extras.fijarTextFiel(boxCodPhone, Ncodphone,true);
		extras.fijarTextFiel(boxPhone, Nphone,true);
		
	}

	
}
