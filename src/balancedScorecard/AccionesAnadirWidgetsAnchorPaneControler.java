package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class AccionesAnadirWidgetsAnchorPaneControler extends AnchorPane{
	
    public AnchorPane getAnchorPanePrincipal() {
		return anchorPanePrincipal;
	}




	public void setAnchorPanePrincipal(AnchorPane anchorPanePrincipal) {
		this.anchorPanePrincipal = anchorPanePrincipal;
	}




	public AnchorPane getAnchorPaneTituloPerspectiva() {
		return anchorPaneTituloPerspectiva;
	}




	public void setAnchorPaneTituloPerspectiva(AnchorPane anchorPaneTituloPerspectiva) {
		this.anchorPaneTituloPerspectiva = anchorPaneTituloPerspectiva;
	}




	public JFXButton getjFXButtonAnadir() {
		return jFXButtonAnadir;
	}




	public void setjFXButtonAnadir(JFXButton jFXButtonAnadir) {
		this.jFXButtonAnadir = jFXButtonAnadir;
	}




	public FontAwesomeIconView getFontAwwesomeIconPlus_Circle() {
		return fontAwwesomeIconPlus_Circle;
	}




	public void setFontAwwesomeIconPlus_Circle(FontAwesomeIconView fontAwwesomeIconPlus_Circle) {
		this.fontAwwesomeIconPlus_Circle = fontAwwesomeIconPlus_Circle;
	}




	@FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private AnchorPane anchorPaneTituloPerspectiva;

    @FXML
    private JFXButton jFXButtonAnadir;

    @FXML
    private FontAwesomeIconView fontAwwesomeIconPlus_Circle;
    
    
    
    
    public AccionesAnadirWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ObjetivosEstrategicosWidgets_Anadir.fxml"));
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
