package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class extras {
	public static Scale escala = new Scale();
	private static File ruta;
	
	
	public static void cambiarVentana(Double valor,Pane Gpanel){
		Double alto = Gpanel.getPrefHeight();
		Double ancho = Gpanel.getPrefWidth();
		escala.setX(valor);  
		escala.setY(valor);
		Gpanel.getTransforms().clear();
		Gpanel.getTransforms().add(escala);
		Double  extraAlto = altoBarra(Gpanel);
		Double  extraAncho = anchoBarra(Gpanel);
		Gpanel.getScene().getWindow().setHeight((alto*valor)+extraAlto);
		Gpanel.getScene().getWindow().setWidth((ancho*valor)+extraAncho);

	}
	
	public static Double altoBarra(Pane Gpanel){
		Double alto = (Gpanel.getScene().getWindow().getHeight()- Gpanel.getScene().getHeight());
		return alto;
	}
	
	public static Double anchoBarra(Pane Gpanel){
		Double ancho = (Gpanel.getScene().getWindow().getWidth()- Gpanel.getScene().getWidth());
		return ancho;
	}
	
	public static void saveAs(ObservableList<Personas> personas) throws IOException {
		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		try {
			ruta = fileChooser.showSaveDialog(stage);
			
			escrituraFile(personas);
			
		} catch (NullPointerException e) {
			System.out.println("No se guardado");
		}
		
	}
	
	public static void save(ObservableList<Personas> personas) throws IOException{
		if (ruta != null) {
			escrituraFile(personas);
		}
		else {
			saveAs(personas);
		}	
	}
	
	private static void escrituraFile(ObservableList<Personas> personas)throws IOException {
		 FileOutputStream fileout = new FileOutputStream(ruta,false); 
		 ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
		 for (Personas persona : personas) {
				dataOS.writeObject(persona);
			}
			dataOS.close();
	}
	
	public static ArrayList<Personas> open() throws IOException, ClassNotFoundException  {
		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		File fichero = fileChooser.showOpenDialog(stage);
		Personas aux = new Personas();
		
		ArrayList<Personas> personas = new ArrayList<Personas>();
		try {
			ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));	
			aux = (Personas) dataIS.readObject();
			while (aux != null) {
				personas.add(aux);
				aux = (Personas) dataIS.readObject();
			}
			dataIS.close();
			ruta = fichero.getAbsoluteFile();
			
		} catch (NullPointerException e) {
			System.out.println("No has abierto nada");
		}
		catch (EOFException eo) {
			System.out.println("FIN DE LECTURA.");
		} 
		catch (StreamCorruptedException x) {
		}
		return personas;
	}
	
}
