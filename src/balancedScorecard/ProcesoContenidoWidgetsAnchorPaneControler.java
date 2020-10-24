package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.geom.Curve;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurve;

public class ProcesoContenidoWidgetsAnchorPaneControler extends AnchorPane{
	
private ObservableList<CubicCurve> arrayCubicCuerve=FXCollections.observableArrayList();


	public ObservableList<CubicCurve> getArrayCubicCuerve() {
	return arrayCubicCuerve;
}


public void setArrayCubicCuerve(ObservableList<CubicCurve> arrayCubicCuerve) {
	this.arrayCubicCuerve = arrayCubicCuerve;
}


	public ProcesoContenidoWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Widgets_Procesos.fxml"));
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
