package balancedScorecard;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class HBoxObjetivoAccionesIndicadoresControler extends HBox {
	
	public HBoxObjetivoAccionesIndicadoresControler(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("HBoxObjetivoAccionesIndicadores.fxml"));
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
