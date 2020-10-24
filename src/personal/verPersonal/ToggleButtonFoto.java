package personal.verPersonal;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Circle;


public class ToggleButtonFoto extends ToggleButton {
	@FXML private  Circle circleFoto;
	private String dni=null;
   
	
	public Circle getCircleFoto() {
		return circleFoto;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public ToggleButtonFoto(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("ToggleButtonFoto.fxml"));
		getStylesheets().add(getClass().getResource("togleButtonFoto.css").toExternalForm());
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}catch(IOException e){
			System.out.println(e);
			
		}
		
	}

}
