package balancedScorecard.indicadores;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import balancedScorecard.ObjetivosEstrategicosWidgetsAnchorPaneControler;
import funciones.Conexion;
import funciones.Funciones;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;




public class Indicadores_controler extends Funciones implements Initializable {
	
	
	public static final int TIPO_INDICADOR_PORCENTAJE=2;
	public static final int TIPO_INDICADOR_NUMERO_CANTIDAD=1;
	public static final String TIPO_INDICADOR_PORCENTAJE_NOMBRE="Porcentaje";
	public static final String TIPO_INDICADOR_NUMERO_CANTIDAD_NOMBRE="Numero/Cantidad ";
	
	
	public static final int TIPO_MEDICION_DIA=1;
	public static final int TIPO_MEDICION_SEMANAL=2;
	public static final int TIPO_MEDICION_MENSUAL=3;
	public static final int TIPO_MEDICION_BIMESTRAL=4;
	public static final int TIPO_MEDICION_TRIMESTRAL=5;
	public static final int TIPO_MEDICION_SEMESTRAL=6;
	public static final int TIPO_MEDICION_ANUAL=7;
	public static final String TIPO_MEDICION_DIA_NOMBRE="Diario";
	public static final String TIPO_MEDICION_SEMANAL_NOMBRE="Semanal";
	public static final String TIPO_MEDICION_MENSUAL_NOMBRE="Mensual";
	public static final String TIPO_MEDICION_BIMESTRAL_NOMBRE="Bimestral";
	public static final String TIPO_MEDICION_TRIMESTRAL_NOMBRE="Trimestral";
	public static final String TIPO_MEDICION_SEMESTRAL_NOMBRE="Semestral";
	public static final String TIPO_MEDICION_ANUAL_NOMBRE="Anual";
	
	
	
	@FXML private Line lineDivision ;
	
	@FXML
    private JFXTextField jfxTextFieldFormula;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonGuardar;

    @FXML
    private JFXTextField jfxTextFieldMeta;

    @FXML
    private JFXTextField jfxTextFieldValorActual;

    @FXML
    private JFXDatePicker jFXDatePickerFechaInicial;

    @FXML
    private JFXDatePicker jFXDatePickerFechaFinal;

    @FXML
    private JFXTextField jfxTextFieldEspecifiqueIndicador;

    @FXML
    private JFXTextField jfxTextFieldPeligroBase;

    @FXML
    private JFXTextField jfxTextFieldPrecauciónBase;

    @FXML
    private JFXTextField jfxTextFieldPrecaucionFinal;

    @FXML
    private JFXTextField jfxTextFieldMetaBase;

    @FXML
    private JFXTextField jfxTextFieldMetaFinal;

    @FXML
    private Label labelCondicionPeligro;

    @FXML
    private Label labelCondicionPrecaucion;

    @FXML
    private Label labelCondicionMeta;

    @FXML
    private JFXTextField jfxTextFieldPeligroFinal;

    @FXML
    private Arc ArcMetaAzul;

    @FXML
    private Arc ArcMetaVerde;

    @FXML
    private Arc ArcMetaPrecaucion;

    @FXML
    private Arc ArcMetaPeligro;

    @FXML
    private Label labelLogroAlcanzado;

    @FXML
    private Arc ArcFlechaIndicador;

    @FXML
    private Label labelValorMaximo;

    @FXML
    private Label labelValorMinimoPeligro;

    @FXML
    private JFXComboBox<TipoIndicador> comboBoxTipoIndicador;
    @FXML private JFXComboBox<TipoMedicion> jFXComboBoxTipoMedicion;

    @FXML
    private JFXTextField jFXTextFieldVarCantidadEspecificaPor;

    @FXML
    private JFXTextField jFXTextFieldVarCantidadTotalPor;

    @FXML
    private JFXTextField jFXTextFieldVarCantidadNumeroNum;

    @FXML
    private JFXTextField jfxTextFieldResponsables;

    @FXML
    private Label labelInformacion;

    @FXML
    private JFXButton buttonClose;


    private ObservableList<TipoMedicion> arrayComboBoxTipoMedcion=FXCollections.observableArrayList();
    private ObservableList<TipoIndicador> arrayComboBoxTipoIndicador=FXCollections.observableArrayList();
   
	
	
	
	public void mostrarTipoMedicion(){
		//arrayComboBoxTipoMedcion.add(new TipoMedicion(Indicadores_controler.TIPO_MEDICION_DIA,Indicadores_controler.TIPO_MEDICION_DIA_NOMBRE));
		//arrayComboBoxTipoMedcion.add(new TipoMedicion(Indicadores_controler.TIPO_MEDICION_SEMANAL,Indicadores_controler.TIPO_MEDICION_SEMANAL_NOMBRE));
		arrayComboBoxTipoMedcion.add(new TipoMedicion(Indicadores_controler.TIPO_MEDICION_MENSUAL,Indicadores_controler.TIPO_MEDICION_MENSUAL_NOMBRE));
		arrayComboBoxTipoMedcion.add(new TipoMedicion(Indicadores_controler.TIPO_MEDICION_BIMESTRAL,Indicadores_controler.TIPO_MEDICION_BIMESTRAL_NOMBRE));
		arrayComboBoxTipoMedcion.add(new TipoMedicion(Indicadores_controler.TIPO_MEDICION_TRIMESTRAL,Indicadores_controler.TIPO_MEDICION_TRIMESTRAL_NOMBRE));
		arrayComboBoxTipoMedcion.add(new TipoMedicion(Indicadores_controler.TIPO_MEDICION_SEMESTRAL,Indicadores_controler.TIPO_MEDICION_SEMESTRAL_NOMBRE));
		arrayComboBoxTipoMedcion.add(new TipoMedicion(Indicadores_controler.TIPO_MEDICION_ANUAL,Indicadores_controler.TIPO_MEDICION_ANUAL_NOMBRE));
		jFXComboBoxTipoMedicion.setItems(arrayComboBoxTipoMedcion);
		
	}
	
	 public void mostrarTipoIndicador(){
		 arrayComboBoxTipoIndicador.add(new TipoIndicador(Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD, Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD_NOMBRE));
		 arrayComboBoxTipoIndicador.add(new TipoIndicador(Indicadores_controler.TIPO_INDICADOR_PORCENTAJE , Indicadores_controler.TIPO_INDICADOR_PORCENTAJE_NOMBRE));
		 comboBoxTipoIndicador.setItems(arrayComboBoxTipoIndicador);
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private int id_accion_estrategica;
	public int getId_accion_estrategica() {
		return id_accion_estrategica;
	}


	public void setId_accion_estrategica(int id_accion_estrategica) {
		this.id_accion_estrategica = id_accion_estrategica;
	}






	public static int OPCION_ACTUALIZAR= 1;
	public static int OPCION_GUARDAR=2;
	
	private int opcionEvento =2;
	

    public int getOpcionEvento() {
		return opcionEvento;
	}


	public void setOpcionEvento(int opcionEvento) {
		this.opcionEvento = opcionEvento;
		if(opcionEvento==OPCION_ACTUALIZAR){
			mostrarSeleccionarDatos();
			mostrarSemaforo(Double.parseDouble((!jfxTextFieldPeligroBase.getText().isEmpty())?jfxTextFieldPeligroBase.getText():"0"),
						Double.parseDouble((!jfxTextFieldPeligroFinal.getText().isEmpty())?jfxTextFieldPeligroFinal.getText():"0"),
						Double.parseDouble((!jfxTextFieldPrecaucionFinal.getText().isEmpty())?jfxTextFieldPrecaucionFinal.getText():"0"),
						Double.parseDouble((!jfxTextFieldMeta.getText().isEmpty())?jfxTextFieldMeta.getText():"0"),
						Double.parseDouble((!jfxTextFieldValorActual.getText().isEmpty())?jfxTextFieldValorActual.getText():"0"));
			
		}
	}





    EventHandler<KeyEvent> eventHandlerKeyEventNumeroDecimales=new EventHandler<KeyEvent>() 
	{
	    @Override
	    public void handle(KeyEvent ke) 
	    {
	        String character = ke.getCharacter();
	        String text = ((JFXTextField)ke.getSource()).getText();//jfxTextFieldMeta.getText();
	        

	         if ( !Funciones.isValid_forDouble(text, character, 999999.99) )
	             ke.consume(); 
	         
	         
	 		mostrarSemaforo(Double.parseDouble((!jfxTextFieldPeligroBase.getText().isEmpty())?jfxTextFieldPeligroBase.getText():"0"),
	 						Double.parseDouble((!jfxTextFieldPeligroFinal.getText().isEmpty())?jfxTextFieldPeligroFinal.getText():"0"),
	 						Double.parseDouble((!jfxTextFieldPrecaucionFinal.getText().isEmpty())?jfxTextFieldPrecaucionFinal.getText():"0"),
	 						Double.parseDouble((!jfxTextFieldMeta.getText().isEmpty())?jfxTextFieldMeta.getText():"0"),
	 						Double.parseDouble((!jfxTextFieldValorActual.getText().isEmpty())?jfxTextFieldValorActual.getText():"0"));

	    }
	};
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		jfxTextFieldFormula.setText("Formula");
		mostrarTipoIndicador();
		mostrarTipoMedicion();
		
		jfxTextFieldValorActual.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		//jfxTextFieldMeta.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		jfxTextFieldValorActual.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		jfxTextFieldPeligroBase.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		jfxTextFieldPeligroFinal.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		jfxTextFieldPrecauciónBase.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		jfxTextFieldPrecaucionFinal.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		jfxTextFieldMetaBase.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		//jfxTextFieldMetaFinal.setOnKeyReleased(eventHandlerKeyEventNumeroDecimales);
		
		
		
		comboBoxTipoIndicador.valueProperty().addListener(new ChangeListener<TipoIndicador>() {

			@Override
			public void changed(ObservableValue<? extends TipoIndicador> observable, TipoIndicador oldValue,
					TipoIndicador newValue) {
				if(comboBoxTipoIndicador.getSelectionModel().getSelectedItem().getId()==Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD){
					jFXTextFieldVarCantidadNumeroNum.setVisible(true);
					jFXTextFieldVarCantidadEspecificaPor.setVisible(false);
					jFXTextFieldVarCantidadTotalPor.setVisible(false);
					lineDivision.setVisible(false);
				}else{
					if(comboBoxTipoIndicador.getSelectionModel().getSelectedItem().getId()==Indicadores_controler.TIPO_INDICADOR_PORCENTAJE){
						jFXTextFieldVarCantidadNumeroNum.setVisible(false);
						jFXTextFieldVarCantidadEspecificaPor.setVisible(true);
						jFXTextFieldVarCantidadTotalPor.setVisible(true);
						lineDivision.setVisible(true);
					}
				}
			}
		});		
		
		
		
		
		jfxTextFieldMeta.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				
				  String character = ke.getCharacter();
			        String text = ((JFXTextField)ke.getSource()).getText();//jfxTextFieldMeta.getText();
			        

			         if ( !Funciones.isValid_forDouble(text, character, 999999.99) )
			             ke.consume(); 
			         
						jfxTextFieldMetaFinal.setText(jfxTextFieldMeta.getText());
			 		mostrarSemaforo(Double.parseDouble((!jfxTextFieldPeligroBase.getText().isEmpty())?jfxTextFieldPeligroBase.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldPeligroFinal.getText().isEmpty())?jfxTextFieldPeligroFinal.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldPrecaucionFinal.getText().isEmpty())?jfxTextFieldPrecaucionFinal.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldMeta.getText().isEmpty())?jfxTextFieldMeta.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldValorActual.getText().isEmpty())?jfxTextFieldValorActual.getText():"0"));

			    
				
				
			}
		});
		
		
		jfxTextFieldMetaFinal.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				
				  String character = ke.getCharacter();
			        String text = ((JFXTextField)ke.getSource()).getText();//jfxTextFieldMeta.getText();
			        

			         if ( !Funciones.isValid_forDouble(text, character, 999999.99) )
			             ke.consume(); 
			         
			         jfxTextFieldMeta.setText(jfxTextFieldMetaFinal.getText());
			 		mostrarSemaforo(Double.parseDouble((!jfxTextFieldPeligroBase.getText().isEmpty())?jfxTextFieldPeligroBase.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldPeligroFinal.getText().isEmpty())?jfxTextFieldPeligroFinal.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldPrecaucionFinal.getText().isEmpty())?jfxTextFieldPrecaucionFinal.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldMeta.getText().isEmpty())?jfxTextFieldMeta.getText():"0"),
			 						Double.parseDouble((!jfxTextFieldValorActual.getText().isEmpty())?jfxTextFieldValorActual.getText():"0"));

			}
		});
		
		

		
		restricciones();
		
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				JFXButton button=(JFXButton)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
	
		
		buttonCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
		buttonGuardar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(verificarCamposVacios()){
					if(opcionEvento==OPCION_GUARDAR){
						insertarDatos();
					}else{
						
						actualizarDatos();
					}
					insertarDatosMetaIncial();

				}
				}
		});
	
		//mostrarSemaforo(Double.parseDouble(jfxTextFieldPeligroBase.getText()), Double.parseDouble(jfxTextFieldPeligroFinal.getText()),Double.parseDouble(jfxTextFieldPrecaucionFinal.getText()), Double.parseDouble(jfxTextFieldMeta.getText()),Double.parseDouble(jfxTextFieldValorActual.getText()));


	}
	
	
	public void restricciones(){
		jfxTextFieldEspecifiqueIndicador.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = jfxTextFieldEspecifiqueIndicador.getStyleClass();
		        if (jfxTextFieldEspecifiqueIndicador.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});
		
		
		
	}

	
	public boolean verificarCamposVacios(){
		
		
		ObservableList<String> styleClass = jfxTextFieldEspecifiqueIndicador.getStyleClass();

		
		
		//especifique indicador 
		styleClass=jfxTextFieldEspecifiqueIndicador.getStyleClass();
		if(jfxTextFieldEspecifiqueIndicador.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldEspecifiqueIndicador.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		//combo box
		styleClass = comboBoxTipoIndicador.getStyleClass();
		if(comboBoxTipoIndicador.getSelectionModel().getSelectedItem()==null){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			comboBoxTipoIndicador.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		//formula xD
		
		
		if(comboBoxTipoIndicador.getSelectionModel().getSelectedItem().getId()==Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD){
			styleClass = jFXTextFieldVarCantidadNumeroNum.getStyleClass();
			if(jFXTextFieldVarCantidadNumeroNum.getText().trim().isEmpty()){
				if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
				jFXTextFieldVarCantidadNumeroNum.requestFocus();
				return false;
			}else{
			    styleClass.removeAll(Collections.singleton("error"));

			}
		}else{
			
			styleClass = jFXTextFieldVarCantidadEspecificaPor.getStyleClass();

			if(jFXTextFieldVarCantidadEspecificaPor.getText().trim().isEmpty()){
				if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
				jFXTextFieldVarCantidadEspecificaPor.requestFocus();
				return false;
			}else{
			    styleClass.removeAll(Collections.singleton("error"));

			}
			
			
			
			styleClass = jFXTextFieldVarCantidadTotalPor.getStyleClass();

			if(jFXTextFieldVarCantidadTotalPor.getText().trim().isEmpty()){
				if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
				jFXTextFieldVarCantidadTotalPor.requestFocus();
				return false;
			}else{
			    styleClass.removeAll(Collections.singleton("error"));

			}
		}
		
		//intervalo de medicion
		styleClass = jFXDatePickerFechaInicial.getStyleClass();

		try{
			if(jFXDatePickerFechaInicial.getValue()==null){
				if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
				jFXDatePickerFechaInicial.requestFocus();
				return false;
			}else{
			    styleClass.removeAll(Collections.singleton("error"));

			}
		}catch(Exception e){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXDatePickerFechaInicial.requestFocus();
			return false;
		}
		
		
		styleClass = jFXDatePickerFechaFinal.getStyleClass();

		try{
			if(jFXDatePickerFechaFinal.getValue()==null){
				if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
				jFXDatePickerFechaFinal.requestFocus();
				return false;
			}else{
			    styleClass.removeAll(Collections.singleton("error"));

			}
		}catch(Exception e){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXDatePickerFechaFinal.requestFocus();
			return false;
		}

		
		styleClass=jfxTextFieldValorActual.getStyleClass();
		if(jfxTextFieldValorActual.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldValorActual.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		styleClass=jfxTextFieldMeta.getStyleClass();
		if(jfxTextFieldMeta.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldMeta.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		
		styleClass=jfxTextFieldPeligroBase.getStyleClass();
		if(jfxTextFieldPeligroBase.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldPeligroBase.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		styleClass=jfxTextFieldPeligroFinal.getStyleClass();
		if(jfxTextFieldPeligroFinal.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldPeligroFinal.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		styleClass=jfxTextFieldPrecaucionFinal.getStyleClass();
		if(jfxTextFieldPrecaucionFinal.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldPrecaucionFinal.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}

		styleClass=jfxTextFieldMetaFinal.getStyleClass();
		if(jfxTextFieldMetaFinal.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldMetaFinal.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		styleClass=jfxTextFieldResponsables.getStyleClass();
		if(jfxTextFieldResponsables.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jfxTextFieldResponsables.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		styleClass=jFXComboBoxTipoMedicion.getStyleClass();
		if(jFXComboBoxTipoMedicion.getSelectionModel().getSelectedItem()==null){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXComboBoxTipoMedicion.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		
		if(Double.parseDouble(jfxTextFieldPeligroBase.getText().trim())<Double.parseDouble(jfxTextFieldPeligroFinal.getText().trim()) && 
				Double.parseDouble(jfxTextFieldPeligroFinal.getText().trim())<Double.parseDouble(jfxTextFieldPrecaucionFinal.getText().trim()) &&
				Double.parseDouble(jfxTextFieldPrecaucionFinal.getText().trim())<Double.parseDouble(jfxTextFieldMetaFinal.getText().trim()) 
				){
			
			
		}else if(Double.parseDouble(jfxTextFieldPeligroBase.getText().trim())>Double.parseDouble(jfxTextFieldPeligroFinal.getText().trim()) && 
				Double.parseDouble(jfxTextFieldPeligroFinal.getText().trim())>Double.parseDouble(jfxTextFieldPrecaucionFinal.getText().trim()) &&
				Double.parseDouble(jfxTextFieldPrecaucionFinal.getText().trim())>Double.parseDouble(jfxTextFieldMetaFinal.getText().trim()) 
			) {
			
		}else{
			jfxTextFieldPeligroBase.requestFocus();
			return false;
		}
		
		return true;
		
	}
	

	
	public void insertarDatos(){
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
              pst = conn.prepareStatement("INSERT INTO INDICADOR(ID,NOMBRE, FORMULA,META_ROJO_INICIAL,META_ROJO_FINAL,META_AMARILLO_INICIAL,META_AMARILLO_FINAL,META_VERDE_INICIAL,META_VERDE_FINAL,RESPONSABLE, VALOR_ACTUAL,    "
              		+ " TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS, FECHA_INICIO, FECHA_FINAL, MEDIR_POR  )"+
              "VALUES(?,?, ?,?,?,?,?,?,?,?,?,"
              + " ?,?,?,?,?,?,?   );"); 
              
              
              pst.setInt(1, id_accion_estrategica );
              
              if(!jfxTextFieldEspecifiqueIndicador.getText().trim().isEmpty()){
                  pst.setString(2, jfxTextFieldEspecifiqueIndicador.getText().trim());
              }else{
            	  pst.setNull(2,java.sql.Types.VARCHAR);
              }

              
              if(!jfxTextFieldFormula.getText().trim().isEmpty()){
            	  pst.setString(3, jfxTextFieldFormula.getText().trim());
              }else{
            	  pst.setNull(3, java.sql.Types.VARCHAR);
              }
              
          
              if(!jfxTextFieldPeligroBase.getText().trim().isEmpty()){
            	  pst.setDouble(4, Double.parseDouble(jfxTextFieldPeligroBase.getText().trim()));
              }else{
            	  pst.setNull(4,java.sql.Types.DOUBLE);
              }
              
              if(!jfxTextFieldPeligroFinal.getText().trim().isEmpty()){
            	  pst.setDouble(5, Double.parseDouble(jfxTextFieldPeligroFinal.getText().trim()));
              }else{
            	  pst.setNull(5, java.sql.Types.DOUBLE);
              }
              
              if(!jfxTextFieldPrecauciónBase.getText().trim().isEmpty()){
            	  pst.setDouble(6, Double.parseDouble(jfxTextFieldPrecauciónBase.getText().trim()));
              }else{
            	  pst.setDouble(6, java.sql.Types.DOUBLE);
              }
              
              if(!jfxTextFieldPrecaucionFinal.getText().trim().isEmpty()){
            	  pst.setDouble(7, Double.parseDouble(jfxTextFieldPrecaucionFinal.getText().trim()));
              }else{
            	  pst.setNull(7,java.sql.Types.DOUBLE);
              }
              
              if(!jfxTextFieldMetaBase.getText().trim().isEmpty()){
            	  pst.setDouble(8, Double.parseDouble(jfxTextFieldMetaBase.getText().trim()));
              }else{
            	  pst.setNull(8, java.sql.Types.DOUBLE);
              }
              
                         
              if(!jfxTextFieldMeta.getText().trim().isEmpty()){
            	  pst.setString(9, jfxTextFieldMeta.getText().trim());
              }else{
            	  pst.setNull(9, java.sql.Types.VARCHAR);
              }
            
              if(!jfxTextFieldResponsables.getText().trim().isEmpty()){
            	  pst.setString(10, jfxTextFieldResponsables.getText().trim());
              }else{
            	  pst.setNull(10,java.sql.Types.VARCHAR);
              }
              
              
              if(!jfxTextFieldValorActual.getText().trim().isEmpty()){
            	  pst.setDouble(11, Double.parseDouble(jfxTextFieldValorActual.getText().trim()));
              }else{
            	  pst.setNull(11, java.sql.Types.DOUBLE);
              }
              
              
              //lo ultimos siete agregados
              if(comboBoxTipoIndicador.getSelectionModel().getSelectedItem()!=null){
            	  pst.setInt(12, comboBoxTipoIndicador.getSelectionModel().getSelectedItem().getId() );
              }else{
            	  pst.setNull(12, java.sql.Types.INTEGER);
              }
              
              if(!jFXTextFieldVarCantidadNumeroNum.getText().trim().isEmpty()){
            	  pst.setString(13, jFXTextFieldVarCantidadNumeroNum.getText().trim());
              }else{
            	  pst.setNull(13,java.sql.Types.VARCHAR);
              }
              
              

              if(!jFXTextFieldVarCantidadEspecificaPor.getText().trim().isEmpty()){
            	  pst.setString(14, jFXTextFieldVarCantidadEspecificaPor.getText().trim());
              }else{
            	  pst.setNull(14,java.sql.Types.VARCHAR);
              }
              
              if(!jFXTextFieldVarCantidadTotalPor.getText().trim().isEmpty()){
            	  pst.setString(15, jFXTextFieldVarCantidadTotalPor.getText().trim());
              }else{
            	  pst.setNull(15,java.sql.Types.VARCHAR);
              }
                
          	if(jFXDatePickerFechaInicial.getValue()!=null){
				java.util.Date dateRegistroConadis =java.util.Date.from(jFXDatePickerFechaInicial.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDateRegistroConadis = new java.sql.Date(dateRegistroConadis.getTime());
				pst.setDate(16,sqlDateRegistroConadis);
				
			}else{
				pst.setNull(16,java.sql.Types.DATE);
			}
            
          	
          	if(jFXDatePickerFechaFinal.getValue()!=null){
				java.util.Date dateRegistroConadis =java.util.Date.from(jFXDatePickerFechaFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDateRegistroConadis = new java.sql.Date(dateRegistroConadis.getTime());
				pst.setDate(17,sqlDateRegistroConadis);
				
			}else{
				pst.setNull(17,java.sql.Types.DATE);
			}
          	
          	
          	
          	 if(jFXComboBoxTipoMedicion.getSelectionModel().getSelectedItem()!=null){
           	  pst.setInt(18, jFXComboBoxTipoMedicion.getSelectionModel().getSelectedItem().getId() );
             }else{
           	  pst.setNull(18, java.sql.Types.INTEGER);
             }
              
              
               int rs = pst.executeUpdate();
               if(rs==1){
            	   labelInformacion.setText("Inserción Correcta");;
            	   
            	  
               }else {
            	   labelInformacion.setText("Error :(...");;

            	   
            	   
               } 
               conn.close();
               pst.close();
               if (! styleClass.contains("labelInformacioncorrecto")) {
	                styleClass.add("labelInformacioncorrecto");
	              
	            }
               
               
               
           }   
           catch(Exception e){
        	   
        	   
        	   if (! styleClass.contains("labelInformacionincorrecto")) {
                   styleClass.add("labelInformacionincorrecto");
                 
               }
        	   labelInformacion.setText("Error :(..."+e.getMessage());;
               e.printStackTrace();
          
          
		  }finally{
        	   try {
        		   if(pst!=null){
        			   pst.close();
				
        	   		}if(conn!=null){
        	   			conn.close();
        	   		}
        	   		if(rsset!=null){
        	   			rsset.close();
        	   		}
        	   
        	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
        	   
           }
		 
		
		
	}
	
	

	
	public void actualizarDatos(){
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("UPDATE INDICADOR SET NOMBRE=?, FORMULA=?,META_ROJO_INICIAL=?,META_ROJO_FINAL=?,META_AMARILLO_INICIAL=?,META_AMARILLO_FINAL=?,META_VERDE_INICIAL=?,META_VERDE_FINAL=?,RESPONSABLE=?, VALOR_ACTUAL=?, "
               		+ ""
               		+ " TIPO_FORMULA=?, VAR_NUMERO_UNO=?, VAR_PORCENTAJE_UNO=?, VAR_PORCENTAJE_DOS=?, FECHA_INICIO=?, FECHA_FINAL=?, MEDIR_POR=? "
               		+ ""
               		+ " WHERE ID=?;"); 
               
               if(!jfxTextFieldEspecifiqueIndicador.getText().trim().isEmpty()){
                   pst.setString(1, jfxTextFieldEspecifiqueIndicador.getText().trim());
               }else{
             	  pst.setNull(1,java.sql.Types.VARCHAR);
               }
               
               if(!jfxTextFieldFormula.getText().trim().isEmpty()){
             	  pst.setString(2, jfxTextFieldFormula.getText().trim());
               }else{
             	  pst.setNull(2, java.sql.Types.VARCHAR);
               }
               
               if(!jfxTextFieldPeligroBase.getText().trim().isEmpty()){
             	  pst.setDouble(3, Double.parseDouble(jfxTextFieldPeligroBase.getText().trim()));
               }else{
             	  pst.setNull(3,java.sql.Types.DOUBLE);
               }
               if(!jfxTextFieldPeligroFinal.getText().trim().isEmpty()){
             	  pst.setDouble(4, Double.parseDouble(jfxTextFieldPeligroFinal.getText().trim()));
               }else{
             	  pst.setNull(4, java.sql.Types.DOUBLE);
               }
               
               if(!jfxTextFieldPrecauciónBase.getText().trim().isEmpty()){
             	  pst.setDouble(5, Double.parseDouble(jfxTextFieldPrecauciónBase.getText().trim()));
               }else{
             	  pst.setDouble(5, java.sql.Types.DOUBLE);
               }
               if(!jfxTextFieldPrecaucionFinal.getText().trim().isEmpty()){
             	  pst.setDouble(6, Double.parseDouble(jfxTextFieldPrecaucionFinal.getText().trim()));
               }else{
             	  pst.setNull(6,java.sql.Types.DOUBLE);
               }
               
               if(!jfxTextFieldMetaBase.getText().trim().isEmpty()){
             	  pst.setDouble(7, Double.parseDouble(jfxTextFieldMetaBase.getText().trim()));
               }else{
             	  pst.setNull(7, java.sql.Types.DOUBLE);
               }
               
                          
               if(!jfxTextFieldMeta.getText().trim().isEmpty()){
             	  pst.setString(8, jfxTextFieldMeta.getText().trim());
               }else{
             	  pst.setNull(8, java.sql.Types.VARCHAR);
               }
             
               if(!jfxTextFieldResponsables.getText().trim().isEmpty()){
             	  pst.setString(9, jfxTextFieldResponsables.getText().trim());
               }else{
             	  pst.setNull(9,java.sql.Types.VARCHAR);
               }
               
               
               if(!jfxTextFieldValorActual.getText().trim().isEmpty()){
            	   pst.setDouble(10, Double.parseDouble(jfxTextFieldValorActual.getText().trim()));
               }else{
            	   pst.setNull(10, java.sql.Types.DOUBLE);
               }
             
               
               
               
               //ultimos siete agregados xD
               
               if(comboBoxTipoIndicador.getSelectionModel().getSelectedItem()!=null){
             	  pst.setInt(11, comboBoxTipoIndicador.getSelectionModel().getSelectedItem().getId() );
               }else{
             	  pst.setNull(11, java.sql.Types.INTEGER);
               }
               
               if(!jFXTextFieldVarCantidadNumeroNum.getText().trim().isEmpty()){
             	  pst.setString(12, jFXTextFieldVarCantidadNumeroNum.getText().trim());
               }else{
            	   
             	  //pst.setNull(12,java.sql.Types.VARCHAR);
             	  pst.setString(12, "Campo Vacio");
               }
               
               

               if(!jFXTextFieldVarCantidadEspecificaPor.getText().trim().isEmpty()){
             	  pst.setString(13, jFXTextFieldVarCantidadEspecificaPor.getText().trim());
               }else{
             	  //pst.setNull(13,java.sql.Types.VARCHAR);
             	 pst.setString(13, "Campo Vacio");
               }
               
               
               if(!jFXTextFieldVarCantidadTotalPor.getText().trim().isEmpty()){
             	  pst.setString(14, jFXTextFieldVarCantidadTotalPor.getText().trim());
               }else{
            	   pst.setString(14, "Campo Vacio");
             	  //pst.setNull(14,java.sql.Types.VARCHAR);
               }
                 
           	if(jFXDatePickerFechaInicial.getValue()!=null){
 				java.util.Date dateRegistroConadis =java.util.Date.from(jFXDatePickerFechaInicial.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
 				java.sql.Date sqlDateRegistroConadis = new java.sql.Date(dateRegistroConadis.getTime());
 				pst.setDate(15,sqlDateRegistroConadis);
 				
 			}else{
 				pst.setNull(15,java.sql.Types.DATE);
 			}
             
           	
           	if(jFXDatePickerFechaFinal.getValue()!=null){
 				java.util.Date dateRegistroConadis =java.util.Date.from(jFXDatePickerFechaFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
 				java.sql.Date sqlDateRegistroConadis = new java.sql.Date(dateRegistroConadis.getTime());
 				pst.setDate(16,sqlDateRegistroConadis);
 				
 			}else{
 				pst.setNull(16,java.sql.Types.DATE);
 			}
           	
           	
           	
           	 if(jFXComboBoxTipoMedicion.getSelectionModel().getSelectedItem()!=null){
            	  pst.setInt(17, jFXComboBoxTipoMedicion.getSelectionModel().getSelectedItem().getId() );
              }else{
            	  pst.setNull(17, java.sql.Types.INTEGER);
              }
               
             
           	 //al final siemtpre where despues 
             pst.setInt(18, id_accion_estrategica );
               
               int rs = pst.executeUpdate();
               if(rs==1){
            	   labelInformacion.setText("Actualización Correcta");;
            	   
            	  
               }else {
            	   labelInformacion.setText("Error :(...");;

            	   
            	   
               } 
               conn.close();
               pst.close();
               if (! styleClass.contains("labelInformacioncorrecto")) {
	                styleClass.add("labelInformacioncorrecto");
	              
	            }
               
               
               
           }   
           catch(Exception e){
        	   
        	   
        	   if (! styleClass.contains("labelInformacionincorrecto")) {
                   styleClass.add("labelInformacionincorrecto");
                 
               }
        	   labelInformacion.setText("Error :(..."+e.getMessage());;
               e.printStackTrace();
          
          
		  }finally{
        	   try {
        		   if(pst!=null){
        			   pst.close();
				
        	   		}if(conn!=null){
        	   			conn.close();
        	   		}
        	   		if(rsset!=null){
        	   			rsset.close();
        	   		}
        	   
        	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
        	   
           }
		 
		
		
	}
	

	



	public void mostrarSeleccionarDatos() {
	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(" SELECT NOMBRE, VALOR_ACTUAL, FORMULA,META_ROJO_INICIAL,META_ROJO_FINAL,META_AMARILLO_INICIAL,"
					+ " META_AMARILLO_FINAL,META_VERDE_INICIAL,META_VERDE_FINAL,RESPONSABLE,"
					+ ""
					+ "TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS, FECHA_INICIO, FECHA_FINAL, MEDIR_POR "
					+ ""
					+ " FROM INDICADOR WHERE ID=?;");
			pst.setInt(1, id_accion_estrategica);
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				jfxTextFieldEspecifiqueIndicador.setText(rs.getString("NOMBRE"));
				jfxTextFieldFormula.setText("Formula");
				jfxTextFieldPeligroBase.setText(rs.getString("META_ROJO_INICIAL"));
				jfxTextFieldPeligroFinal.setText(rs.getString("META_ROJO_FINAL"));
				jfxTextFieldPrecauciónBase.setText(rs.getString("META_AMARILLO_INICIAL"));
				jfxTextFieldPrecaucionFinal.setText(rs.getString("META_AMARILLO_FINAL"));
				jfxTextFieldMetaBase.setText(rs.getString("META_VERDE_INICIAL"));
				jfxTextFieldMetaFinal.setText(rs.getString("META_VERDE_FINAL"));
				jfxTextFieldMeta.setText(rs.getString("META_VERDE_FINAL"));
				jfxTextFieldResponsables.setText(rs.getString("RESPONSABLE"));
				
				jfxTextFieldValorActual.setText(rs.getString("VALOR_ACTUAL"));
				
				//ultimos siete agregados xD
				comboBoxTipoIndicador.getSelectionModel().select(new TipoIndicador(rs.getInt("TIPO_FORMULA"), ""));
				jFXTextFieldVarCantidadNumeroNum.setText(rs.getString("VAR_NUMERO_UNO"));
				jFXTextFieldVarCantidadEspecificaPor.setText(rs.getString("VAR_PORCENTAJE_UNO"));
				jFXTextFieldVarCantidadTotalPor.setText(rs.getString("VAR_PORCENTAJE_DOS"));
				jFXDatePickerFechaInicial.setValue(rs.getDate("FECHA_INICIO").toLocalDate());
				jFXDatePickerFechaFinal.setValue(rs.getDate("FECHA_FINAL").toLocalDate());
				jFXComboBoxTipoMedicion.getSelectionModel().select(new TipoMedicion(rs.getInt("MEDIR_POR"), ""));

				
			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();

				}
				if (pst != null) {
					pst.close();

				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

	
	
public void mostrarSemaforo( double peligroIncial, double peligro, double precaucion, double meta, double metaAlcanzada ){
	

		
		double tamano=0;
		double anguloPeligro=0;
		double anguloPrecaucion=0;
		double anguloMetaVerde=0;
		double anguloMetaSuperadaAzul=0;
		double anguloMetaAlcanzada=0;
		
		if(meta>peligroIncial){
			if(metaAlcanzada>meta){
				tamano=metaAlcanzada-peligroIncial;
				
			}else{
				tamano=meta-peligroIncial;
			}
			
			//angulo peligro
			anguloPeligro=((peligro-peligroIncial)*180)/tamano;
			ArcMetaPeligro.setStartAngle(180-anguloPeligro);
			ArcMetaPeligro.setLength(anguloPeligro);
			
			//angulo Precaucion
			anguloPrecaucion=((precaucion-peligroIncial)*180)/tamano;
			ArcMetaPrecaucion.setStartAngle(180-anguloPrecaucion);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);
			
			//angulo Meta
			anguloMetaVerde=((meta-peligroIncial)*180)/tamano;
			ArcMetaVerde.setStartAngle(180-anguloMetaVerde);
			ArcMetaVerde.setLength(anguloMetaVerde);
			
			//angulo metaoptima
			
			//angulo meta alcanzada indicasor
			anguloMetaAlcanzada=((metaAlcanzada-peligroIncial)*180)/tamano;
			
			ArcFlechaIndicador.setStartAngle(180-anguloMetaAlcanzada-1);
			
			
			labelCondicionPeligro.setText("<=f(x)<=");
			labelCondicionPrecaucion.setText("<f(x)<=");
			labelCondicionMeta.setText("<f(x)<=");
			
			labelValorMaximo.setText(meta+"");
			labelValorMinimoPeligro.setText(peligroIncial+"");
			
			//new hilosIndicador(anguloMetaAlcanzada);
			
			
		}else{
			if(metaAlcanzada<meta){
				tamano=peligroIncial-metaAlcanzada;
				
			}else{
				tamano=peligroIncial-meta;
			}
			
			
			//angulo peligro
			anguloPeligro=((peligroIncial-peligro)*180)/tamano;
			ArcMetaPeligro.setStartAngle(0);
			ArcMetaPeligro.setLength(anguloPeligro);
			
			//angulo Precaucion
			anguloPrecaucion=((peligroIncial-precaucion)*180)/tamano;
			ArcMetaPrecaucion.setStartAngle(0);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);
			
			//angulo Meta
			anguloMetaVerde=((peligroIncial-meta)*180)/tamano;
			ArcMetaVerde.setStartAngle(0);
			ArcMetaVerde.setLength(anguloMetaVerde);
			
			//angulo metaoptima
			
			//angulo meta alcanzada
			
			anguloMetaAlcanzada=((peligroIncial-metaAlcanzada)*180)/tamano;
			ArcFlechaIndicador.setStartAngle(anguloMetaAlcanzada-1);
			
			
			labelCondicionPeligro.setText(">=f(x)>=");
			labelCondicionPrecaucion.setText(">f(x)>=");
			labelCondicionMeta.setText(">f(x)>=");
			
			labelValorMaximo.setText(peligroIncial+"");
			labelValorMinimoPeligro.setText(meta+"");
		}
		
		
		labelLogroAlcanzado.setText(jfxTextFieldValorActual.getText());
		
		jfxTextFieldPrecauciónBase.setText(jfxTextFieldPeligroFinal.getText());
		jfxTextFieldMetaBase.setText(jfxTextFieldPrecaucionFinal.getText());
	}
	
	



public void insertarDatosMetaIncial() {
	ObservableList<String> styleClass = labelInformacion.getStyleClass();
	styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));
	styleClass.removeAll(Collections.singleton("labelInformacioncorrecto"));

	String mensaje = null;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rsset = null;
	try {

	

		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement(
				" INSERT INTO META_LOGRADA(VALOR_ACTUAL,TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS, DATO_NUMERO_UNO, "
						+ " DATO_PORCENTAJE_UNO, DATO_PORCENTAJE_DOS, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL,"
						+ " META_VERDE_INICIAL, META_VERDE_FINAL, RESPONSABLE, FECHA, ID_INDICADOR ) SELECT VALOR_ACTUAL,TIPO_FORMULA, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS,"
						+ " ?,  ?, ?, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL, META_VERDE_INICIAL, META_VERDE_FINAL, RESPONSABLE, "
						+ "    "
						+ " ?, ? FROM   INDICADOR WHERE ID=?;");

		if (comboBoxTipoIndicador.getSelectionModel().getSelectedItem().getId() == Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD) {
			pst.setDouble(1, 0.0);
			;
			pst.setNull(2, java.sql.Types.DOUBLE);
			;
			pst.setNull(3, java.sql.Types.DOUBLE);
			;
		} else if (comboBoxTipoIndicador.getSelectionModel().getSelectedItem().getId() == Indicadores_controler.TIPO_INDICADOR_PORCENTAJE) {
			pst.setNull(1, java.sql.Types.DOUBLE);
			;
			pst.setDouble(2, 0);
			;
			pst.setDouble(3, 1);
			;

		}

		//pst.setInt(4, id_accion_estrategica);
		java.util.Date date = java.util.Date
				.from(jFXDatePickerFechaInicial.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		pst.setDate(4, sqlDate);
		pst.setInt(5, id_accion_estrategica);
		pst.setInt(6, id_accion_estrategica);

		int rs = pst.executeUpdate();
		if (rs == 1) {
			labelInformacion.setText("Inserción Correcta");
			;

		} else {
			labelInformacion.setText("Error :(...");
			;

		}
		conn.close();
		pst.close();
		if (!styleClass.contains("labelInformacioncorrecto")) {
			styleClass.add("labelInformacioncorrecto");

		}

	} catch (Exception e) {

		if (!styleClass.contains("labelInformacionincorrecto")) {
			styleClass.add("labelInformacionincorrecto");

		}
		labelInformacion.setText("Error :(..." + e.getMessage());
		;
		e.printStackTrace();

	} finally {
		try {
			if (pst != null) {
				pst.close();

			}
			if (conn != null) {
				conn.close();
			}
			if (rsset != null) {
				rsset.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}

	


  

}