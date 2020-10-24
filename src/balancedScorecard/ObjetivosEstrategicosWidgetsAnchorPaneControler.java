package balancedScorecard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import balancedScorecard.objetivos.Perpectiva;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ObjetivosEstrategicosWidgetsAnchorPaneControler extends AnchorPane {
	

	
	public static int ID_PERPECTIVA_CLIENTE=1;;
	public static int  ID_PERPECTIVA_PROCESOS_INTERNOS=2;
	public static int  ID_PERPECTIVA_FINACIERA=4;
	public static int  ID_PERPECTIVA_APRENDIZAJE=3;
	public static String COLOR_PERPECTIVA_CLIENTE="#009ABF";
	public static String COLOR_PERPECTIVA_PROCESOS_INTERNOS="#008548";
	public static String COLOR_PERPECTIVA_FINACIERA="#5b0067";
	public static String COLOR_PERPECTIVA_APRENDIZAJE="#C37D0E  ";
	
	public static String NOMBRE_PERPECTIVA_CLIENTE="Enfoque en el Usuario";
	public static String NOMBRE_PERPECTIVA_PROCESOS_INTERNOS="Procesos Internos";
	public static String NOMBRE_PERPECTIVA_FINACIERA="Financiera";
	public static String NOMBRE_PERPECTIVA_APRENDIZAJE="Aprendizaje y Crecimiento";

	public static String nombrePerspectiva(int idPerspectiva){
		if(idPerspectiva==ID_PERPECTIVA_CLIENTE){
			return NOMBRE_PERPECTIVA_CLIENTE;
		}else if(idPerspectiva==ID_PERPECTIVA_PROCESOS_INTERNOS){
			return NOMBRE_PERPECTIVA_PROCESOS_INTERNOS;
			
		}else if(idPerspectiva==ID_PERPECTIVA_FINACIERA){
			return NOMBRE_PERPECTIVA_FINACIERA;
			
		}else if(idPerspectiva==ID_PERPECTIVA_APRENDIZAJE){
			return NOMBRE_PERPECTIVA_APRENDIZAJE;
		}
		return "vacio xD";
	}
	
	public static String colorCSSPerspectiva(int idPerspectiva){
		if(idPerspectiva==ID_PERPECTIVA_CLIENTE){
			return COLOR_PERPECTIVA_CLIENTE;
		}else if(idPerspectiva==ID_PERPECTIVA_PROCESOS_INTERNOS){
			return COLOR_PERPECTIVA_PROCESOS_INTERNOS;
			
		}else if(idPerspectiva==ID_PERPECTIVA_FINACIERA){
			return COLOR_PERPECTIVA_FINACIERA;
			
		}else if(idPerspectiva==ID_PERPECTIVA_APRENDIZAJE){
			return COLOR_PERPECTIVA_APRENDIZAJE;
		}
		return "vacio xD";
	}
	

	private int idObjetivoEstrategico=-1;
	private int TipoPerpectiva=-1;
	  
	
	
	public int getTipoPerpectiva() {
		return TipoPerpectiva;
	}

	public void setTipoPerpectiva(int tipoPerpectiva) {
		TipoPerpectiva = tipoPerpectiva;
	}

	public int getIdObjetivoEstrategico() {
		return idObjetivoEstrategico;
	}

	public void setIdObjetivoEstrategico(int idObjetivoEstrategico) {
		this.idObjetivoEstrategico = idObjetivoEstrategico;
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



	@FXML
	    private AnchorPane anchorPanePerspectiva;

	  
	  
	    @FXML
	    private Label labelPerspectiva;

	    @FXML
	    private JFXButton jFXButtonEditar;

	    public Label getLabelPeligro() {
			return labelPeligro;
		}

		public void setLabelPeligro(Label labelPeligro) {
			this.labelPeligro = labelPeligro;
		}

		public Label getLabelPrecaucion() {
			return labelPrecaucion;
		}

		public void setLabelPrecaucion(Label labelPrecaucion) {
			this.labelPrecaucion = labelPrecaucion;
		}

		public Label getLabelMeta() {
			return labelMeta;
		}

		public void setLabelMeta(Label labelMeta) {
			this.labelMeta = labelMeta;
		}

		public Label getLabelMetaSuperada() {
			return labelMetaSuperada;
		}

		public void setLabelMetaSuperada(Label labelMetaSuperada) {
			this.labelMetaSuperada = labelMetaSuperada;
		}

		public Label getLabelAccionesNoEjecutadas() {
			return labelAccionesNoEjecutadas;
		}

		public void setLabelAccionesNoEjecutadas(Label labelAccionesNoEjecutadas) {
			this.labelAccionesNoEjecutadas = labelAccionesNoEjecutadas;
		}


		@FXML
	    private JFXButton jFXButtonEliminar;

	    @FXML
	    private Label labelObjetivoEstrategico;
	
	    @FXML
	    private Label labelPeligro;

	    @FXML
	    private Label labelPrecaucion;

	    @FXML
	    private Label labelMeta;

	    @FXML
	    private Label labelMetaSuperada;

	    @FXML
	    private Label labelAccionesNoEjecutadas;
	 

	    public AnchorPane getAnchorPanePerspectiva() {
			return anchorPanePerspectiva;
		}

		public void setAnchorPanePerspectiva(AnchorPane anchorPanePerspectiva) {
			this.anchorPanePerspectiva = anchorPanePerspectiva;
		}

		public Label getLabelPerspectiva() {
			return labelPerspectiva;
		}

		public void setLabelPerspectiva(Label labelPerspectiva) {
			this.labelPerspectiva = labelPerspectiva;
		}

		public Label getLabelObjetivoEstrategico() {
			return labelObjetivoEstrategico;
		}

		public void setLabelObjetivoEstrategico(Label labelObjetivoEstrategico) {
			this.labelObjetivoEstrategico = labelObjetivoEstrategico;
		}

	
	
	public ObjetivosEstrategicosWidgetsAnchorPaneControler(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("ObjetivosEstrategicosWidgets.fxml"));
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
