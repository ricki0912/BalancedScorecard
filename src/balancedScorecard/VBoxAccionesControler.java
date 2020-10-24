package balancedScorecard;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class VBoxAccionesControler extends VBox {
	
	
	
	public VBoxAccionesControler(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("VBoxAcciones.fxml"));
		//getStylesheets().add(getClass().getResource("togleButtonFoto.css").toExternalForm());
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println(e);
			
		}
	}
	
}
