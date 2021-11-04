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
import model.Personas;
import model.extras;

public class nuevoController implements Initializable{

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
	
	@FXML 	private GridPane panel; 
	
	@FXML	private Button ButtonAdd;
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
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		
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
			
			Stage stage = (Stage) this.ButtonAdd.getScene().getWindow();
			AgendaController.personas.add(persona);
			stage.close();
		}
		else {
			alerta.setTitle("Warning Dialog");
			alerta.setHeaderText(null);
			alerta.setContentText("No puede haber ningun campo vacio");
			alerta.showAndWait();
		}
	
	}
	
	@FXML
	private void actionExit(ActionEvent event) {
		extras.cambiarVentana(extras.escala.getX(),this.panel);
//		Stage stage = (Stage) this.ButtonExit.getScene().getWindow();
//		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		
		AgendaController.fijarTextFiel(boxFirstName, AgendaController.NfirstName,false);
		AgendaController.fijarTextFiel(boxLastName, AgendaController.NlastName,false);
		AgendaController.fijarTextFiel(boxStreet, AgendaController.Nstreet,false);
		AgendaController.fijarTextFiel(boxCity, AgendaController.Ncity,false);
		AgendaController.fijarTextFiel(boxPostalCode, AgendaController.NpostalCode,true);
		AgendaController.fijarTextFiel(boxCodPhone, AgendaController.Ncodphone,true);
		AgendaController.fijarTextFiel(boxPhone, AgendaController.Nphone,true);
		
	}

	
}
