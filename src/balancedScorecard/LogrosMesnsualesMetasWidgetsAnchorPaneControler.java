package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public class LogrosMesnsualesMetasWidgetsAnchorPaneControler extends AnchorPane{
	
	
	
	  @FXML
	    private AnchorPane anchorPaneTituloFecha;

	    @FXML
	    private Label labelTitulo;

	    @FXML
	    private FontAwesomeIconView jFXButtonEditar;

	    @FXML
	    private FontAwesomeIconView jFXButtonEliminar;

	    @FXML
	    private Label labelSubtitulo;

	    @FXML
	    private Label labelLogroAlcanzado;

	    @FXML
	    private ProgressBar progressBarLogroAlcanzado;




	public LogrosMesnsualesMetasWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Widgets_mes_.fxml"));
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




	public Label getLabelSubtitulo() {
		return labelSubtitulo;
	}




	public void setLabelSubtitulo(Label labelSubtitulo) {
		this.labelSubtitulo = labelSubtitulo;
	}




	public Label getLabelLogroAlcanzado() {
		return labelLogroAlcanzado;
	}




	public void setLabelLogroAlcanzado(Label labelLogroAlcanzado) {
		this.labelLogroAlcanzado = labelLogroAlcanzado;
	}




	public ProgressBar getProgressBarLogroAlcanzado() {
		return progressBarLogroAlcanzado;
	}




	public void setProgressBarLogroAlcanzado(ProgressBar progressBarLogroAlcanzado) {
		this.progressBarLogroAlcanzado = progressBarLogroAlcanzado;
	}

}
