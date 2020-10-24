package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class IndicadoresContenidoWidgetsAnchorPaneControler extends AnchorPane{
	@FXML private HBox hBoxOpcionesIndicador;
	@FXML private JFXButton jFXButtonResetearLogros;
	
	
	
	public HBox gethBoxOpcionesIndicador() {
		return hBoxOpcionesIndicador;
	}





	public void sethBoxOpcionesIndicador(HBox hBoxOpcionesIndicador) {
		this.hBoxOpcionesIndicador = hBoxOpcionesIndicador;
	}





	public JFXButton getjFXButtonResetearLogros() {
		return jFXButtonResetearLogros;
	}





	public void setjFXButtonResetearLogros(JFXButton jFXButtonResetearLogros) {
		this.jFXButtonResetearLogros = jFXButtonResetearLogros;
	}
	@FXML private JFXButton jFXButtonMetas;
	
  
	public JFXButton getjFXButtonMetas() {
		return jFXButtonMetas;
	}





	public void setjFXButtonMetas(JFXButton jFXButtonMetas) {
		this.jFXButtonMetas = jFXButtonMetas;
	}
	@FXML
    private AnchorPane anchorPanetituloIndicador;

    @FXML
    private Label labelTituloIndicador;

    public AnchorPane getAnchorPanetituloIndicador() {
		return anchorPanetituloIndicador;
	}
    @FXML private Label labelValorActualContenido ,     labelPeligroContendido ,
    labelPrecaucionContendido;




	public Label getLabelValorActualContenido() {
		return labelValorActualContenido;
	}





	public void setLabelValorActualContenido(Label labelValorActualContenido) {
		this.labelValorActualContenido = labelValorActualContenido;
	}





	public Label getLabelPeligroContendido() {
		return labelPeligroContendido;
	}





	public void setLabelPeligroContendido(Label labelPeligroContendido) {
		this.labelPeligroContendido = labelPeligroContendido;
	}





	public Label getLabelPrecaucionContendido() {
		return labelPrecaucionContendido;
	}





	public void setLabelPrecaucionContendido(Label labelPrecaucionContendido) {
		this.labelPrecaucionContendido = labelPrecaucionContendido;
	}





	public void setAnchorPanetituloIndicador(AnchorPane anchorPanetituloIndicador) {
		this.anchorPanetituloIndicador = anchorPanetituloIndicador;
	}





	public Label getLabelTituloIndicador() {
		return labelTituloIndicador;
	}





	public void setLabelTituloIndicador(Label labelTituloIndicador) {
		this.labelTituloIndicador = labelTituloIndicador;
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





	public Label getLabelIndicadorIndicador() {
		return labelIndicadorIndicador;
	}





	public void setLabelIndicadorIndicador(Label labelIndicadorIndicador) {
		this.labelIndicadorIndicador = labelIndicadorIndicador;
	}





	public Label getLabelFormulaContendido() {
		return labelFormulaContendido;
	}





	public void setLabelFormulaContendido(Label labelFormulaContendido) {
		this.labelFormulaContendido = labelFormulaContendido;
	}





	public Label getLabelMetaContendido() {
		return labelMetaContendido;
	}





	public void setLabelMetaContendido(Label labelMetaContendido) {
		this.labelMetaContendido = labelMetaContendido;
	}





	public Label getLabelResponsablesContendido() {
		return labelResponsablesContendido;
	}





	public void setLabelResponsablesContendido(Label labelResponsablesContendido) {
		this.labelResponsablesContendido = labelResponsablesContendido;
	}





	@FXML
    private JFXButton jFXButtonEditar;

    @FXML
    private JFXButton jFXButtonEliminar;

    @FXML
    private Label labelIndicadorIndicador;

    @FXML
    private Label labelFormulaContendido;

    @FXML
    private Label labelMetaContendido;

    @FXML
    private Label labelResponsablesContendido; 
    




	public IndicadoresContenidoWidgetsAnchorPaneControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("IndicadoresWidgets_Contenido.fxml"));
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
