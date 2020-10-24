package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.security.sasl.AuthorizeCallback;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.qoppa.d.ab;

import balancedScorecard.acciones.Acciones_controler;
import balancedScorecard.indicadores.Indicadores_controler;
import balancedScorecard.mision_vision.Mision_Vision_controler;
import funciones.ColoresPersonalizados;
import funciones.Conexion;
import funciones.Funciones;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sesion.Sesion;

public class PrincipalInterfazIMetasMensualesSubInternoControler extends Funciones implements Initializable {

	private JFXDatePicker jFXDatePickerFechaMeta=new JFXDatePicker();
	
	
    public JFXDatePicker getjFXDatePickerFechaMeta() {
		return jFXDatePickerFechaMeta;
	}



	public void setjFXDatePickerFechaMeta(JFXDatePicker jFXDatePickerFechaMeta) {
		this.jFXDatePickerFechaMeta = jFXDatePickerFechaMeta;
	}

	@FXML
    private VBox vBoxPrcipalMetas;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		mostrarMetasBaseDeDatos();
		jFXDatePickerFechaMeta.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {


				LocalDate date = jFXDatePickerFechaMeta.getValue();
		         System.err.println("Selected date: " + date);
				
				
			}
		});
	}
	


	public void mostrarMetas(){
		vBoxPrcipalMetas.getChildren().clear();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try{
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("  SELECT ID, NOMBRE, PERSPECTIVA FROM OBJETIVOS;");
			rs = pst.executeQuery();
			while(rs.next()){
				HBoxAccionesIndicadoresControler hboxAccionesIndicadores=new HBoxAccionesIndicadoresControler();
				hboxAccionesIndicadores.getChildren().clear();
				LogrosMesnsualesMetasWidgetsAnchorPaneControler logros=new LogrosMesnsualesMetasWidgetsAnchorPaneControler();
				MetasAnadirWidgetsAnchorPaneControler anadirMetas=new MetasAnadirWidgetsAnchorPaneControler();

				hboxAccionesIndicadores.getChildren().add(anadirMetas);
				
				
				vBoxPrcipalMetas.getChildren().add(hboxAccionesIndicadores);
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
	
	public void mostrarMetasBaseDeDatos(){
		String codigoSqlFechaWhrere=" ";
		mostrarMetasBaseDeDatos(codigoSqlFechaWhrere);
	}
	

	public void mostrarMetasBaseDeDatos(LocalDate date){
		java.util.Date date2 =java.util.Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date2.getTime());
		System.out.println(sqlDate);
		String codigoSqlFechaWhrere=" AND FECHA='"+sqlDate+"'";
		mostrarMetasBaseDeDatos(codigoSqlFechaWhrere);
	}
	
	
	public void mostrarMetasBaseDeDatos(String datee) {
		
		vBoxPrcipalMetas.getChildren().clear();
		Connection conn = null;
		PreparedStatement pstObjetivosEstrategicos = null;
		ResultSet rsObjetivosEstrategicos = null;
		
		PreparedStatement pstAccionesEstrategicas = null;
		ResultSet rsAccionesEstrategicas= null;
		
		PreparedStatement pstIndicadores = null;
		ResultSet rsIndicadores= null;
		
		PreparedStatement pstMetasLogradas=null;
		ResultSet rsMetasLogradas=null;
		
		
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pstObjetivosEstrategicos = conn.prepareStatement("  SELECT ID, NOMBRE, PERSPECTIVA FROM OBJETIVOS;");
			rsObjetivosEstrategicos = pstObjetivosEstrategicos.executeQuery();

			System.out.println("estoy aqui 1");
			
			while (rsObjetivosEstrategicos.next()) {
			
				HBoxObjetivoAccionesIndicadoresControler hboxOAI=new HBoxObjetivoAccionesIndicadoresControler();
				hboxOAI.getChildren().clear();
				
				
				pstAccionesEstrategicas = conn.prepareStatement("SELECT ID, ID_OBJETIVOS, NOMBRE FROM ACCIONES WHERE ID_OBJETIVOS=?;");
				pstAccionesEstrategicas.setInt(1, rsObjetivosEstrategicos.getInt("ID"));
				
				rsAccionesEstrategicas = pstAccionesEstrategicas.executeQuery();
				
				HBoxAccionesIndicadoresControler hboxAccionesIndicadores=new HBoxAccionesIndicadoresControler();
				hboxAccionesIndicadores.getChildren().clear();
				
				VBoxAccionesControler vBoxAcciones=new VBoxAccionesControler();
				
				
				vBoxAcciones.getChildren().clear();
		
				
				System.out.println(rsAccionesEstrategicas.getFetchSize());
				
				if(rsAccionesEstrategicas.next()){
					rsAccionesEstrategicas.beforeFirst();
					while(rsAccionesEstrategicas.next()){
						
						
						int id_accion =rsAccionesEstrategicas.getInt("ID");
						
						
					
						
						
						pstIndicadores = conn.prepareStatement("SELECT ID, NOMBRE, FORMULA, META_ROJO_INICIAL, META_ROJO_FINAL,"+
						"META_AMARILLO_INICIAL, META_AMARILLO_FINAL, META_VERDE_INICIAL,"
						+"META_VERDE_FINAL, RESPONSABLE FROM INDICADOR WHERE ID=? ;");
						pstIndicadores.setInt(1, rsAccionesEstrategicas.getInt("ID"));
						rsIndicadores = pstIndicadores.executeQuery();
					
						
						if(rsIndicadores.next()){
							
							
								int idIndicador=rsIndicadores.getInt("id");
								pstMetasLogradas=conn.prepareStatement("SELECT ID, META_ROJO_INICIAL, META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL, "
										+ "META_VERDE_INICIAL, META_VERDE_FINAL, META_LOGRADA, FECHA, ID_INDICADOR FROM META_LOGRADA WHERE ID_INDICADOR=?  "+datee+";");
								pstMetasLogradas.setInt(1, rsIndicadores.getInt("id"));
								
								rsMetasLogradas=pstMetasLogradas.executeQuery();
								HBox auxiliarMetas=new HBox();
								auxiliarMetas.setSpacing(3);
								if(rsMetasLogradas.next()){
									LogrosMesnsualesMetasWidgetsAnchorPaneControler logros=new LogrosMesnsualesMetasWidgetsAnchorPaneControler();
									//
									logros.getLabelTitulo().setText("Fecha: "+rsMetasLogradas.getDate("FECHA").toLocalDate());
									logros.getLabelLogroAlcanzado().setText(rsMetasLogradas.getDouble("META_LOGRADA")+"");
									logros.getAnchorPaneTituloFecha().setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")) +";");
									
									
									mostrarValorBarraProgreso(logros.getProgressBarLogroAlcanzado(), rsMetasLogradas.getDouble("META_ROJO_INICIAL"), rsMetasLogradas.getDouble("META_ROJO_FINAL"), 
											rsMetasLogradas.getDouble("META_AMARILLO_FINAL"), rsMetasLogradas.getDouble("META_VERDE_FINAL"), rsMetasLogradas.getDouble("META_LOGRADA"));
									//extraer datos y añadirlos al objeto xD
									
										
									auxiliarMetas.getChildren().add(logros);

									while(rsMetasLogradas.next()){
										LogrosMesnsualesMetasWidgetsAnchorPaneControler logros1=new LogrosMesnsualesMetasWidgetsAnchorPaneControler();
										
										logros1.getLabelTitulo().setText("Fecha: "+rsMetasLogradas.getDate("FECHA").toLocalDate());
										logros1.getLabelLogroAlcanzado().setText(rsMetasLogradas.getDouble("META_LOGRADA")+"");
										logros1.getAnchorPaneTituloFecha().setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rsObjetivosEstrategicos.getInt("PERSPECTIVA")) +";");
										
										
										mostrarValorBarraProgreso(logros1.getProgressBarLogroAlcanzado(), rsMetasLogradas.getDouble("META_ROJO_INICIAL"), rsMetasLogradas.getDouble("META_ROJO_FINAL"), 
												rsMetasLogradas.getDouble("META_AMARILLO_FINAL"), rsMetasLogradas.getDouble("META_VERDE_FINAL"), rsMetasLogradas.getDouble("META_LOGRADA"));
									//extraer datos y añadirlos al objeto xD
										
										auxiliarMetas.getChildren().add(logros1);

									}
								}else{
									MetasAnadirWidgetsAnchorPaneControler anadirLogro=new MetasAnadirWidgetsAnchorPaneControler();
									anadirLogro.setVisible((Sesion.ROL==2)?true:false);
									//se especifica lo que va hacer el softgware en añadir
									
									/// DE SER POCIBLE CREAR UNA CLASE PARA ESTO Xd
									anadirLogro.getjFXButtonAnadir().setOnAction(new EventHandler<ActionEvent>() {
										
										@Override
										public void handle(ActionEvent event) {
											
											
											if(jFXDatePickerFechaMeta.getValue()!=null){
												TextInputDialog dialog = new TextInputDialog("");
												dialog.setTitle("Logro Alcanzado");
												dialog.setHeaderText("");
												dialog.setContentText("Fecha "+jFXDatePickerFechaMeta.getValue());

												// Traditional way to get the response value.
												Optional<String> result = dialog.showAndWait();
												if (result.isPresent()){
													
												    System.out.println("Your name: " + result.get());
												    
												    
												    Connection conn=null;
													  PreparedStatement pst=null;
													  ResultSet rsset=null;
													  try{     
														
														  
											              Conexion.conectarDB(); 
														  conn=Conexion.getConexion();
											               pst = conn.prepareStatement("INSERT INTO META_LOGRADA(META_ROJO_INICIAL,VALOR_ACTUAL, META_ROJO_FINAL,META_AMARILLO_INICIAL, META_AMARILLO_FINAL,META_VERDE_INICIAL,"+
											            		   " META_VERDE_FINAL, META_LOGRADA, FECHA, ID_INDICADOR) SELECT META_ROJO_INICIAL,VALOR_ACTUAL, META_ROJO_FINAL,META_AMARILLO_INICIAL, META_AMARILLO_FINAL,META_VERDE_INICIAL,"+
											            		   " META_VERDE_FINAL, ?,?, ? FROM   INDICADOR WHERE ID=?;"); 
											               pst.setDouble(1,Double.valueOf(result.get() ));
											            
											               java.util.Date date =java.util.Date.from(jFXDatePickerFechaMeta.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
															java.sql.Date sqlDate = new java.sql.Date(date.getTime());
															pst.setDate(2,sqlDate);
															pst.setInt(3, idIndicador);
															pst.setInt(4, idIndicador);
															
											               
											               
											  
											            
											               
											               int rs = pst.executeUpdate();
											            
											               conn.close();
											               pst.close();
											              
											               mostrarMetasBaseDeDatos(jFXDatePickerFechaMeta.getValue());
											               
											               
											           }   
											           catch(Exception e){
											        	   
											        	   
											        	   Alert alert = new Alert(AlertType.ERROR);
											        	   alert.setTitle("Error");
											        	   alert.setHeaderText("Inserción");
											        	   alert.setContentText("Error"+e.getMessage());

											        	   alert.showAndWait();
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
											    
											}else{
												  Alert alert = new Alert(AlertType.ERROR);
									        	   alert.setTitle("Error");
									        	   alert.setHeaderText("Inserción");
									        	   alert.setContentText("Seleccione una fecha por favor!!!!");
									        	   alert.showAndWait();
											}
											
									       
											  
											 
										}
									});
									
									auxiliarMetas.getChildren().add(anadirLogro);
									
								}
								
								
								vBoxAcciones.getChildren().add(auxiliarMetas);///

							
							

						}else{
							AnchorPane amchorPaneVacio= new AnchorPane();
							amchorPaneVacio.setPrefHeight(160);
							vBoxAcciones.getChildren().add(amchorPaneVacio);
							
						}
					
						
						
						pstIndicadores.close();
						rsIndicadores.close();
						
						
						
					}
				}else{
					AnchorPane amchorPaneVacio= new AnchorPane();
					amchorPaneVacio.setPrefHeight(160);
					vBoxAcciones.getChildren().add(amchorPaneVacio);
				}
				
				hboxAccionesIndicadores.getChildren().add(vBoxAcciones);
		
				System.out.println("El taamñao del de vBoxAcciones es "+vBoxAcciones.getChildren().size());

		

				
				pstAccionesEstrategicas.close();
				rsAccionesEstrategicas.close();
			
				hboxOAI.getChildren().add(hboxAccionesIndicadores);
				
				vBoxPrcipalMetas.getChildren().add(hboxOAI);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (rsObjetivosEstrategicos != null) {
					rsObjetivosEstrategicos.close();

				}
				if (pstObjetivosEstrategicos != null) {
					pstObjetivosEstrategicos.close();

				}
				
				
				if (rsAccionesEstrategicas != null) {
					rsAccionesEstrategicas.close();

				}
				if (pstAccionesEstrategicas != null) {
					pstAccionesEstrategicas.close();

				}
				
				if (pstIndicadores != null) {
					pstIndicadores.close();

				}
				
				if(rsIndicadores!=null){
					rsIndicadores.close();
				}
				
				if(pstMetasLogradas!=null){
					pstMetasLogradas.close();
				}
				if(rsMetasLogradas!=null){
					rsMetasLogradas.close();
				}
				
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}
	
	
	
	public void mostrarValorBarraProgreso(ProgressBar pogressBar, Double rojo_incial,Double rojo_final,
			Double amarillo_final, Double verde_final, Double metaMensual){
			Double intervaloMetaVerde, intevaloProgreso;
		if(rojo_incial<verde_final){
			pogressBar.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			
			//reglas de tres simpeles convertir a por ciento
				if(rojo_incial<=metaMensual && metaMensual<=verde_final){
					intervaloMetaVerde=verde_final-rojo_incial;
					intevaloProgreso=metaMensual-rojo_incial;
					pogressBar.setProgress((intevaloProgreso/intervaloMetaVerde));
				}else if(verde_final<metaMensual){
					pogressBar.setProgress(1);
		
				}else if(metaMensual<rojo_incial){
					pogressBar.setProgress(0);
				}
			//
			if(metaMensual<=rojo_final){
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_PELIGRO+"; "
								+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_PELIGRO+";"
								+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_PELIGRO_FONDO+"; "
								+ "");
				
				
			}else if(rojo_final<metaMensual && metaMensual<=amarillo_final){
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_PRECAUCION+"; "
						+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_PRECAUCION+";"
						+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_PRECAUCION_FONDO+"; "
						+ "");
			}else if(amarillo_final<metaMensual && metaMensual<=verde_final){
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_META+"; "
						+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_META+";"
						+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_META_FONDO+"; "
						+ ""						);
			}else if(verde_final<metaMensual){
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_META_SUPERADA+"; "
						+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_META_SUPERADA+";"
						+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_META_SUPERADA_FONDO+"; "
						+ "");
				
			}
			
		}else{
			pogressBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			
			//intevalos
			if(rojo_incial>=metaMensual && metaMensual>=verde_final){
				intervaloMetaVerde=rojo_incial-verde_final;
				intevaloProgreso=rojo_incial-metaMensual;
				pogressBar.setProgress(intevaloProgreso/intervaloMetaVerde);
			}else if(verde_final>metaMensual){
				pogressBar.setProgress(1);
			}else if(rojo_incial<metaMensual){
				pogressBar.setProgress(0);
			}
			
			if(metaMensual>=rojo_final){
				//color rojo 
				
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_PELIGRO+"; "
						+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_PELIGRO+";"
						+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_PELIGRO_FONDO+"; "
						+ "");
			}else if(rojo_final>metaMensual && metaMensual>=amarillo_final){
				//rojo amariloo
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_PRECAUCION+"; "
						+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_PRECAUCION+";"
						+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_PRECAUCION_FONDO+"; "
						+ "");

			}else if(amarillo_final>metaMensual && metaMensual>=verde_final){
				//color verde
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_META+"; "
						+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_META+";"
						+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_META_FONDO+"; "
						+ "");
			}else if(verde_final>metaMensual){
				
				//color azul
				pogressBar.setStyle( " -fx-box-border: goldenrod;"
						+ " -fx-accent: "+ColoresPersonalizados.META_COLOR_META_SUPERADA+"; "
						+ "   -fx-text-box-border: "+ColoresPersonalizados.META_COLOR_META_SUPERADA+";"
						+ "  -fx-control-inner-background:"+ColoresPersonalizados.META_COLOR_META_SUPERADA_FONDO+"; "
						+ "");
				
			}
		}
	}
	

	



}
