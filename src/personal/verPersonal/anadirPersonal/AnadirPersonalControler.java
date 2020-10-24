package personal.verPersonal.anadirPersonal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXTextField;

import funciones.Conexion;
import funciones.Funciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import personal.Rol;
import sesion.Sesion;



public class AnadirPersonalControler extends Funciones implements Initializable {
	
    private final String LINK_CARPETA_FOTO="/personal/verPersonal/anadirPersonal/";
    
    
    //private String urlImagePerfil=null;
	
    @FXML
    private TextField textFieldNombre;

    @FXML 
    private ComboBox<Rol> comboBoxRol;
    @FXML
    private TextField textFieldDNI;

    @FXML
    private TextField textFieldApellMaterno;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelInformacion;

    @FXML
    private TextField textFieldCargo;

    @FXML
    private Button buttonGuardar;

    @FXML
    private TextField textFieldApellPaterno;
    
    @FXML
    private TextField textFieldContrasena;

    @FXML
    private Hyperlink HyperlinkSeleccionarFoto;
	
	private ObservableList<Rol> arrayComboBoxRol = FXCollections.observableArrayList();

    
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//textFieldDNI.add
		textFieldDNI.setOnKeyTyped(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
		        

		         if ( isNumeric(ke.getCharacter()) && textFieldDNI.getText().length()<8){
		              
		         }else{
		        	 ke.consume();
		         }
			
			}
		});
		//mostramos foto de perfila aleaoriamente
		imageViewFoto.setImage(new Image(LINK_CARPETA_FOTO+"foto"+((int) (Math.random() * 9) )+".jpg"));
		//
		mostrarRol();
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
		HyperlinkSeleccionarFoto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Image image=seleccionarImage();
				if(image!=null){
					imageViewFoto.setImage(image);
				}
				
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
				labelInformacion.setText(	insertarDatos());
			}
		});
		
	
		
		
	}
	

	public void  mostrarRol(){
		
		if(Sesion.ROL!=0){
			arrayComboBoxRol.add(new Rol(1, "Administrador"));
		}
		arrayComboBoxRol.add(new Rol(2, "Usuario"));
		comboBoxRol.getItems().addAll(arrayComboBoxRol);
		
	}
	
	public String insertarDatos(){
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("INSERT INTO USUARIOS( DNI, NOMBRES, APELL_PATERNO, APELL_MATERNO, FOTO, CARGO, CONTRASENNA,  CREADO_POR, MODIFICADO_POR, ROL)"
               												+ " VALUES (?,?,?,?,?,?,?,?,?,?)"); 
               pst.setString(1, textFieldDNI.getText().trim());
               pst.setString(2, textFieldNombre.getText().trim());
               pst.setString(3, textFieldApellPaterno.getText().trim());
               pst.setString(4, textFieldApellMaterno.getText().trim());
               if (imageViewFoto.getImage() != null) {
            	   BufferedImage image = SwingFXUtils.fromFXImage(imageViewFoto.getImage(), null);
            	   
            	   ByteArrayOutputStream baos = null;
            	   try {
            	       baos = new ByteArrayOutputStream();
            	       ImageIO.write(image, "jpg", baos);
            	   } finally {
            	       try {
            	           baos.close();
            	       } catch (Exception e) {
            	       }
            	   }
            	   
            	   ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                   pst.setBlob(5, bais);
               } else {
                   pst.setBlob(5, (Blob) null);
               }
               pst.setString(6, textFieldCargo.getText());
               
               pst.setString(7, encriptar(textFieldContrasena.getText()));
              
               pst.setString(8, Sesion.DNI);
               pst.setString(9, Sesion.DNI);
               pst.setInt(10,comboBoxRol.getSelectionModel().getSelectedItem().getId());
               
               int rs = pst.executeUpdate();
               if(rs==1){
            	   mensaje="El nuevo usuario fue creado exitosamente ";
            	   
            	  
               }else {
            	   mensaje="No se pudo crear el nuevo usuario... :(";
            	   
            	   
               }
               
               
               conn.close();
               pst.close();
               
               
               
               
           }   
           catch(SQLException e){
        	   mensaje=e.getMessage();
               e.printStackTrace();
          
           }
		  catch(IOException e){
			  mensaje=e.getMessage();
		  }finally{
        	   try {
        		   if(pst!=null){
        			   pst.close();
				
        	   		}if(conn!=null){
        	   			conn.close();
        	   		}
        	   
        	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
        	   
           }
		 
		
		
		return mensaje;
	}
	
	
	
	


	


  

}