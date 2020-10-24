package balancedScorecard.semaforo_modal;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;

import balancedScorecard.CuadroEstadisticoWidgetsAnchorPaneControler;
import ds.desktop.notify.DesktopNotify;
import funciones.Conexion;
import funciones.Funciones;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;




public class Semaforo_modal extends Funciones implements Initializable {
	
	
	
	@FXML private JFXButton buttonClose;
	@FXML private Label labelPagination;
	@FXML private JFXButton jFXButtonAtras;
	@FXML private JFXButton jFXButtonDelante;
	private int posicion=-1;
	
	public int getPositionDelante(){
		if(posicion>=idIndicadores.size()-1){
			mostrarCuadrosEstadisticos();
			posicion=-1;
		}
			posicion++;
		return posicion;
	}
	public int getPositionAtras(){
		if(posicion<=0){
			mostrarCuadrosEstadisticos();
			posicion=idIndicadores.size();
		}
		posicion--;
		return posicion;
	}
	
	@FXML private AnchorPane anchorPanecontenedorSemaforo;
	private ArrayList<Integer> idIndicadores=new ArrayList<Integer>();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mostrarCuadrosEstadisticos();
		getPositionDelante();
		
      //  DesktopNotify.showDesktopMessage("Mensaje de Advertencia", "Este es un mensaje de advertencia. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.WARNING);

		//inicio de hilo 
		/*
		ScheduledExecutorService executor = Executors.
			    newSingleThreadScheduledExecutor();
			 
			ScheduledFuture future = executor.scheduleWithFixedDelay(
			    new Runnable() {
			 
			    @Override
			    public void run() {
			    	getPositionDelante();
					mostrarIndicador();			    }
			}, 10, 30, TimeUnit.SECONDS);
			 
			try {
				future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}*/

        //new Thread(sleeper).start();;
              
		
		
		mostrarIndicador();
		jFXButtonAtras.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getPositionAtras();
				mostrarIndicador();
			}
		});
		
		jFXButtonDelante.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getPositionDelante();
				mostrarIndicador();
				
			}
		});
		
		
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Stage stage=(Stage)buttonClose.getScene().getWindow();
				stage.close();
				
			}
		});
		
		
	}

	
	public void mostrarIndicador(){
		anchorPanecontenedorSemaforo.getChildren().clear();
		
		///
		//logica
	SemaforoWidgets_modalControler cuadro=new SemaforoWidgets_modalControler();
	final int id=idIndicadores.get(posicion);
		
	cuadro.setId_indicador(id);
	cuadro.mostrarDatos();
	labelPagination.setText((posicion+1)+"/"+(idIndicadores.size()));
	anchorPanecontenedorSemaforo.getChildren().add(cuadro);
	}
	
	
	public void mostrarCuadrosEstadisticos(){
		idIndicadores.clear();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try{
			Conexion.conectarDB();
			conn = Conexion.getConexion();

			
			
			pst = conn.prepareStatement("SELECT ID_INDICADOR FROM META_LOGRADA GROUP BY ID_INDICADOR;");
					
					rs = pst.executeQuery();
			
					
					int cont=1;
			while(rs.next()){
				
				
				idIndicadores.add(rs.getInt("ID_INDICADOR"));
				
			
				//Thread.sleep(5000);
				
				
				
				
					
				/*	Thread hilo =new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							
						}
					}, rs.getInt("ID_INDICADOR")+"");
					hilo.start();
				
				 */					
					
					
					///

				
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