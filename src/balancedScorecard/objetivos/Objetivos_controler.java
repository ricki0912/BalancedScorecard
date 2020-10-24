package balancedScorecard.objetivos;

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




public class Objetivos_controler extends Funciones implements Initializable {

	private int id_objetivo=-1;
	public int getId_objetivo() {
		return id_objetivo;
	}


	public void setId_objetivo(int id_objetivo) {
		this.id_objetivo = id_objetivo;
	}


	public  static int INSERTAR=1;
	public static int ACTUALIZAR=2;
	private int opcionEvento=1;
	
    public int getOpcionEvento() {
		return opcionEvento;
	}


	public void setOpcionEvento(int opcionEvento) {
		this.opcionEvento = opcionEvento;
		if(opcionEvento==2){
			mostrarObjetivo();

		}
	}


	@FXML
    private JFXTextArea jFXTextAreaObjetivo;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Label labelInformacion;

    @FXML
    private JFXComboBox<Perpectiva> jFXCombox_perspectiva;

    @FXML
    private JFXButton buttonClose;
	
    
private ObservableList<Perpectiva> arrayPerpectiva=FXCollections.observableArrayList();

	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mostrarPerspectiva();
		
		
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
					if(opcionEvento==1){
						insertarDatos();
					}else{
						actualizarDatos();
					}

				}
				}
		});
	
		
		
	}
	
	
	public void restricciones(){
		jFXTextAreaObjetivo.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = jFXTextAreaObjetivo.getStyleClass();
		        if (jFXTextAreaObjetivo.getText().trim().length()==0) {
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
		
		
		ObservableList<String> styleClass = jFXCombox_perspectiva.getStyleClass();

		
	
		if(jFXCombox_perspectiva.getSelectionModel().getSelectedItem()==null){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXCombox_perspectiva.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		if(jFXTextAreaObjetivo.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			jFXTextAreaObjetivo.requestFocus();
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
               pst = conn.prepareStatement("INSERT INTO OBJETIVOS( NOMBRE, PERSPECTIVA) VALUES(?,?);"); 
               
               
               pst.setString(1, jFXTextAreaObjetivo.getText().trim());
               pst.setInt(2, jFXCombox_perspectiva.getSelectionModel().getSelectedItem().getId());
            
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
               pst = conn.prepareStatement("UPDATE OBJETIVOS SET NOMBRE=?, PERSPECTIVA=? WHERE ID=?;"); 
               pst.setString(1, jFXTextAreaObjetivo.getText().trim());
               pst.setInt(2, jFXCombox_perspectiva.getSelectionModel().getSelectedItem().getId());
               pst.setInt(3, id_objetivo);
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
	

	



	public void mostrarObjetivo() {
	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(" SELECT NOMBRE,PERSPECTIVA FROM OBJETIVOS WHERE ID=?");
			pst.setInt(1, id_objetivo);
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
			jFXTextAreaObjetivo.setText(rs.getString("NOMBRE"));
			jFXCombox_perspectiva.getSelectionModel().select(new Perpectiva(rs.getInt("PERSPECTIVA")));
						

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

	
	public void cerrarVentana(){
		Stage stage=(Stage)jFXTextAreaObjetivo.getScene().getWindow();
		stage.close();
	}
	
	
	public void mostrarPerspectiva(){
		arrayPerpectiva.add(new Perpectiva(4, ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_FINACIERA));
		arrayPerpectiva.add(new Perpectiva(1,ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_CLIENTE ));
		arrayPerpectiva.add(new Perpectiva(2, ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_PROCESOS_INTERNOS));
		arrayPerpectiva.add(new Perpectiva(3, ObjetivosEstrategicosWidgetsAnchorPaneControler.NOMBRE_PERPECTIVA_APRENDIZAJE));
		jFXCombox_perspectiva.setItems(arrayPerpectiva);
	}



	


  

}