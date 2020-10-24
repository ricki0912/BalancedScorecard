package balancedScorecard;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class VBoxIndicadoresControler extends VBox {
	
	
	
	public VBoxIndicadoresControler(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("VBoxIndicadores.fxml"));
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
