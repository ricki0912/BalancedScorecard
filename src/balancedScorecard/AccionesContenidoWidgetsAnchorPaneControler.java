package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AccionesContenidoWidgetsAnchorPaneControler extends AnchorPane{
	
    @FXML
    private AnchorPane anchorPanePrincipalAcciones;

    @FXML
    private AnchorPane anchorPaneTituloAcciones;

    @FXML
    private JFXButton jFXButtonEditar;

    @FXML
    private JFXButton jFXButtonEliminar;

    @FXML
    private Label labelContenidoAccion;    
    
	@FXML Label labelTituloPerspectiva;
    
    public Label getLabelTituloPerspectiva() {
		return labelTituloPerspectiva;
	}



	public void setLabelTituloPerspectiva(Label labelTituloPerspectiva) {
		this.labelTituloPerspectiva = labelTituloPerspectiva;
	}



	public AnchorPane getAnchorPanePrincipalAcciones() {
		return anchorPanePrincipalAcciones;
	}



	public void setAnchorPanePrincipalAcciones(AnchorPane anchorPanePrincipalAcciones) {
		this.anchorPanePrincipalAcciones = anchorPanePrincipalAcciones;
	}



	public AnchorPane getAnchorPaneTituloAcciones() {
		return anchorPaneTituloAcciones;
	}



	public void setAnchorPaneTituloAcciones(AnchorPane anchorPaneTituloAcciones) {
		this.anchorPaneTituloAcciones = anchorPaneTituloAcciones;
	}



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



	public Label getLabelContenidoAccion() {
		return labelContenidoAccion;
	}



	public void setLabelContenidoAccion(Label labelContenidoAccion) {
		this.labelContenidoAccion = labelContenidoAccion;
	}



	public AccionesContenidoWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AccionesContenidoWidgets.fxml"));
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
