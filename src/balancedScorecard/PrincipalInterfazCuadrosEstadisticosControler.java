package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import balancedScorecard.acciones.Acciones_controler;
import balancedScorecard.semaforo_modal.Semaforo_modal;
import funciones.Conexion;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PrincipalInterfazCuadrosEstadisticosControler implements Initializable {
    @FXML
    private VBox vBoxCuadrosEstadisticos;
    
    @FXML private JFXButton jFXButtonSlideshow;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		jFXButtonSlideshow.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

		        
			      Stage stagess=(Stage)jFXButtonSlideshow.getScene().getWindow();
			      AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				  stackPane.setVisible(true);
					
					
					
					
					String urlFxml = "/balancedScorecard/semaforo_modal/Semaforo.fxml";
					String css = "/balancedScorecard/semaforo_modal/semaforo_modal.css";
					try {
						
						//System.out.println("estoy dentro del panel xD");
						

						//System.out.println("Hola estoy dento de, metodo de modal :D");
						FXMLLoader fXMLLoader=new FXMLLoader();
						fXMLLoader.setLocation(getClass().getResource(urlFxml));
						fXMLLoader.load();
						
						Semaforo_modal mvc=fXMLLoader.getController();
						

						
						
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
		

		mostrarCuadrosEstadisticos();

	}

	public void mostrarCuadrosEstadisticos(){
		
		
		
		
		vBoxCuadrosEstadisticos.getChildren().clear();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try{
			Conexion.conectarDB();
			conn = Conexion.getConexion();

			
			
			pst = conn.prepareStatement("SELECT ID_INDICADOR FROM META_LOGRADA GROUP BY ID_INDICADOR;");
					
					rs = pst.executeQuery();
			
					HBox hboxHorizontal=new HBox();
					hboxHorizontal.setPrefHeight(339);
					hboxHorizontal.setPrefWidth(773);
					hboxHorizontal.setSpacing(30);
					int cont=1;
			while(rs.next()){
					
					
					///
					//logica
				CuadroEstadisticoWidgetsAnchorPaneControler cuadro=new CuadroEstadisticoWidgetsAnchorPaneControler();
				final int id=rs.getInt("ID_INDICADOR");
					
				cuadro.setId_indicador(id);
				cuadro.mostrarDatos();
					
				/*	Thread hilo =new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							
						}
					}, rs.getInt("ID_INDICADOR")+"");
					hilo.start();
				
				 */					
					
					
					///
					
					hboxHorizontal.getChildren().add(cuadro);
					
					
					
					
					
					
					
				if(cont==3){
					vBoxCuadrosEstadisticos.getChildren().add(hboxHorizontal);
					hboxHorizontal=new HBox();
					hboxHorizontal.setPrefHeight(339);
					hboxHorizontal.setPrefWidth(773);
					hboxHorizontal.setSpacing(30);
					cont=1;
				}else{
					cont++;
				}
				
				
				
			}
			if(cont==2 || cont==3){
				vBoxCuadrosEstadisticos.getChildren().add(hboxHorizontal);

			}
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();

				}
				if (pst!= null) {
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

}