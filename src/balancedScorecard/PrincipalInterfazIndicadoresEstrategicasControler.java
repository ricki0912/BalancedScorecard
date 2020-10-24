package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import acciones.WidgetsObjetivosAccionesAnchorPane_Controler;
import balancedScorecard.acciones.Acciones_controler;
import balancedScorecard.mision_vision.Mision_Vision_controler;
import balancedScorecard.objetivos.Objetivos_controler;
import funciones.Conexion;
import funciones.Funciones;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sesion.Sesion;

public class PrincipalInterfazIndicadoresEstrategicasControler extends Funciones implements Initializable {
	@FXML private VBox vBoxAccionesGrupo;
	

	    @FXML
	    private HBox hBoxSubContenedorFinanciera;

	    @FXML
	    private HBox hBoxSubContenedorCliente;

	    @FXML
	    private HBox hBoxSubContenedorProcesos;

	    @FXML
	    private HBox hBoxSubContenedorAprendizaje;
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//mostrarObjetivoEstrategicoYAcciones();
		mostrarObjetivoEstrategicoYAcciones_v2();
	
	}

	public void mostrarObjetivoEstrategicoYAcciones_v2(){
		//vBoxAccionesGrupo.getChildren().clear();
		
		for(int i=1;i<=4;i++){

			System.out.println("estoy aqui 1");
				HBox auxiliar= null;
				if(i==4){
					auxiliar=hBoxSubContenedorFinanciera;
				}else
				if(i==1){
					auxiliar=hBoxSubContenedorCliente;
				}else if(i==2){
					auxiliar=hBoxSubContenedorProcesos;			
				}else if(i==3){
					auxiliar=hBoxSubContenedorAprendizaje;
				}
				auxiliar.getChildren().clear();
				
				//variable tabla
				
				//System.out.println ("pON FIN ENTRE Xd");
				WidgetsObjetivosAccionesAnchorPane_Controler tablaObjetivo=new WidgetsObjetivosAccionesAnchorPane_Controler();
				tablaObjetivo.setPerspectiva(i);
				tablaObjetivo.setOpcionSeleccionada(WidgetsObjetivosAccionesAnchorPane_Controler.OPCION_OBJETIVO);
				tablaObjetivo.getJfxButtonNuevo().setVisible(false);
				tablaObjetivo.getTablecolumn().setPrefWidth(tablaObjetivo.getTablecolumn().getWidth()+tablaObjetivo.getTableColumnOpciones().getWidth());;
				tablaObjetivo.getTableColumnOpciones().setVisible(false);
				tablaObjetivo.getTableColumnOpciones().setPrefWidth(0);
				tablaObjetivo.getLabelTitulo().setText("Objetivos Estrategicos");
				tablaObjetivo.getBorderPaneEncabezado().setStyle("  -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(i)+";");
				auxiliar.getChildren().add(tablaObjetivo);
			
				
				
				
				WidgetsObjetivosAccionesAnchorPane_Controler tablaAcciones=new WidgetsObjetivosAccionesAnchorPane_Controler();
				
				tablaAcciones.getJfxButtonNuevo().setVisible(false);
				tablaAcciones.getTablecolumn().setPrefWidth(tablaObjetivo.getTablecolumn().getWidth()+tablaObjetivo.getTableColumnOpciones().getWidth());
				tablaAcciones.getLabelTitulo().setText("Acciones Estrategicas");
				tablaAcciones.getTableColumnOpciones().setVisible(false);
				tablaAcciones.getTableColumnOpciones().setPrefWidth(0);	
				tablaObjetivo.setWidgetsAcciones(tablaAcciones);
				tablaAcciones.getBorderPaneEncabezado().setStyle("  -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(i)+";");
				auxiliar.getChildren().add(tablaAcciones);
				
				
				
				//IndicadoresContenidoWidgetsAnchorPaneControler indicador=new IndicadoresContenidoWidgetsAnchorPaneControler();
				HBox hboxContenedorIndicador=new HBox();
				hboxContenedorIndicador.setPrefWidth(376);
				
				//tablaAcciones.setWidgetsIndicador(hboxContenedorIndicador);
				tablaAcciones.setContenedorIndicador(hboxContenedorIndicador);
				tablaAcciones.setPerspectiva(i);
				auxiliar.getChildren().add(hboxContenedorIndicador);
				
				

			}


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void mostrarObjetivoEstrategicoYAcciones() {
		vBoxAccionesGrupo.getChildren().clear();
		Connection conn = null;
		PreparedStatement pstObjetivosEstrategicos = null;
		ResultSet rsObjetivosEstrategicos = null;
		
		PreparedStatement pstAccionesEstrategicas = null;
		ResultSet rsAccionesEstrategicas= null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pstObjetivosEstrategicos = conn.prepareStatement("  SELECT ID, NOMBRE, PERSPECTIVA FROM OBJETIVOS ORDER BY PERSPECTIVA ;");
			rsObjetivosEstrategicos = pstObjetivosEstrategicos.executeQuery();
			int countObjetivo=1; 

			System.out.println("estoy aqui 1");
			while (rsObjetivosEstrategicos.next()) {
				HboxAccionesControler fila=new HboxAccionesControler();
				fila.getChildren().clear();
				
				//Objetivo estrategoico
				ObjetivoEstrategicosLateralWidgetsAnchorPaneControler obEstrategico=new ObjetivoEstrategicosLateralWidgetsAnchorPaneControler();
				
				obEstrategico.getLabelObjetivoEstrategico().setText(rsObjetivosEstrategicos.getString("NOMBRE"));
				//obEstrategico.getLabelNombrePerspectiva().setText(ObjetivosEstrategicosWidgetsAnchorPaneControler.nombrePerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")));
				obEstrategico.getLabelNombrePerspectiva().setText("Objetivo E. I. n° "+countObjetivo);

				obEstrategico.getAnchorPaneNombrePerspectiva().setStyle("    -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA"))+";");

				
				
				
				
				
				
				int id_objetivo=rsObjetivosEstrategicos.getInt("ID");
				
				fila.getChildren().add(obEstrategico);
				
				
				//acciones rellenado 
				
				pstAccionesEstrategicas = conn.prepareStatement("SELECT ID, ID_OBJETIVOS, NOMBRE FROM ACCIONES WHERE ID_OBJETIVOS=?;");
				pstAccionesEstrategicas.setInt(1, rsObjetivosEstrategicos.getInt("ID"));
				
				rsAccionesEstrategicas = pstAccionesEstrategicas.executeQuery();
				int countAccion=1;
				while(rsAccionesEstrategicas.next()){
					AccionesContenidoWidgetsAnchorPaneControler accion=new AccionesContenidoWidgetsAnchorPaneControler();
					//creadno el ojbeto xd
					
					int id_accion=rsAccionesEstrategicas.getInt("ID");
					accion.getjFXButtonEditar().setVisible((Sesion.ROL==1)?true:false);
					accion.getjFXButtonEliminar().setVisible((Sesion.ROL==1)?true:false);
					accion.getLabelContenidoAccion().setText(rsAccionesEstrategicas.getString("NOMBRE"));
					accion.getAnchorPaneTituloAcciones().setStyle(" -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA"))+";");
					accion.getLabelTituloPerspectiva().setText("Acción Estrategica "+countAccion);
					accion.getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							JFXButton hyperLinkCambiarContrasena=(JFXButton)event.getSource();
							
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
								mvc.setId_accion(id_accion);

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
								
								
								
								
								mostrarObjetivoEstrategicoYAcciones();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							stackPane.setVisible(false);

						}
					});
					
					accion.getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
							alert.showAndWait();

							if (alert.getResult() == ButtonType.YES) {
								eliminarAccion(id_accion);
								mostrarObjetivoEstrategicoYAcciones();
							}
							
						}
					});
					
					//cerrabnd el objeto xD
					fila.getChildren().add(accion);
					countAccion++;
					
				}
				pstAccionesEstrategicas.close();
				rsAccionesEstrategicas.close();
				
				
				AccionesAnadirWidgetsAnchorPaneControler accionesAnadir=new AccionesAnadirWidgetsAnchorPaneControler();
				accionesAnadir.setVisible((Sesion.ROL==1)?true:false);
				accionesAnadir.getAnchorPaneTituloPerspectiva().setStyle( "-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA"))+";");
				accionesAnadir.getFontAwwesomeIconPlus_Circle().setStyle("-fx-fill: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA"))+";");
				accionesAnadir.getjFXButtonAnadir().setOnAction(new EventHandler<ActionEvent>() {
					
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
							mvc.setId_objetivo(id_objetivo);
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							Stage stage=new Stage();
							stage.setScene(scene);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.initStyle(StageStyle.TRANSPARENT);
							
							
							
							stage.showAndWait();;
							
							
							
							
							mostrarObjetivoEstrategicoYAcciones();
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						stackPane.setVisible(false);
					}
				});
				fila.getChildren().add(accionesAnadir);
				
				vBoxAccionesGrupo.getChildren().add(fila);		
				countObjetivo++;

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (rsObjetivosEstrategicos != null) {
					rsObjetivosEstrategicos.close();

				}
				if (pstObjetivosEstrategicos != null) {
					pstObjetivosEstrategicos.close();

				}
				
				
				if (rsAccionesEstrategicas != null) {
					rsAccionesEstrategicas.close();

				}
				if (pstAccionesEstrategicas != null) {
					pstAccionesEstrategicas.close();

				}
				
				
				if (conn != null) {
					conn.close();
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
	



}
