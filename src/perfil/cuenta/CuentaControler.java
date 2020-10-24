package perfil.cuenta;

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

import com.sun.prism.impl.BufferUtil;

import funciones.Conexion;
import funciones.Funciones;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sesion.Sesion;



public class CuentaControler extends Funciones implements Initializable {

      
    
    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldDNI;

    @FXML
    private TextField textFieldApellMaterno;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private Hyperlink hyperlinkCambiarContrasena;

    @FXML
    private TextField textFieldCargo;

    @FXML
    private Label labelMostrarInformacion;

    @FXML
    private TextField textFieldApellPaterno;

    @FXML
    private Button buttonModificar;

    @FXML
    private CheckBox checkBoxEstado;

    @FXML
    private Hyperlink hyperlinkCambiarFoto;

    @FXML
    private TextField textFieldCreadoPor;
    
    
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//cargar Campos
		textFieldDNI.setText(Sesion.DNI);
		textFieldNombre.setText(Sesion.NOMBRES);
		textFieldApellPaterno.setText(Sesion.APELL_PATERNO);
		textFieldApellMaterno.setText(Sesion.APELL_MATERNO);
		textFieldCargo.setText(Sesion.CARGO);
		imageViewFoto.setImage(Sesion.FOTO);
		checkBoxEstado.setSelected((Sesion.ESTADO==1)? true:false);
		textFieldCreadoPor.setText(Sesion.CREADO_POR);
		
		//ejecutar eventos
		hyperlinkCambiarFoto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Image image=seleccionarImage();
				if(image!=null){
					imageViewFoto.setImage(image);
				}
				
			}
		});
		
		hyperlinkCambiarContrasena.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Stage stagess=(Stage)hyperlinkCambiarContrasena.getScene().getWindow();
				AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				stackPane.setVisible(true);
				System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));
				
				
				
				
				
				
				//Hyperlink hyperLinkCambiarContrasena=(Hyperlink)event.getSource();
			
				//Stage stage=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
				//StackPane stackPane=(StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(1);
				//stackPane.setVisible(true);
				//System.out.println(stage.getScene().getRoot().getChildrenUnmodifiable().get(1));
				
						
				String urlFxml="/perfil/cuenta/cambiarContrasena/CambiarContrasena.fxml";
				String css="/perfil/cuenta/cambiarContrasena/cambiarContrasena.css";
				try {
					mostrarInterfazModalShowAndWait(urlFxml, css);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				stackPane.setVisible(false);
				
				
			}
		});
		
		buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				labelMostrarInformacion.setText(modificarDatos());;
			}
		});
		
		
		
		

	
	}
	
	
	public String modificarDatos(){
		
			String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("UPDATE USUARIOS SET NOMBRES=?, APELL_PATERNO=?, APELL_MATERNO=?, FOTO=?, CARGO=?, MODIFICADO_POR=?, ESTADO=? WHERE DNI=?"); 
               pst.setString(1, textFieldNombre.getText().trim());
               pst.setString(2, textFieldApellPaterno.getText().trim());
               pst.setString(3, textFieldApellMaterno.getText().trim());
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

                   pst.setBlob(4, bais);
               } else {
                   pst.setBlob(4, (Blob) null);
               }
               
               pst.setString(5, textFieldCargo.getText().trim());
               
               
               pst.setString(6, Sesion.DNI);
               pst.setInt(7, (checkBoxEstado.isSelected())?1:0);
               pst.setString(8, textFieldDNI.getText().trim());
               
               int rs = pst.executeUpdate();
               if(rs==1){
            	   mensaje ="Se ejecuto correctamente la correción y/o actualización de datos..";
            	  
               }else {
            	   mensaje= "Fallo la correción y/o actualización de datos... :(";
            	   
               }
               
               
               conn.close();
               pst.close();
               
               
               
               
           }   
		  catch(IOException e){
			  mensaje=e.getMessage();
			  e.printStackTrace();
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