package balancedScorecard.mision_vision;

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
import java.util.Collections;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;

import funciones.Conexion;
import funciones.Funciones;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;




public class Mision_Vision_controler extends Funciones implements Initializable {
@FXML private JFXTabPane	jFXTabPaneOpciones;
	public  static int TAP_MISION=1;
	public  static int TAP_VISION=2;
	private int tapOpcion=1;
	

    public int getTapOpcion() {
		return tapOpcion;
	}


	public void setTapOpcion(int tapOpcion) {
		this.tapOpcion = tapOpcion;
		
		if(tapOpcion==1){
			jFXTabPaneOpciones.getSelectionModel().select(tabMision);
		}else{
			jFXTabPaneOpciones.getSelectionModel().select(tabVsion);
		}
	}




	@FXML
    private Tab tabVsion;

    @FXML
    private JFXTextArea jFXTextAreaVision;

    @FXML
    private Button buttonCancelarVision;

    @FXML
    private Button buttonGuardarVision;

    @FXML
    private Label labelInformacionVision;

    @FXML
    private Tab tabMision;

    @FXML
    private JFXTextArea jFXTextAreaMision;

    @FXML
    private Button buttonCancelarMision;

    @FXML
    private Button buttonGuardarMision;

    @FXML
    private Label labelInformacionMision;

    @FXML
    private Button buttonModificar;

    @FXML
    private JFXButton buttonClose;

	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		mostrarVision();
		mostrarMision();
		restricciones();
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				JFXButton button=(JFXButton)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
	
		
		buttonCancelarVision.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		buttonCancelarMision.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
		buttonGuardarMision.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(verificarCamposVacios_Mision()){
					insertarDatos_Mision();

				}
				}
		});
		buttonGuardarVision.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(verificarCamposVacios_Vision()){
					insertarDatos_Vision();

				}
				}
		});
		
	
		
		
	}
	
	
	public void restricciones(){
		jFXTextAreaMision.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = jFXTextAreaMision.getStyleClass();
		        if (jFXTextAreaMision.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});
		
		jFXTextAreaVision.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = jFXTextAreaVision.getStyleClass();
		        if (jFXTextAreaVision.getText().trim().length()==0) {
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

	
	public boolean verificarCamposVacios_Mision(){
		
		
		ObservableList<String> styleClass = jFXTextAreaMision.getStyleClass();

		
	
		if(jFXTextAreaMision.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXTextAreaMision.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		return true;
	}
	
	public boolean verificarCamposVacios_Vision(){
		
		
		ObservableList<String> styleClass = jFXTextAreaVision.getStyleClass();

		
	
		if(jFXTextAreaVision.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXTextAreaVision.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		return true;
	}


	
	public void insertarDatos_Mision(){
		ObservableList<String> styleClass = labelInformacionMision.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("UPDATE MISION SET MISION=? WHERE ID=1;"); 
               
               
               pst.setString(1, jFXTextAreaMision.getText().trim());
               int rs = pst.executeUpdate();
               if(rs==1){
            	   labelInformacionMision.setText("Inserción Correcta");;
            	   
            	  
               }else {
            	   labelInformacionMision.setText("Error :(...");;

            	   
            	   
               } 
               conn.close();
               pst.close();
               if (! styleClass.contains("labelInformacioncorrecto")) {
	                styleClass.add("labelInformacioncorrecto");
	              
	            }
               
               
               cerrarVentana();
           }   
           catch(SQLException e){
        	   
        	   
        	   if (! styleClass.contains("labelInformacionincorrecto")) {
                   styleClass.add("labelInformacionincorrecto");
                 
               }
        	   labelInformacionMision.setText("Error :(..."+e.getMessage());;
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
	

	public void insertarDatos_Vision(){
		ObservableList<String> styleClass = labelInformacionVision.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("UPDATE VISION SET VISION=? WHERE ID=1;"); 
               
               
               pst.setString(1, jFXTextAreaVision.getText().trim());
               int rs = pst.executeUpdate();
               if(rs==1){
            	   labelInformacionVision.setText("Inserción Correcta");;
            	   
            	  
               }else {
            	   labelInformacionVision.setText("Error :(...");;

            	   
            	   
               } 
               conn.close();
               pst.close();
               if (! styleClass.contains("labelInformacioncorrecto")) {
	                styleClass.add("labelInformacioncorrecto");
	              
	            }
               
               
               cerrarVentana();
           }   
           catch(SQLException e){
        	   
        	   
        	   if (! styleClass.contains("labelInformacionincorrecto")) {
                   styleClass.add("labelInformacionincorrecto");
                 
               }
        	   labelInformacionMision.setText("Error :(..."+e.getMessage());;
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
			jFXTextAreaMision.setText(rs.getString("MISION"));
						

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
			jFXTextAreaVision.setText(rs.getString("VISION"));
						

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

	

	
	private void cerrarVentana(){
		
		Stage stage=(Stage)jFXTabPaneOpciones.getScene().getWindow();
		stage.close();
	}

  

}