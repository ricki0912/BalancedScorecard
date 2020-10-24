package acciones;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class OpcionesControler extends HBox{
	
    @FXML
    private JFXButton jFXButtonEditar;

    @FXML
    private JFXButton jFXButtonEliminar;

	
	public JFXButton getjFXButtonEditar() {
		return jFXButtonEditar;
	}


	public void setjFXButtonEditar(JFXButton jFXButtonEditar) {
		this.jFXButtonEditar = jFXButtonEditar;
	}


	public JFXButton getjFXButtonEliminar() {
		return jFXButtonEliminar;
	}


	public void setjFXButtonEliminar(JFXButton jFXButtonEliminar) {
		this.jFXButtonEliminar = jFXButtonEliminar;
	}


	public OpcionesControler(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Opciones.fxml"));
		//getStylesheets().add(getClass().getResource("Opciones.css").toExternalForm());
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}catch(IOException e){
	
			
		}
		
	}
}
