package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import balancedScorecard.acciones.Acciones_controler;
import balancedScorecard.indicadores.Indicadores_controler;
import balancedScorecard.mision_vision.Mision_Vision_controler;
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

public class PrincipalInterfazIndicadoresEstrategicasControler2 extends Funciones implements Initializable {
   @FXML
    private VBox vBoxPrcipalObjetivoAccionIndicador;;
    


	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mostrarObjetivoEstrategico_Acciones_Indicadores();
	}
	


	
	
	public void mostrarObjetivoEstrategico_Acciones_Indicadores() {
		vBoxPrcipalObjetivoAccionIndicador.getChildren().clear();
		Connection conn = null;
		PreparedStatement pstObjetivosEstrategicos = null;
		ResultSet rsObjetivosEstrategicos = null;
		
		PreparedStatement pstAccionesEstrategicas = null;
		ResultSet rsAccionesEstrategicas= null;
		
		PreparedStatement pstIndicadores = null;
		ResultSet rsIndicadores= null;
		
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pstObjetivosEstrategicos = conn.prepareStatement("  SELECT ID, NOMBRE, PERSPECTIVA FROM OBJETIVOS;");
			rsObjetivosEstrategicos = pstObjetivosEstrategicos.executeQuery();

			System.out.println("estoy aqui 1");
			int countObjetivo=1; 
			while (rsObjetivosEstrategicos.next()) {
			
				HBoxObjetivoAccionesIndicadoresControler hboxOAI=new HBoxObjetivoAccionesIndicadoresControler();
				hboxOAI.getChildren().clear();
				ObjetivoEstrategicosLateralWidgetsAnchorPaneControler obEstrategico=new ObjetivoEstrategicosLateralWidgetsAnchorPaneControler();
				obEstrategico.setPrefHeight(160);
				
				obEstrategico.getLabelObjetivoEstrategico().setText(rsObjetivosEstrategicos.getString("NOMBRE"));
				//obEstrategico.getLabelNombrePerspectiva().setText(ObjetivosEstrategicosWidgetsAnchorPaneControler.nombrePerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")));
				obEstrategico.getLabelNombrePerspectiva().setText("Objetivo E. I. n° "+countObjetivo);

				obEstrategico.getAnchorPaneNombrePerspectiva().setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA"))+";");


				hboxOAI.getChildren().add(obEstrategico);
				
				pstAccionesEstrategicas = conn.prepareStatement("SELECT ID, ID_OBJETIVOS, NOMBRE FROM ACCIONES WHERE ID_OBJETIVOS=?;");
				pstAccionesEstrategicas.setInt(1, rsObjetivosEstrategicos.getInt("ID"));
				
				rsAccionesEstrategicas = pstAccionesEstrategicas.executeQuery();
				
				HBoxAccionesIndicadoresControler hboxAccionesIndicadores=new HBoxAccionesIndicadoresControler();
				hboxAccionesIndicadores.getChildren().clear();
				
				VBoxAccionesControler vBoxAcciones=new VBoxAccionesControler();
				vBoxAcciones.getChildren().clear();
				VBoxIndicadoresControler vBoxIndicadores=new VBoxIndicadoresControler();
				vBoxIndicadores.getChildren().clear();
				System.out.println(rsAccionesEstrategicas.getFetchSize());
				while(rsAccionesEstrategicas.next()){
					
				
					int id_accion =rsAccionesEstrategicas.getInt("ID");
					AccionesContenidoWidgetsAnchorPaneControler accion=new AccionesContenidoWidgetsAnchorPaneControler();
					
					accion.getLabelContenidoAccion().setText(rsAccionesEstrategicas.getString("NOMBRE"));
					//acciones-acciones
						accion.getAnchorPaneTituloAcciones().setStyle( "-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")) +";");
					
						
						accion.getjFXButtonEditar().setVisible((Sesion.ROL==1)?true:false);
						accion.getjFXButtonEliminar().setVisible((Sesion.ROL==1)?true:false);

					//
					vBoxAcciones.getChildren().add(accion);
					
					pstIndicadores = conn.prepareStatement("SELECT ID, NOMBRE, VALOR_ACTUAL, FORMULA, META_ROJO_INICIAL, META_ROJO_FINAL,"+
					"META_AMARILLO_INICIAL, META_AMARILLO_FINAL, META_VERDE_INICIAL,"
					+"META_VERDE_FINAL, RESPONSABLE FROM INDICADOR WHERE ID=?;");
					pstIndicadores.setInt(1, rsAccionesEstrategicas.getInt("ID"));
					
				
					
					rsIndicadores = pstIndicadores.executeQuery();
				
					
					if(rsIndicadores.next()){
						
							//anadir indicador
							IndicadoresContenidoWidgetsAnchorPaneControler aux=new IndicadoresContenidoWidgetsAnchorPaneControler();
							int id_indicador=rsIndicadores.getInt("ID");
							aux.getLabelIndicadorIndicador().setText(rsIndicadores.getString("NOMBRE"));
							aux.getAnchorPanetituloIndicador().setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")) +";");
							aux.getLabelFormulaContendido().setText(rsIndicadores.getString("FORMULA"));
							aux.getLabelResponsablesContendido().setText(rsIndicadores.getString("RESPONSABLE"));
							//aux.getLabelFormulaContendido().setText("");
							aux.getLabelValorActualContenido().setText(rsIndicadores.getString("VALOR_ACTUAL"));

							if(rsIndicadores.getDouble("META_VERDE_FINAL")>=rsIndicadores.getDouble("META_ROJO_INICIAL")){
								aux.getLabelPeligroContendido().setText(rsIndicadores.getString("META_ROJO_INICIAL")+"<=f(x)<="+rsIndicadores.getString("META_ROJO_FINAL"));
								aux.getLabelPrecaucionContendido().setText(rsIndicadores.getString("META_ROJO_FINAL")+"<f(x)<="+rsIndicadores.getString("META_AMARILLO_FINAL"));
								aux.getLabelMetaContendido().setText(rsIndicadores.getString("META_AMARILLO_FINAL")+"<f(x)<="+rsIndicadores.getString("META_VERDE_FINAL"));
								//aux.getLabelMetaContendido().setText(rsIndicadores.getString("META_VERDE_FINAL"));
							}else {
								aux.getLabelPeligroContendido().setText(rsIndicadores.getString("META_ROJO_INICIAL")+">=f(x)>="+rsIndicadores.getString("META_ROJO_FINAL"));
								aux.getLabelPrecaucionContendido().setText(rsIndicadores.getString("META_ROJO_FINAL")+">f(x)>="+rsIndicadores.getString("META_AMARILLO_FINAL"));
								aux.getLabelMetaContendido().setText(rsIndicadores.getString("META_AMARILLO_FINAL")+">f(x)>="+rsIndicadores.getString("META_VERDE_FINAL"));
							}
							
							aux.getjFXButtonEditar().setVisible((Sesion.ROL==1)?true:false);
							aux.getjFXButtonEliminar().setVisible((Sesion.ROL==1)?true:false);
							aux.getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
									alert.showAndWait();

									if (alert.getResult() == ButtonType.YES) {
										eliminarIndicador(id_indicador);
										mostrarObjetivoEstrategico_Acciones_Indicadores();
									}
								}
							});
							aux.getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent arg0) {
									
									Stage stagess=(Stage)aux.getjFXButtonEditar().getScene().getWindow();
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

									    mvc.setId_accion_estrategica(id_accion);
										Parent parent= fXMLLoader.getRoot();
										Scene scene=new Scene(parent);
										scene.setFill(new Color(0,0,0,0));
										scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
										Stage stage=new Stage();
										stage.setScene(scene);
										stage.initModality(Modality.APPLICATION_MODAL);
										stage.initStyle(StageStyle.TRANSPARENT);
										
										
										
										stage.showAndWait();;
										
										mostrarObjetivoEstrategico_Acciones_Indicadores();
										
										
									
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									stackPane.setVisible(false);
									
									
								}
							});
							//reducir indicador
							vBoxIndicadores.getChildren().add(aux);

						
						

					}else{
					
						IndicadoresAnadirWidgetsAnchorPaneControler indicadores=new IndicadoresAnadirWidgetsAnchorPaneControler();
						indicadores.getAnchorPaneTituloIndicador().setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")) +";");
						indicadores.setVisible((Sesion.ROL==1)?true:false);
						indicadores.getFontAwesomePlus_circle().setStyle(" -fx-fill: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")) +";"
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
									
								    mvc.setId_accion_estrategica(id_accion);
									Parent parent= fXMLLoader.getRoot();
									Scene scene=new Scene(parent);
									scene.setFill(new Color(0,0,0,0));
									scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
									Stage stage=new Stage();
									stage.setScene(scene);
									stage.initModality(Modality.APPLICATION_MODAL);
									stage.initStyle(StageStyle.TRANSPARENT);
									
									
									
									stage.showAndWait();;
									
									mostrarObjetivoEstrategico_Acciones_Indicadores();
									
									
								
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								stackPane.setVisible(false);

							}
						});
						
						
						vBoxIndicadores.getChildren().add(indicadores);
					}
					pstIndicadores.close();
					rsIndicadores.close();
					
					
					
				}
				
				hboxAccionesIndicadores.getChildren().add(vBoxAcciones);
				hboxAccionesIndicadores.getChildren().add(vBoxIndicadores);
				System.out.println("El taamñao del de vBoxAcciones es "+vBoxAcciones.getChildren().size());

				System.out.println("El taamñao del de vBoxIndicadores es "+vBoxIndicadores.getChildren().size());

				
				pstAccionesEstrategicas.close();
				rsAccionesEstrategicas.close();
			
				hboxOAI.getChildren().add(hboxAccionesIndicadores);
				
				vBoxPrcipalObjetivoAccionIndicador.getChildren().add(hboxOAI);
				
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
	



}
