package cargando;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;







 
public class CargandoControlerAnchorPane extends AnchorPane {
	@FXML ProgressIndicator progressIndicatorCargando;

   public ProgressIndicator getProgressIndicatorCargando() {
		return progressIndicatorCargando;
	}

	public void setProgressIndicatorCargando(ProgressIndicator progressIndicatorCargando) {
		this.progressIndicatorCargando = progressIndicatorCargando;
	}

public CargandoControlerAnchorPane (){
	 	FXMLLoader loader=new FXMLLoader(getClass().getResource("CargandoInterfaz.fxml"));
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