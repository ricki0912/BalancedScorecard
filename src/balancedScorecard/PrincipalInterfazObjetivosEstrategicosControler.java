package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import balancedScorecard.mision_vision.Mision_Vision_controler;
import balancedScorecard.objetivos.Objetivos_controler;
import funciones.Conexion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sesion.Sesion;

public class PrincipalInterfazObjetivosEstrategicosControler implements Initializable{

    @FXML
    private HBox hBox_clientes;

    @FXML
    private HBox hBox_ProcesosInternos;

    @FXML
    private HBox hBox_Aprendizaje_Conocimiento;

    @FXML
    private HBox hBox_finaciero;

    @FXML
    private JFXButton jFXButton_agregar;
	
    @FXML private VBox vBoxContenerdorPrincipalPerspectivas;
	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mostrarObjetivos();
		
		
		
		//añadir eventos al VBox vBoxContenerdorPrincipalPerspectivas
		vBoxContenerdorPrincipalPerspectivas.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				vBoxContenerdorPrincipalPerspectivas.setCursor(Cursor.HAND);
				System.out.println("mouseReleased "+ event.getSceneX());
				System.out.println("mouseReleased "+ event.getX());
			}
		});
		vBoxContenerdorPrincipalPerspectivas.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				vBoxContenerdorPrincipalPerspectivas.setCursor(Cursor.CROSSHAIR);
				System.out.println("mousePresed "+ event.getSceneX());
				System.out.println("mousePresed "+ event.getX());
				
			}
		});
		
		vBoxContenerdorPrincipalPerspectivas.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				vBoxContenerdorPrincipalPerspectivas.setCursor(Cursor.CROSSHAIR);
				System.out.println("mouseDragged "+ event.getSceneX());
				System.out.println("mouseDragged "+ event.getX());				
			}
		});
		vBoxContenerdorPrincipalPerspectivas.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				vBoxContenerdorPrincipalPerspectivas.setCursor(Cursor.HAND);
				System.out.println("mouseEntered "+ event.getSceneX());
				System.out.println("mouseEntered "+ event.getX());					
			}
		});
		vBoxContenerdorPrincipalPerspectivas.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				vBoxContenerdorPrincipalPerspectivas.setCursor(Cursor.DEFAULT);
				System.out.println("mouseExited "+ event.getSceneX());
				System.out.println("mouseExited "+ event.getX());	
			}
		});
		
		
		
		//termina los eventos de VBox vBoxContenerdorPrincipalPerspectivas
		jFXButton_agregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				JFXButton hyperLinkCambiarContrasena=(JFXButton)event.getSource();
				
				Stage stagess=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
				AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				stackPane.setVisible(true);

				String urlFxml = "/balancedScorecard/objetivos/Objetivos_Interfaz.fxml";
				String css = "/balancedScorecard/objetivos/objetivos_Interfaz.css";
				try {
					

					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					
					Objetivos_controler mvc=fXMLLoader.getController();
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage=new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.initStyle(StageStyle.TRANSPARENT);
					
					
					
					stage.showAndWait();;
					
					
					
					
				mostrarObjetivos();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stackPane.setVisible(false);
				
			}
		});
		
		privilegios();
	}
	

	public void limpiarHBox(){
		hBox_clientes.getChildren().clear();
		hBox_ProcesosInternos.getChildren().clear();
		hBox_Aprendizaje_Conocimiento.getChildren().clear();
		hBox_finaciero.getChildren().clear();

	}
	public void mostrarObjetivos() {
		limpiarHBox();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			
			//pst = conn.prepareStatement("  SELECT ID, NOMBRE, PERSPECTIVA FROM OBJETIVOS");
			
			/*pst=conn.prepareStatement("SELECT "
					+ "objetivos.ID, objetivos.NOMBRE, objetivos.PERSPECTIVA, "
					+ "SUM((SELECT  IF(META_LOGRADA.META_ROJO_INICIAL<META_LOGRADA.META_VERDE_FINAL, IF( MAX(META_LOGRADA.META_LOGRADA)<=META_LOGRADA.META_ROJO_FINAL,1,0),IF(MIN(META_LOGRADA.META_LOGRADA)>=META_LOGRADA.META_ROJO_FINAL,1,0))  FROM META_LOGRADA WHERE meta_lograda.ID_INDICADOR=ACCIONES.ID)) AS ROJO, "
					+ "SUM((SELECT  IF(META_LOGRADA.META_ROJO_INICIAL<META_LOGRADA.META_VERDE_FINAL, IF( META_LOGRADA.META_ROJO_FINAL<MAX(META_LOGRADA.META_LOGRADA) AND MAX(META_LOGRADA.META_LOGRADA)<=META_LOGRADA.META_AMARILLO_FINAL,1,0),IF(META_LOGRADA.META_ROJO_FINAL>MIN(META_LOGRADA.META_LOGRADA) AND MIN(META_LOGRADA.META_LOGRADA)>=META_LOGRADA.META_AMARILLO_FINAL,1,0))  FROM META_LOGRADA WHERE meta_lograda.ID_INDICADOR=ACCIONES.ID)) AS AMARILLO, "
					+ "SUM((SELECT  IF(META_LOGRADA.META_ROJO_INICIAL<META_LOGRADA.META_VERDE_FINAL, IF( META_LOGRADA.META_AMARILLO_FINAL<MAX(META_LOGRADA.META_LOGRADA) AND MAX(META_LOGRADA.META_LOGRADA)<=META_LOGRADA.META_VERDE_FINAL,1,0),IF(META_LOGRADA.META_AMARILLO_FINAL>MIN(META_LOGRADA.META_LOGRADA) AND MIN(META_LOGRADA.META_LOGRADA)>=META_LOGRADA.META_VERDE_FINAL,1,0))  FROM META_LOGRADA WHERE meta_lograda.ID_INDICADOR=ACCIONES.ID)) AS VERDE, "
					+ "SUM((SELECT  IF(META_LOGRADA.META_ROJO_INICIAL<META_LOGRADA.META_VERDE_FINAL, IF( MAX(META_LOGRADA.META_LOGRADA)>META_LOGRADA.META_VERDE_FINAL,1,0),IF(MIN(META_LOGRADA.META_LOGRADA)<META_LOGRADA.META_VERDE_FINAL,1,0)) FROM META_LOGRADA WHERE meta_lograda.ID_INDICADOR=ACCIONES.ID)) AS AZUL, "
					+ " COUNT(ACCIONES.ID_OBJETIVOS) AS TOTAL_ACCIONES "
					
					+ "FROM objetivos LEFT JOIN ACCIONES ON objetivos.ID=ACCIONES.ID_OBJETIVOS GROUP BY OBJETIVOS.ID; ");
					
					*/
			
			pst=conn.prepareStatement("CALL objetivos_estrategicos();");
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				
				
				
				ObjetivosEstrategicosWidgetsAnchorPaneControler objetivos= new ObjetivosEstrategicosWidgetsAnchorPaneControler();
				
				objetivos.setOnMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						//objetivos.setCursor(Cursor.MOVE);
						//System.out.println(arg0.getX());
					}
				});
				
				
				objetivos.getLabelObjetivoEstrategico().setText(rs.getString("NOMBRE"));
				int id_objetivoo=rs.getInt("ID");
				objetivos.getLabelPeligro().setText((rs.getInt("TOTAL_ACCIONES")-rs.getInt("AMARILLO")-rs.getInt("VERDE")-rs.getInt("AZUL"))+"");
				objetivos.getLabelPrecaucion().setText(rs.getInt("AMARILLO")+"");
				objetivos.getLabelMeta().setText(rs.getInt("VERDE")+"");
				objetivos.getLabelMetaSuperada().setText(rs.getInt("AZUL")+"");
				objetivos.getLabelAccionesNoEjecutadas().setText(rs.getInt("TOTAL_ACCIONES")+"");
				
				
				if(Sesion.ROL!=1){
					objetivos.getjFXButtonEditar().setVisible(false);
				}
				objetivos.getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						JFXButton hyperLinkCambiarContrasena=(JFXButton)event.getSource();
						
						Stage stagess=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
						AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
						stackPane.setVisible(true);
						
						
						String urlFxml = "/balancedScorecard/objetivos/Objetivos_Interfaz.fxml";
						String css = "/balancedScorecard/objetivos/objetivos_Interfaz.css";
						try {
							

							System.out.println("Hola estoy dento de, metodo de modal :D");
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							
							Objetivos_controler mvc=fXMLLoader.getController();
							
							mvc.setId_objetivo(id_objetivoo);
							mvc.setOpcionEvento(Objetivos_controler.ACTUALIZAR);
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							Stage stage=new Stage();
							stage.setScene(scene);
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.initStyle(StageStyle.TRANSPARENT);
							
							
							
							stage.showAndWait();;
							
							
							
							
						mostrarObjetivos();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						stackPane.setVisible(false);
						
					}
				});
				
				//privilegios
				if(Sesion.ROL!=1){
					objetivos.getjFXButtonEliminar().setVisible(false);
				}
				
				//
				objetivos.getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
						alert.showAndWait();

						if (alert.getResult() == ButtonType.YES) {
							eliminarObjetivo(id_objetivoo);
							mostrarObjetivos();
						}
						
						
					}
				});
				
				
						if(rs.getInt("PERSPECTIVA")==1){
							objetivos.getLabelPerspectiva().setText(ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_CLIENTE);
							objetivos.getAnchorPanePerspectiva().setStyle("    -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.COLOR_PERPECTIVA_CLIENTE+";");
							hBox_clientes.getChildren().add(objetivos);
						}
						if(rs.getInt("PERSPECTIVA")==2){
							objetivos.getLabelPerspectiva().setText(ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_PROCESOS_INTERNOS);
							objetivos.getAnchorPanePerspectiva().setStyle("    -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.COLOR_PERPECTIVA_PROCESOS_INTERNOS+";");
							hBox_ProcesosInternos.getChildren().add(objetivos);
						}
						if(rs.getInt("PERSPECTIVA")==3){
							objetivos.getLabelPerspectiva().setText(ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_APRENDIZAJE);
							objetivos.getAnchorPanePerspectiva().setStyle("    -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.COLOR_PERPECTIVA_APRENDIZAJE+";");
							hBox_Aprendizaje_Conocimiento.getChildren().add(objetivos);
						}
						
						if(rs.getInt("PERSPECTIVA")==4){
							objetivos.getLabelPerspectiva().setText(ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_FINACIERA);
							objetivos.getAnchorPanePerspectiva().setStyle("    -fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.COLOR_PERPECTIVA_FINACIERA+";");
							hBox_finaciero.getChildren().add(objetivos);
						}
						

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
	
	

	public void eliminarObjetivo(int id_objetivo){
		
      
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement(" delete from OBJETIVOS where id = ?;"); 
               pst.setInt(1, id_objetivo);
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
	
	public void privilegios(){
		if(Sesion.ROL!=1){
			jFXButton_agregar.setVisible(false);
		}
		
		
	}
	

	

}
