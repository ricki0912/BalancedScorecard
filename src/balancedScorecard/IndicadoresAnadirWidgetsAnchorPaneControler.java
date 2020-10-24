package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class IndicadoresAnadirWidgetsAnchorPaneControler extends AnchorPane{
	
  



    public AnchorPane getAnchorPaneTituloIndicador() {
		return anchorPaneTituloIndicador;
	}



	public void setAnchorPaneTituloIndicador(AnchorPane anchorPaneTituloIndicador) {
		this.anchorPaneTituloIndicador = anchorPaneTituloIndicador;
	}



	public JFXButton getjFXButtonAnadir() {
		return jFXButtonAnadir;
	}



	public void setjFXButtonAnadir(JFXButton jFXButtonAnadir) {
		this.jFXButtonAnadir = jFXButtonAnadir;
	}



	public FontAwesomeIconView getFontAwesomePlus_circle() {
		return fontAwesomePlus_circle;
	}



	public void setFontAwesomePlus_circle(FontAwesomeIconView fontAwesomePlus_circle) {
		this.fontAwesomePlus_circle = fontAwesomePlus_circle;
	}



	@FXML
    private AnchorPane anchorPaneTituloIndicador;

    @FXML
    private JFXButton jFXButtonAnadir;

    @FXML
    private FontAwesomeIconView fontAwesomePlus_circle;
    
    
    
    public IndicadoresAnadirWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("IndicadoresWidgets_Anadir.fxml"));
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
