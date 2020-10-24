package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sesion.Sesion;

public class PrincipalInterfazMisionVisionControler extends Funciones implements Initializable {
	 @FXML
	    private Label labelMostrarVision;

	    @FXML
	    private JFXButton jFXButtonEditarVision;

	    @FXML
	    private Label labelMostrarMision;

	    @FXML
	    private JFXButton jFXButtonEditarMision;
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		mostrarMision();
		mostrarVision();
		jFXButtonEditarMision.setOnAction(new  EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
		
				JFXButton hyperLinkCambiarContrasena=(JFXButton)event.getSource();
				
				Stage stagess=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
				AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				stackPane.setVisible(true);
				System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));
			
				
				String urlFxml = "/balancedScorecard/mision_vision/Mision_Vision_Interfaz.fxml";
				String css = "/balancedScorecard/mision_vision/mision_Vision_Interfaz.css";
				try {
					
					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					Mision_Vision_controler mvc=fXMLLoader.getController();
					mvc.setTapOpcion(Mision_Vision_controler.TAP_MISION);
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage=new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.initStyle(StageStyle.TRANSPARENT);
					
					
					
					stage.showAndWait();;
					stackPane.setVisible(false);

					
					
					
					mostrarMision();
					mostrarVision();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
		
		jFXButtonEditarVision.setOnAction(new  EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
		
				JFXButton hyperLinkCambiarContrasena=(JFXButton)event.getSource();
				
				Stage stagess=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
				AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				stackPane.setVisible(true);
				System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));
				
				
				String urlFxml = "/balancedScorecard/mision_vision/Mision_Vision_Interfaz.fxml";
				String css = "/balancedScorecard/mision_vision/mision_Vision_Interfaz.css";
				try {
					

					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					
					Mision_Vision_controler mvc=fXMLLoader.getController();
					mvc.setTapOpcion(Mision_Vision_controler.TAP_VISION);
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage=new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.initStyle(StageStyle.TRANSPARENT);
					
					
					
					stage.showAndWait();;
					stackPane.setVisible(false);

					
					
					
					mostrarMision();
					mostrarVision();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
		privilegios();

		
	   
	}
	
	


	public void mostrarMision() {
	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("  SELECT MISION FROM MISION WHERE ID=1");
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
			labelMostrarMision.setText(rs.getString("MISION"));
						

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

	


	public void mostrarVision() {
	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("  SELECT VISION FROM VISION WHERE ID=1");
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				labelMostrarVision.setText(rs.getString("VISION"));
						

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
	
	public void privilegios(){
		if(Sesion.ROL==1){
			jFXButtonEditarMision.setVisible(true);
			jFXButtonEditarVision.setVisible(true);
		}else{
			jFXButtonEditarVision.setVisible(false);
			jFXButtonEditarMision.setVisible(false);
		}
	}

}
