package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MetasAnadirWidgetsAnchorPaneControler extends AnchorPane{
	
  
    @FXML
    private AnchorPane anchorPaneTituloFecha;

    @FXML
    private Label labelTitulo;

    @FXML
    private FontAwesomeIconView jFXButtonEditar;

    @FXML
    private FontAwesomeIconView jFXButtonEliminar;

    @FXML
    private JFXButton jFXButtonAnadir;

    @FXML
    private FontAwesomeIconView fontAwesomeAnadir;
    
    
    public AnchorPane getAnchorPaneTituloFecha() {
		return anchorPaneTituloFecha;
	}


	public void setAnchorPaneTituloFecha(AnchorPane anchorPaneTituloFecha) {
		this.anchorPaneTituloFecha = anchorPaneTituloFecha;
	}


	public Label getLabelTitulo() {
		return labelTitulo;
	}


	public void setLabelTitulo(Label labelTitulo) {
		this.labelTitulo = labelTitulo;
	}


	public FontAwesomeIconView getjFXButtonEditar() {
		return jFXButtonEditar;
	}


	public void setjFXButtonEditar(FontAwesomeIconView jFXButtonEditar) {
		this.jFXButtonEditar = jFXButtonEditar;
	}


	public FontAwesomeIconView getjFXButtonEliminar() {
		return jFXButtonEliminar;
	}


	public void setjFXButtonEliminar(FontAwesomeIconView jFXButtonEliminar) {
		this.jFXButtonEliminar = jFXButtonEliminar;
	}


	public JFXButton getjFXButtonAnadir() {
		return jFXButtonAnadir;
	}


	public void setjFXButtonAnadir(JFXButton jFXButtonAnadir) {
		this.jFXButtonAnadir = jFXButtonAnadir;
	}


	public FontAwesomeIconView getFontAwesomeAnadir() {
		return fontAwesomeAnadir;
	}


	public void setFontAwesomeAnadir(FontAwesomeIconView fontAwesomeAnadir) {
		this.fontAwesomeAnadir = fontAwesomeAnadir;
	}


	public MetasAnadirWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Widgets_mes_anadir.fxml"));
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
