package balancedScorecard;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ObjetivoEstrategicosLateralWidgetsAnchorPaneControler extends AnchorPane{
	
	@FXML
    private AnchorPane anchorPanePanelPrincipal;

    @FXML
    private AnchorPane anchorPaneNombrePerspectiva;

    @FXML
    private Label labelNombrePerspectiva;

    @FXML
    private Label labelObjetivoEstrategico;
    
    public AnchorPane getAnchorPanePanelPrincipal() {
		return anchorPanePanelPrincipal;
	}




	public void setAnchorPanePanelPrincipal(AnchorPane anchorPanePanelPrincipal) {
		this.anchorPanePanelPrincipal = anchorPanePanelPrincipal;
	}




	public AnchorPane getAnchorPaneNombrePerspectiva() {
		return anchorPaneNombrePerspectiva;
	}




	public void setAnchorPaneNombrePerspectiva(AnchorPane anchorPaneNombrePerspectiva) {
		this.anchorPaneNombrePerspectiva = anchorPaneNombrePerspectiva;
	}




	public Label getLabelNombrePerspectiva() {
		return labelNombrePerspectiva;
	}




	public void setLabelNombrePerspectiva(Label labelNombrePerspectiva) {
		this.labelNombrePerspectiva = labelNombrePerspectiva;
	}




	public Label getLabelObjetivoEstrategico() {
		return labelObjetivoEstrategico;
	}




	public void setLabelObjetivoEstrategico(Label labelObjetivoEstrategico) {
		this.labelObjetivoEstrategico = labelObjetivoEstrategico;
	}





    
    
    
    
    public ObjetivoEstrategicosLateralWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ObjetivosEstrategicosWidgets_Lateral.fxml"));
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
