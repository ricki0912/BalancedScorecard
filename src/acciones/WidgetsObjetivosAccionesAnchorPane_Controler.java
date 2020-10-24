package acciones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import balancedScorecard.AccionesContenidoWidgetsAnchorPaneControler;
import balancedScorecard.IndicadoresAnadirWidgetsAnchorPaneControler;
import balancedScorecard.IndicadoresContenidoWidgetsAnchorPaneControler;
import balancedScorecard.ObjetivosEstrategicosWidgetsAnchorPaneControler;
import balancedScorecard.acciones.Acciones_controler;
import balancedScorecard.indicadores.Indicadores_controler;
import balancedScorecard.metas.Meta_controler;
import funciones.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sesion.Sesion;



public class WidgetsObjetivosAccionesAnchorPane_Controler extends AnchorPane {
	
	
	private HBox  contenedorIndicador=null;


	public HBox getContenedorIndicador() {
		return contenedorIndicador;
	}


	public void setContenedorIndicador(HBox contenedorIndicador) {
		this.contenedorIndicador = contenedorIndicador;
	}


	private WidgetsObjetivosAccionesAnchorPane_Controler widgetsAcciones=null;
	
	public WidgetsObjetivosAccionesAnchorPane_Controler getWidgetsAcciones() {
		return widgetsAcciones;
	}


	public void setWidgetsAcciones(WidgetsObjetivosAccionesAnchorPane_Controler widgetsAcciones) {
		this.widgetsAcciones = widgetsAcciones;
	}


	private int perspectiva=-1;
	private int id_objetivo=-1;
	
	public int getPerspectiva() {
		return perspectiva;
	}


	public int getId_objetivo() {
		return id_objetivo;
	}


	public void setId_objetivo(int id_objetivo) {
		this.id_objetivo = id_objetivo;
	}


	public void setPerspectiva(int perspectiva) {
		this.perspectiva = perspectiva;
	}


	public  static final int OPCION_OBJETIVO=1;
	public  static final int OPCION_ACCIONES=2;
	
	private int opcionSeleccionada=1;
	

    public int getOpcionSeleccionada() {
		return opcionSeleccionada;
	}


	public void setOpcionSeleccionada(int opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
		
		if(opcionSeleccionada==OPCION_OBJETIVO){
			mostrarDatosObjetivos();
		}else if(opcionSeleccionada==OPCION_ACCIONES){
			mostrarDatosAcciones();
		}
	}


	
	
	
	

	    @FXML
	    private BorderPane BorderPaneEncabezado;

	    @FXML
	    private Label labelTitulo;

	    @FXML
	    private JFXButton jfxButtonNuevo;

	

	    @FXML
	    private TableColumn<ObjetivoAcciones_Class, String> tableColumnOpciones;
	
	
	
	@FXML
    private AnchorPane anchorPanePanelPrincipal;

    @FXML
    private AnchorPane anchorPaneNombreObjetivo_Acc;

    @FXML
    private Label labelNombreObjet_acc;

    @FXML
    private TableView<ObjetivoAcciones_Class> tableView;

    @FXML
    private TableColumn<ObjetivoAcciones_Class, String> tableColumnNro;

    @FXML
    private TableColumn<ObjetivoAcciones_Class, String> tablecolumn;
    
    
    private ObservableList<ObjetivoAcciones_Class> arrayTableView=FXCollections.observableArrayList();
    
    
    
	
	public WidgetsObjetivosAccionesAnchorPane_Controler(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("WidgetsObjetivosAccionesAnchorPane.fxml"));
		//getStylesheets().add(getClass().getResource("togleButtonFoto.css").toExternalForm());
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println(e);
			
		}
		inicializarTableView();
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
			if(opcionSeleccionada==WidgetsObjetivosAccionesAnchorPane_Controler.OPCION_OBJETIVO){
				System.out.println("prueba del indicador ");
				int id_objetivo=tableView.getSelectionModel().getSelectedItem().getId();
				widgetsAcciones.setId_objetivo(tableView.getSelectionModel().getSelectedItem().getId());;
				widgetsAcciones.setOpcionSeleccionada(WidgetsObjetivosAccionesAnchorPane_Controler.OPCION_ACCIONES);
				
				


				
				widgetsAcciones.getJfxButtonNuevo().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						JFXButton hyperLinkCambiarContrasena=(JFXButton)event.getSource();
						
						Stage stagess=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
						AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
						stackPane.setVisible(true);
						
						
						
						String urlFxml = "/balancedScorecard/acciones/Acciones_Interfaz.fxml";
						String css = "/balancedScorecard/acciones/acciones_Interfaz.css";
						try {
							
							System.out.println("Hola estoy dento de, metodo de modal :D");
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							Acciones_controler mvc=fXMLLoader.getController();
							mvc.setId_objetivo(tableView.getSelectionModel().getSelectedItem().getId());
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							Stage stage=new Stage();
							stage.setScene(scene);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.initStyle(StageStyle.TRANSPARENT);
							
							
							
							stage.showAndWait();;
							
							
							
							
							widgetsAcciones.setId_objetivo(id_objetivo);;
							widgetsAcciones.setOpcionSeleccionada(WidgetsObjetivosAccionesAnchorPane_Controler.OPCION_ACCIONES);

						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						stackPane.setVisible(false);
						
					}
				});
			
			}else if(opcionSeleccionada==WidgetsObjetivosAccionesAnchorPane_Controler.OPCION_ACCIONES){
				int id_accion=tableView.getSelectionModel().getSelectedItem().getId();
				mostrarIndicadores(id_accion);
			}
				
				
			}
		});
		//mostrarDatosObjetivos();
		//System.out.println("holaaaaaaaa pinche... que haces leyendo :V");
	}
	

	public TableColumn<ObjetivoAcciones_Class, String> getTablecolumn() {
		return tablecolumn;
	}


	public void setTablecolumn(TableColumn<ObjetivoAcciones_Class, String> tablecolumn) {
		this.tablecolumn = tablecolumn;
	}


	public BorderPane getBorderPaneEncabezado() {
		return BorderPaneEncabezado;
	}


	public void setBorderPaneEncabezado(BorderPane borderPaneEncabezado) {
		BorderPaneEncabezado = borderPaneEncabezado;
	}


	public Label getLabelTitulo() {
		return labelTitulo;
	}


	public void setLabelTitulo(Label labelTitulo) {
		this.labelTitulo = labelTitulo;
	}


	public JFXButton getJfxButtonNuevo() {
		return jfxButtonNuevo;
	}


	public void setJfxButtonNuevo(JFXButton jfxButtonNuevo) {
		this.jfxButtonNuevo = jfxButtonNuevo;
	}


	public TableColumn<ObjetivoAcciones_Class, String> getTableColumnOpciones() {
		return tableColumnOpciones;
	}


	public void setTableColumnOpciones(TableColumn<ObjetivoAcciones_Class, String> tableColumnOpciones) {
		this.tableColumnOpciones = tableColumnOpciones;
	}


	public static int getOpcionAcciones() {
		return OPCION_ACCIONES;
	}

	
	
	public void mostrarIndicadores(int indicador){
		contenedorIndicador.getChildren().clear();
		
		Connection conn = null;
	
		
		PreparedStatement pstIndicadores = null;
		ResultSet rsIndicadores= null;
		
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
		
		
		
		
		
	
		pstIndicadores = conn.prepareStatement(" SELECT ID, VALOR_ACTUAL, NOMBRE, FORMULA,TIPO_FORMULA, VAR_NUMERO_UNO , VAR_PORCENTAJE_UNO , VAR_PORCENTAJE_DOS, "
				+ " FECHA_INICIO, FECHA_FINAL, MEDIR_POR, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL,META_AMARILLO_FINAL, "
				+ " META_VERDE_INICIAL, META_VERDE_FINAL,  RESPONSABLE FROM INDICADOR WHERE ID=?; ");
		pstIndicadores.setInt(1,indicador);
		
	
		
		rsIndicadores = pstIndicadores.executeQuery();
	
		
		if(rsIndicadores.next()){
			
				//anadir indicador
				//IndicadoresContenidoWidgetsAnchorPaneControler aux=new IndicadoresContenidoWidgetsAnchorPaneControler();
				int id_indicador=rsIndicadores.getInt("ID");
				int tipo_indicadorr=rsIndicadores.getInt("TIPO_FORMULA");
				String varNum=rsIndicadores.getString("VAR_NUMERO_UNO");
				String varPor1=rsIndicadores.getString("VAR_PORCENTAJE_UNO");
				String varPor2=rsIndicadores.getString("VAR_PORCENTAJE_DOS");
				IndicadoresContenidoWidgetsAnchorPaneControler widgetsIndicador= new IndicadoresContenidoWidgetsAnchorPaneControler();
				widgetsIndicador.getLabelIndicadorIndicador().setText(rsIndicadores.getString("NOMBRE"));
				widgetsIndicador.getAnchorPanetituloIndicador().setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(perspectiva) +";");
				
				
				widgetsIndicador.getLabelFormulaContendido().setText(rsIndicadores.getString("FORMULA"));
				  if(Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD==rsIndicadores.getInt("TIPO_FORMULA")){
						

					  widgetsIndicador.getLabelFormulaContendido().setText(rsIndicadores.getString("VAR_NUMERO_UNO"));
					  //xYChartSeriesMetaLogradaPeriodo.getData().add(new XYChart.Data(String.valueOf(rsset.getDate("FECHA")), rs.getDouble("DATO_NUMERO_UNO")));

         		}else if(Indicadores_controler.TIPO_INDICADOR_PORCENTAJE==rsIndicadores.getInt("TIPO_FORMULA")){
         			
					
         			  widgetsIndicador.getLabelFormulaContendido().setText(rsIndicadores.getString("VAR_PORCENTAJE_UNO")+"/"+rsIndicadores.getString("VAR_PORCENTAJE_DOS"));


         		}
				
				
				
				
				
				widgetsIndicador.getLabelResponsablesContendido().setText(rsIndicadores.getString("RESPONSABLE"));
				//aux.getLabelFormulaContendido().setText("");
				widgetsIndicador.getLabelValorActualContenido().setText(rsIndicadores.getString("VALOR_ACTUAL"));
				
				//widgetsIndicador.getjFXButtonMetas().setVisible((Sesion.ROL==1)?true:false);
				widgetsIndicador.getjFXButtonResetearLogros().setVisible((Sesion.ROL==1)?true:false);
				widgetsIndicador.getjFXButtonResetearLogros().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
						Alert alert = new Alert(AlertType.CONFIRMATION, "Cuidado con esta opción.. \nTen en cuenta que se eliminará todos los logros registrados hasta la fecha.. \n¿Está seguro que desea continuar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
						alert.showAndWait();

						if (alert.getResult() == ButtonType.YES) {
							eliminarLogros(id_indicador);
							
						}
						
						
					}
				});
				
				
				widgetsIndicador.getjFXButtonMetas().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						
						
						Stage stagess=(Stage)widgetsIndicador.getjFXButtonEditar().getScene().getWindow();
						AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
						stackPane.setVisible(true);
						System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));
					
						String urlFxml = "/balancedScorecard/metas/Metas_Interfaz.fxml";
						String css = "/balancedScorecard/metas/indicadores_Interfaz.css";
						try {
							
							System.out.println("Hola estoy dento de, metodo de modal :D");
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							Meta_controler mvc=fXMLLoader.getController();
							mvc.setTipo_indicador(tipo_indicadorr);
							mvc.setId_indicador(id_indicador);
							mvc.getjFXTextFieldVarCantidadNumeroNum().setPromptText(varNum);
							mvc.getjFXTextFieldVarCantidadEspecificaPor().setPromptText(varPor1);
							mvc.getjFXTextFieldVarCantidadTotalPor().setPromptText(varPor2);
							//mvc.setId_accion_estrategica(id_indicador);
							//mvc.setOpcionEvento(Indicadores_controler.OPCION_ACTUALIZAR);

						   // mvc.setId_accion_estrategica(id_indicador);
							
							
							
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							Stage stage=new Stage();
							stage.setScene(scene);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.initStyle(StageStyle.TRANSPARENT);
							
							
							
							stage.showAndWait();;
							
							
							
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						stackPane.setVisible(false);
						
						
						
					}
				});

				if(rsIndicadores.getDouble("META_VERDE_FINAL")>=rsIndicadores.getDouble("META_ROJO_INICIAL")){
					widgetsIndicador.getLabelPeligroContendido().setText(rsIndicadores.getString("META_ROJO_INICIAL")+"<=f(x)<="+rsIndicadores.getString("META_ROJO_FINAL"));
					widgetsIndicador.getLabelPrecaucionContendido().setText(rsIndicadores.getString("META_ROJO_FINAL")+"<f(x)<="+rsIndicadores.getString("META_AMARILLO_FINAL"));
					widgetsIndicador.getLabelMetaContendido().setText(rsIndicadores.getString("META_AMARILLO_FINAL")+"<f(x)<="+rsIndicadores.getString("META_VERDE_FINAL"));
					//aux.getLabelMetaContendido().setText(rsIndicadores.getString("META_VERDE_FINAL"));
				}else {
					widgetsIndicador.getLabelPeligroContendido().setText(rsIndicadores.getString("META_ROJO_INICIAL")+">=f(x)>="+rsIndicadores.getString("META_ROJO_FINAL"));
					widgetsIndicador.getLabelPrecaucionContendido().setText(rsIndicadores.getString("META_ROJO_FINAL")+">f(x)>="+rsIndicadores.getString("META_AMARILLO_FINAL"));
					widgetsIndicador.getLabelMetaContendido().setText(rsIndicadores.getString("META_AMARILLO_FINAL")+">f(x)>="+rsIndicadores.getString("META_VERDE_FINAL"));
				}
				
				widgetsIndicador.getjFXButtonEditar().setVisible((Sesion.ROL==1)?true:false);
				widgetsIndicador.getjFXButtonEliminar().setVisible((Sesion.ROL==1)?true:false);
				widgetsIndicador.getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
						alert.showAndWait();

						if (alert.getResult() == ButtonType.YES) {
							eliminarIndicador(id_indicador);
							mostrarIndicadores(id_indicador);
						}
					}
				});
				widgetsIndicador.getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						
						Stage stagess=(Stage)widgetsIndicador.getjFXButtonEditar().getScene().getWindow();
						AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
						stackPane.setVisible(true);
						System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));
					
						String urlFxml = "/balancedScorecard/indicadores/Indicadores_Interfaz.fxml";
						String css = "/balancedScorecard/indicadores/indicadores_Interfaz.css";
						try {
							
							System.out.println("Hola estoy dento de, metodo de modal :D");
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							Indicadores_controler mvc=fXMLLoader.getController();
							mvc.setId_accion_estrategica(id_indicador);
							mvc.setOpcionEvento(Indicadores_controler.OPCION_ACTUALIZAR);

						    mvc.setId_accion_estrategica(id_indicador);
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							Stage stage=new Stage();
							stage.setScene(scene);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.initStyle(StageStyle.TRANSPARENT);
							
							
							
							stage.showAndWait();;
							
							mostrarIndicadores(id_indicador);
							
							
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						stackPane.setVisible(false);
						
						
					}
				});
				contenedorIndicador.getChildren().add(widgetsIndicador);

			
			

		}else{
		
			IndicadoresAnadirWidgetsAnchorPaneControler indicadores=new IndicadoresAnadirWidgetsAnchorPaneControler();
			indicadores.getAnchorPaneTituloIndicador().setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(perspectiva) +";");
			indicadores.setVisible((Sesion.ROL==1)?true:false);
			indicadores.getFontAwesomePlus_circle().setStyle(" -fx-fill: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(perspectiva) +";"
															+"-fx-effect :  dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5); ");
			//indicadores.getjFXButtonAnadir().setVisible((Sesion.ROL==1)?true:false);
			indicadores.getjFXButtonAnadir().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					Stage stagess=(Stage)indicadores.getjFXButtonAnadir().getScene().getWindow();
					AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
					stackPane.setVisible(true);
					System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));

					String urlFxml = "/balancedScorecard/indicadores/Indicadores_Interfaz.fxml";
					String css = "/balancedScorecard/indicadores/indicadores_Interfaz.css";
					try {
						
						System.out.println("Hola estoy dento de, metodo de modal :D");
						FXMLLoader fXMLLoader=new FXMLLoader();
						fXMLLoader.setLocation(getClass().getResource(urlFxml));
						fXMLLoader.load();
						Indicadores_controler mvc=fXMLLoader.getController();
						
					    mvc.setId_accion_estrategica(indicador);
						Parent parent= fXMLLoader.getRoot();
						Scene scene=new Scene(parent);
						scene.setFill(new Color(0,0,0,0));
						scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
						Stage stage=new Stage();
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initStyle(StageStyle.TRANSPARENT);
						
						
						
						stage.showAndWait();;
						
						mostrarIndicadores(indicador);						
						
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					stackPane.setVisible(false);

				}
			});
			
			
			
			contenedorIndicador.getChildren().add(indicadores);

	
		}
		pstIndicadores.close();
		rsIndicadores.close();
		
		
		
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
			
				
				if (pstIndicadores != null) {
					pstIndicadores.close();

				}
				
				if(rsIndicadores!=null){
					rsIndicadores.close();
				}
				
				
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
	}
	
	

	public void inicializarTableView(){
		tableView.setItems(arrayTableView);
		tableColumnNro.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tablecolumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));		
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones")); 
		
	}
	
	
	public void mostrarDatosObjetivos(){
		mostrarDatosObjetivos("");
	}
	public void mostrarDatosObjetivos(String buscar) {
		// vamos limpiar el contenedor de los botones
		tableView.getItems().clear();
		// hacer la conexcioon con la base de datos y obtener alas busquedas
		// encontradas
		 Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID, NOMBRE, PERSPECTIVA FROM objetivos WHERE PERSPECTIVA=?; ;");
			//pst.setString(1, "%" + buscar + "%");
			pst.setInt(1, perspectiva);
			
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			int contador=1;
			while (rs.next()) {
				ObjetivoAcciones_Class auxiliar=new ObjetivoAcciones_Class(rs.getInt("ID"), contador, rs.getString("NOMBRE"));
				
				
				tableView.getItems().add(auxiliar);
						contador++;

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
	
	
	
	public void mostrarDatosAcciones(){
		mostrarDatosAcciones("");
	}
	public void mostrarDatosAcciones(String buscar) {
		// vamos limpiar el contenedor de los botones
		tableView.getItems().clear();
		// hacer la conexcioon con la base de datos y obtener alas busquedas
		// encontradas
		 Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID, NOMBRE FROM acciones WHERE ID_OBJETIVOS=? ;");
			//pst.setString(1, "%" + buscar + "%");
			pst.setInt(1, id_objetivo);
			
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			int contador=1;
			while (rs.next()) {
				ObjetivoAcciones_Class auxiliar=new ObjetivoAcciones_Class(rs.getInt("ID"), contador, rs.getString("NOMBRE"));
				int id_acccion_auxiliar=rs.getInt("ID");
				auxiliar.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
						alert.showAndWait();

						if (alert.getResult() == ButtonType.YES) {
							eliminarAccion(id_acccion_auxiliar);
							mostrarDatosAcciones();
						}
						
					}
				});
				
				
				auxiliar.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {

						
						JFXButton hyperLinkCambiarContrasena=(JFXButton)arg0.getSource();
						
						Stage stagess=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
						AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
						stackPane.setVisible(true);
						
						
						
						
						String urlFxml = "/balancedScorecard/acciones/Acciones_Interfaz.fxml";
						String css = "/balancedScorecard/acciones/acciones_Interfaz.fxml";
						try {
							
							
							

							System.out.println("Hola estoy dento de, metodo de modal :D");
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							
							Acciones_controler mvc=fXMLLoader.getController();
							mvc.setId_accion(id_acccion_auxiliar);

							mvc.setId_objetivo(id_objetivo);
							mvc.setEvento(Acciones_controler.OPCION_ACTUALIZAR);
							
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							Stage stage=new Stage();
							stage.setScene(scene);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.initStyle(StageStyle.TRANSPARENT);
							
							
							
							stage.showAndWait();;
							
							
							
							
							mostrarDatosAcciones();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						stackPane.setVisible(false);

					
					
						
						
					}
				});
				
				tableView.getItems().add(auxiliar);
						contador++;

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
	
	

	public void eliminarIndicador(int id_indicador){
		
      
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement(" delete from INDICADOR where id = ?;"); 
               pst.setInt(1, id_indicador);
               int rs = pst.executeUpdate();
               
               conn.close();
               pst.close();
            
               
               
               
           }   
           catch(SQLException e){
        	   
				Alert alert = new Alert(AlertType.ERROR, "Error :( ?"+e.getMessage(),ButtonType.CANCEL);
				alert.showAndWait();
        	  
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
	
	
	public void eliminarAccion(int id_accion){
		
	      
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement(" delete from ACCIONES where id = ?;"); 
               pst.setInt(1, id_accion);
               int rs = pst.executeUpdate();
               
               conn.close();
               pst.close();
            
               
               
               
           }   
           catch(SQLException e){
        	   
				Alert alert = new Alert(AlertType.ERROR, "Error :( ?"+e.getMessage(),ButtonType.CANCEL);
				alert.showAndWait();
        	  
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
	
	public void eliminarLogros(int id_indicador){
		
		  
			String mensaje=null;
			  Connection conn=null;
			  PreparedStatement pst=null;
			  ResultSet rsset=null;
			  try{     
				
				  
	              Conexion.conectarDB(); 
				  conn=Conexion.getConexion();
	               pst = conn.prepareStatement(" delete from meta_lograda where id_indicador = ?;"); 
	               pst.setInt(1, id_indicador);
	               int rs = pst.executeUpdate();
	               
	               conn.close();
	               pst.close();
	            
	               
	               
	               Alert alert = new Alert(AlertType.INFORMATION, "Acción realizada correctamente :) ",ButtonType.CANCEL);
					alert.showAndWait();
	           }   
	           catch(SQLException e){
	        	   
					Alert alert = new Alert(AlertType.ERROR, "Error :( ?"+e.getMessage(),ButtonType.CANCEL);
					alert.showAndWait();
	        	  
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
	
	
	

}
