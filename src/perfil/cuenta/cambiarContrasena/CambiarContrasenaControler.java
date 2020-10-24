package perfil.cuenta.cambiarContrasena;


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

import funciones.Conexion;
import funciones.Funciones;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sesion.Sesion;





public class CambiarContrasenaControler extends Funciones  implements Initializable {

	@FXML
    private BorderPane borderPaneNuevoPersonal;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelInformacion;

    @FXML
    private Button buttonGuardar;

    @FXML
    private PasswordField passwordFieldContrasenaActual;

    @FXML
    private PasswordField passwordFieldNuevaContrasena;

    @FXML
    private PasswordField passwordFieldRepetirNuevaContrasena;


    
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		buttonGuardar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(passwordFieldContrasenaActual.getText().trim().isEmpty() || passwordFieldNuevaContrasena.getText().trim().isEmpty()||passwordFieldRepetirNuevaContrasena.getText().trim().isEmpty()){
					labelInformacion.setText("Por favor rellene los campos requeridos");
					
				}else {
					
					if(Sesion.CONTRASENA.equals(encriptar(passwordFieldContrasenaActual.getText()))){
						if(passwordFieldNuevaContrasena.getText().equals(passwordFieldRepetirNuevaContrasena.getText())){
							labelInformacion.setText(actualizarContrasena(encriptar(passwordFieldNuevaContrasena.getText())));
							Sesion.CONTRASENA=encriptar(passwordFieldNuevaContrasena.getText());
						}else{
							labelInformacion.setText(" La confirmación de la contraseña no coincide");
						}
					}else{
						labelInformacion.setText("La contraseña actual es incorrecta");
					}
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
		
	buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
		
	
	}
	
	
	
	
public String actualizarContrasena(String nuevaContrasenaEncriptada){
		
		String mensaje=null;
	  Connection conn=null;
	  PreparedStatement pst=null;
	  try{     
		
		  
          Conexion.conectarDB(); 
		  conn=Conexion.getConexion();
           pst = conn.prepareStatement("UPDATE USUARIOS SET CONTRASENNA=? WHERE DNI=?"); 
           pst.setString(1,nuevaContrasenaEncriptada);
          
           pst.setString(2, Sesion.DNI);
           
           int rs = pst.executeUpdate();
           if(rs==1){
        	   mensaje ="Se ejecuto correctamente la correción y/o actualización de datos..";
        	  
           }else {
        	   mensaje= "Fallo la correción y/o actualización de datos... :(";
        	   
           }
           
           
           conn.close();
           pst.close();
           
           
           
           
       }   
       catch(SQLException e){
    	   mensaje=e.getMessage();
    	   System.out.println(e.getMessage());
           e.printStackTrace();
      
       }  finally{
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