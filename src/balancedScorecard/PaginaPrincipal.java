/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancedScorecard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Optional;

import cargando.Cargando;
import funciones.Conexion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.LoginControler;

/**
 *
 * @author danml
 */
public class PaginaPrincipal extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	
    	cargarPagina(stage);
    	
    	
    	
    	
    	
     
    }

    public void cargarPagina(Stage stage) throws IOException{
    	Conexion.conectarDB();

		
		
		
		if(Conexion.CONN==null){
			
			TextInputDialog texInputDialogIp=new TextInputDialog(LoginControler.leerFichero(LoginControler.stringFicheroIP));
			texInputDialogIp.setTitle("Congigurar IP Servidor");
			texInputDialogIp.setHeaderText("");
			texInputDialogIp.setContentText("Ingrese IP-SERVIDOR:");

			// Traditional way to get the response value.
			Optional<String> result = texInputDialogIp.showAndWait();
			if (result.isPresent()){
			   LoginControler.escribirFichero(LoginControler.stringFicheroIP, result.get());
			   Conexion.conectarDB();
			   cargarPagina(stage);
			   
			}
			 		
			
			
				
			
	//System.out.println(" errorrrr ");
			
			
			
		}else{
	
			Stage estageCarganado=Cargando.cargando(stage);
			estageCarganado.centerOnScreen();
			estageCarganado.show();
			
			Task<Void> task=new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					Parent root = FXMLLoader.load(getClass().getResource("InterfazPrincipalInterfaz.fxml"));
			        
			        Scene scene = new Scene(root);
			        //ProgressIndicator progreso=(ProgressIndicator)estageCarganado.getScene().getRoot().getChildrenUnmodifiable().get(0);
			        
			        Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							estageCarganado.close();
							stage.setScene(scene);
					        stage.setMaximized(true);
					        stage.show();



						}
					});
					
					return null;
				}
			};
			Thread hilo=new Thread(task);
			hilo.setDaemon(true);
			hilo.start();
			
			
			   
		}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
