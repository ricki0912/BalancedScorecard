package balancedScorecard.acciones;

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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;

import balancedScorecard.ObjetivosEstrategicosWidgetsAnchorPaneControler;
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




public class Acciones_controler extends Funciones implements Initializable {
	private int opcionSeleccionada=2;
	public static int OPCION_ACTUALIZAR=1;
	public static int OPCION_INSERTAR=2;
	public void setEvento(int opcion){
		opcionSeleccionada=opcion;
		if(opcion==OPCION_ACTUALIZAR){
			mostrarAcccion();
		}
	}
	

	
    @FXML
    private JFXTextArea jFXTextAreaAccion;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Label labelInformacion;

    @FXML
    private JFXButton buttonClose;
	
	
	private int id_objetivo=-1;
	public int getId_objetivo() {
		return id_objetivo;
	}


	public void setId_objetivo(int id_objetivo) {
		this.id_objetivo = id_objetivo;
	}
	
	private int id_accion=-1;


	public int getId_accion() {
		return id_accion;
	}


	public void setId_accion(int id_accion) {
		this.id_accion = id_accion;
	}

	public  static int INSERTAR=1;
	public static int ACTUALIZAR=2;

	






	public void cerrarVentana(){
		Stage stage=(Stage)jFXTextAreaAccion.getScene().getWindow();
		stage.close();
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		
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
					if(opcionSeleccionada==2){
						insertarDatos();
					}else{
						actualizarDatos();
					}

				}
				}
		});
	
		
		
	}
	
	
	public void restricciones(){
		jFXTextAreaAccion.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = jFXTextAreaAccion.getStyleClass();
		        if (jFXTextAreaAccion.getText().trim().length()==0) {
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
		
		
		ObservableList<String> styleClass = jFXTextAreaAccion.getStyleClass();

		
	
	
		
		
		if(jFXTextAreaAccion.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXTextAreaAccion.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

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
               pst = conn.prepareStatement("INSERT INTO ACCIONES (ID_OBJETIVOS, NOMBRE) VALUES(?,?);"); 
               
               pst.setInt(1,id_objetivo );
               pst.setString(2, jFXTextAreaAccion.getText().trim());
            
            
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
               
               
				cerrarVentana();

           }   
           catch(SQLException e){
        	   
        	   
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
               pst = conn.prepareStatement("UPDATE ACCIONES SET NOMBRE=?, ID_OBJETIVOS=? WHERE ID=?;"); 
               pst.setString(1, jFXTextAreaAccion.getText().trim());
               pst.setInt(2,id_objetivo);
               pst.setInt(3, id_accion);
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
               
               cerrarVentana();
               
           }   
           catch(SQLException e){
        	   
        	   
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
	

	



	public void mostrarAcccion() {
	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID_OBJETIVOS, NOMBRE FROM ACCIONES WHERE ID=?;");
			pst.setInt(1, id_accion);
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
			jFXTextAreaAccion.setText(rs.getString("NOMBRE"));
			id_objetivo=rs.getInt("ID_OBJETIVOS");
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

	
	




	


  

}