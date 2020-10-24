package personal.verPersonal;


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

import funciones.Conexion;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import personal.verPersonal.restablecerContrasena.RestablecerContrasenaControler;
import sesion.Sesion;

public class VerPersonalControler implements Initializable {
	private ToggleGroup togleGrouppersonal=new ToggleGroup();

	@FXML
    private TextField textFieldNombre;

    @FXML
    private Hyperlink hyperlinkRestablecerContrasena;

    @FXML
    private Label labelInformacion;

    @FXML
    private TextField textFieldApellPaterno;

    @FXML
    private Button ButtonEliminar;

    @FXML
    private TextField textFieldDNI;

    @FXML
    private TextField textFieldBuscar;

    @FXML
    private TextField textFieldApellMaterno;


    @FXML
    private Button ButtonBuscar;

    @FXML
    private TextField textFieldCargo;

    @FXML
    private Hyperlink hyperlinkSubirFoto;

    @FXML
    private CheckBox checkBoxEstado;

    @FXML
    private Button ButtonModificar;
    
    @FXML
    private ToggleButton togleButtonPersonalFotoNombre;

    @FXML
    private TextField textFieldCreadoPor;
    
    @FXML 
    private VBox vBoxButonesFoto;
	
    @FXML 
    private ImageView imageViewFoto;
    @FXML private TextField textFieldModificadoPor;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mostrarPersonalBotonesFotos();
		textFieldBuscar.setOnKeyReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				TextField textFieldBuscar=(TextField)arg0.getSource();
				String textoBuscar=textFieldBuscar.getText();
				mostrarPersonalBotonesFotos(textoBuscar);
			}
		});
		
		hyperlinkRestablecerContrasena.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Stage stagess=(Stage)hyperlinkRestablecerContrasena.getScene().getWindow();
				AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				stackPane.setVisible(true);
				System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));
				
				
				
				
				
				//Hyperlink hyperLinkCambiarContrasena=(Hyperlink)event.getSource();
							
				
				//Stage stagePr=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
				//StackPane stackPane=(StackPane) stagePr.getScene().getRoot().getChildrenUnmodifiable().get(1);
				//stackPane.setVisible(true);
				//System.out.println(stagePr.getScene().getRoot().getChildrenUnmodifiable().get(1));
				
						
				String urlFxml="/personal/verPersonal/restablecerContrasena/RestablecerContrasena.fxml";
				String css="/personal/verPersonal/restablecerContrasena/restablecerContrasena.css";
				try {
					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					Parent parent= fXMLLoader.getRoot();
					RestablecerContrasenaControler rc=fXMLLoader.getController();
					rc.getTextFieldDniUsuario().setText(textFieldDNI.getText());
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage=new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.showAndWait();
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				stackPane.setVisible(false);
				
				
				
				
				
			}
		});
		
		ButtonModificar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				labelInformacion.setText(modificarDatos());
				
			}
		});
		
		ButtonEliminar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminarr :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					labelInformacion.setText(eliminarDatos());
					mostrarPersonalBotonesFotos();
					inicializarCamposVacios();
				}
				
				
			}
		});
	}
		
	
	
	public void mostrarDatosPersonaSeleccionada(String dni){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT DNI,NOMBRES, APELL_PATERNO, APELL_MATERNO, FOTO, CARGO,  ESTADO, CREADO_POR, MODIFICADO_POR FROM USUARIOS WHERE DNI=?;");
			pst.setString(1, dni);
			rs=pst.executeQuery();
			while(rs.next()){
				textFieldDNI.setText(rs.getString("DNI"));
				textFieldNombre.setText(rs.getString("NOMBRES"));
				textFieldApellPaterno.setText(rs.getString("APELL_PATERNO"));
				textFieldApellMaterno.setText(rs.getString("APELL_MATERNO"));
				textFieldCargo.setText(rs.getString("CARGO"));
				textFieldCreadoPor.setText(rs.getString("CREADO_POR"));
				textFieldModificadoPor.setText(rs.getString("MODIFICADO_POR"));
				checkBoxEstado.setSelected((rs.getInt("ESTADO")==1)?true : false);
				Blob imageBlob=rs.getBlob("FOTO");
            	if(imageBlob!=null){
            		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
            		imageViewFoto.setImage(new Image(byteArrayInputStream));
               	}else {
               		imageViewFoto.setImage(new Image("/personal/verPersonal/anadirPersonal/foto0.jpg"));
            	}
			}
			
		}catch(Exception e){
			
			
		}		
	}
	
	//metodo para mostrar todos los usuarios
	public void mostrarPersonalBotonesFotos(){
		  mostrarPersonalBotonesFotos("");
	}
	
	
	
	//metodo para realizar las busquedas 
	public void mostrarPersonalBotonesFotos(String buscar){
		  //vamos limpiar el contenedor de los botones
		  vBoxButonesFoto.getChildren().clear();  
		  //hacer la conexcioon con la base de datos y obtener alas busquedas encontradas
          Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
            Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT DNI, NOMBRES, FOTO FROM USUARIOS WHERE NOMBRES LIKE ? OR DNI LIKE ?");
              pst.setString(1, "%"+buscar+"%");
              pst.setString(2, "%"+buscar+"%");
              rs = pst.executeQuery();
             while(rs.next()){
          	   	ToggleButtonFoto tf=new ToggleButtonFoto();	
          	   	
         		tf.setDni(rs.getString("DNI"));
          	   	tf.setText(rs.getString("NOMBRES"));
          	   	tf.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							ToggleButtonFoto fbfoto=(ToggleButtonFoto)arg0.getSource();
							if(!fbfoto.isSelected()){
								fbfoto.setSelected(true);							
							}
							mostrarDatosPersonaSeleccionada(fbfoto.getDni());	
						}
					});
          	   	
          	   	Blob imageBlob=rs.getBlob("FOTO");
          	   	
              	if(imageBlob!=null){
              		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
              		tf.getCircleFoto().setFill(new ImagePattern(new Image(byteArrayInputStream)));
                 	}else {
                 		tf.getCircleFoto().setFill(new ImagePattern(new Image("/personal/verPersonal/anadirPersonal/foto0.jpg")));
                 	}
              	togleGrouppersonal.getToggles().add(tf);
         		vBoxButonesFoto.getChildren().add(tf);          		
             }
             
             
         }   
         catch(Exception e){
        
             e.printStackTrace();
        
         }  finally{
      	   try {
      		   if(rs!=null){
      			   rs.close();
				
      	   		}
      		   if(pst!=null){
      			   pst.close();
				
      	   		}if(conn!=null){
      	   			conn.close();
      	   		}
      	   
      	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
      	   
         }
	}

	public String modificarDatos(){
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto"));  
        
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
        	   if (! styleClass.contains("labelInformacioncorrecto")) {
	                styleClass.add("labelInformacioncorrecto");
	              
	            }
        	   mensaje ="Se ejecuto correctamente la correción y/o actualización de datos..";
        	  
           }else {
        	   mensaje= "Fallo la correción y/o actualización de datos... :(";
        	   
           }
           
           
           conn.close();
           pst.close();
           
           
           
           
       }   
	  catch(IOException e){
		  if (! styleClass.contains("labelInformacionincorrecto")) {
              styleClass.add("labelInformacionincorrecto");
            
          }
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

public String eliminarDatos(){
		
		String mensaje=null;
	  Connection conn=null;
	  PreparedStatement pst=null;
	  try{     
		
		  
          Conexion.conectarDB(); 
		  conn=Conexion.getConexion();
           pst = conn.prepareStatement("DELETE FROM USUARIOS WHERE DNI=?"); 
           pst.setString(1, textFieldDNI.getText().trim());
          
           
           int rs = pst.executeUpdate();
           if(rs==1){
        	   mensaje ="Se ejecuto correctamente la correción y/o actualización de datos..";
        	  
           }else {
        	   mensaje= "Fallo la correción y/o actualización de datos... :(";
        	   
           }
           
           
           conn.close();
           pst.close();
           
           
           
           
       }catch(SQLException e){
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
	
public void inicializarCamposVacios(){
	textFieldDNI.setText("");
	textFieldNombre.setText("");
	textFieldApellPaterno.setText("");
	textFieldApellMaterno.setText("");
	textFieldCargo.setText("");
	textFieldCreadoPor.setText("");
	textFieldModificadoPor.setText("");
	checkBoxEstado.setSelected(false);
	imageViewFoto.setImage(null);
}

  

}