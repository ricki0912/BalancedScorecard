
/*
 * 
 * Desarrollado por Ricardo Jesus Solis Almerco 
 * 
 * */




package balancedScorecard.semaforo_modal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.jfoenix.controls.JFXButton;

import balancedScorecard.ObjetivosEstrategicosWidgetsAnchorPaneControler;
import balancedScorecard.indicadores.Indicadores_controler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import funciones.Conexion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;

public class SemaforoWidgets_modalControler extends AnchorPane{

    @FXML
    private Label labelValorActualContenido;

    @FXML
    private Label labelPeligroContenido;

    @FXML
    private Label labelPrecaucionContenido;

    @FXML
    private Label labelValorActualReloj;

    @FXML
    private Label labelIzquierdoReloj;

    @FXML
    private Label labelderechoReloj;

    @FXML
    private Label labelObjetivo;
    
    @FXML private Label labelAccion;

	
	
	
	
	
	
		@FXML private Label labelLogroAlcanzado;
	
		int id_indicador; 
		
		@FXML
	    private LineChart<String, Number> lineChartMetas;

		private CategoryAxis xAxis = new CategoryAxis();
	    private NumberAxis yAxis = new NumberAxis();
		
	    @FXML
	    private Label labelFormulaContenido;

	    @FXML
	    private Label labelResponsablesContenido;

	    @FXML
	    private Label labelMetaContenido;

	    @FXML
	    private Arc ArcMetaAzul;

	    @FXML
	    private Arc ArcMetaVerde;

	    @FXML
	    private Arc ArcMetaPrecaucion;

	    @FXML
	    private Arc ArcMetaPeligro;

	    @FXML
	    private Arc ArcFlechaIndicador;

	    @FXML
	    private Label labelFechaContenido;

	    @FXML
	    private Label labelPerspectiva;
    
	    private XYChart.Series xYChartSeriesMetaEstablecidad = new XYChart.Series();
	    private XYChart.Series xYChartSeriesPeligro = new XYChart.Series();
	    private XYChart.Series xYChartSeriesPrecaucion = new XYChart.Series();
	    private XYChart.Series xYChartSeriesPeriodo = new XYChart.Series();
	    private XYChart.Series xYChartSeriesMetaLograda = new XYChart.Series();

	    
	
	
	
	



	public SemaforoWidgets_modalControler(){
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("SemaforoWidgets_modal.fxml"));
		//getStylesheets().add(getClass().getResource("togleButtonFoto.css").toExternalForm());
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println(e);
			
		}
    }
	
	
	
	
	public int getId_indicador() {
		return id_indicador;
	}




	public void setId_indicador(int id_indicador) {
		this.id_indicador = id_indicador;
	}




	public void mostrarDatos(){
		lineChartMetas.getData().addAll(xYChartSeriesPeligro, xYChartSeriesPrecaucion, xYChartSeriesMetaEstablecidad,xYChartSeriesMetaLograda, xYChartSeriesPeriodo );
		lineChartMetas.getXAxis().setLabel("Logros por Fecha");
		xYChartSeriesPeligro.setName("Peligro");
		xYChartSeriesPrecaucion.setName("Precaucion");
		xYChartSeriesMetaEstablecidad.setName(" Meta ");
		xYChartSeriesMetaLograda.setName("Logro Alcumulado");
		xYChartSeriesPeriodo.setName("Logro por Periodo");
		   
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try{
			//Conexion.conectarDB();
			//conn = Conexion.getConexion();
			Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(Conexion.URL_INICIAL+ Conexion.IP_SERVIDOR+"/"+ Conexion.BASE_DATOS+Conexion.UNICODE, Conexion.USER, Conexion.PASS);
			
			
			pst = conn.prepareStatement("SELECT ID, (SELECT NOMBRE FROM OBJETIVOS WHERE ID=(SELECT ID_OBJETIVOS FROM ACCIONES WHERE ID=ID_INDICADOR)) AS NOMBRE_OBJETIVO,(SELECT NOMBRE FROM INDICADOR WHERE ID=ID_INDICADOR)AS NOMBRE_INDICADOR, "+ 
" (SELECT RESPONSABLE FROM INDICADOR WHERE ID=ID_INDICADOR)AS RESPONSABLE_INDICADOR , "+
" (SELECT FORMULA FROM INDICADOR WHERE ID=ID_INDICADOR)AS FORMULA_INDICADOR , "+
" (SELECT NOMBRE FROM ACCIONES WHERE ID=ID_INDICADOR) AS NOMBRE_ACCIONES,TIPO_FORMULA, DATO_NUMERO_UNO, DATO_PORCENTAJE_UNO, DATO_PORCENTAJE_DOS, VAR_NUMERO_UNO, VAR_PORCENTAJE_UNO, VAR_PORCENTAJE_DOS,  "
+ " META_ROJO_INICIAL ,META_ROJO_FINAL, META_AMARILLO_INICIAL, META_AMARILLO_FINAL, "+ 
" META_VERDE_INICIAL, META_VERDE_FINAL, RETONAR_VALOR_ACUMULADO(?,(SELECT FECHA_INICIO FROM indicador WHERE ID=?), FECHA) AS VALOR_ACUMULATIVO"
+ " , VALOR_ACTUAL, FECHA , (SELECT PERSPECTIVA FROM OBJETIVOS WHERE ID=(SELECT ID_OBJETIVOS FROM ACCIONES WHERE ID=ID_INDICADOR)) "+
" AS PERSPECTIVA, META_LOGRADA, ID_INDICADOR "+
" FROM META_LOGRADA WHERE ID_INDICADOR=?;");
			pst.setInt(1, id_indicador);
			pst.setInt(2, id_indicador);
			pst.setInt(3, id_indicador);
					rs = pst.executeQuery();
			
					HBox hboxHorizontal=new HBox();
					hboxHorizontal.setPrefHeight(339);
					hboxHorizontal.setPrefWidth(773);
					hboxHorizontal.setSpacing(30);
					int cont=1;
			while(rs.next()){
				//logica
				
					int id=rs.getInt("ID");
				
					labelFechaContenido.setText("Fecha: "+rs.getDate("FECHA") );
					labelResponsablesContenido.setText(rs.getString("RESPONSABLE_INDICADOR"));
					labelFormulaContenido.setText(rs.getString("FORMULA_INDICADOR"));
					labelMetaContenido.setText(String.valueOf(rs.getDouble("META_VERDE_FINAL")));
				
					xYChartSeriesMetaEstablecidad.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("META_VERDE_FINAL")));
				
				
					
					xYChartSeriesMetaLograda.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("VALOR_ACUMULATIVO")));
					
					xYChartSeriesPeligro.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("META_ROJO_FINAL")));
					
					xYChartSeriesPrecaucion.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("META_AMARILLO_FINAL")));
					
					
					  if(Indicadores_controler.TIPO_INDICADOR_NUMERO_CANTIDAD==rs.getInt("TIPO_FORMULA")){
							xYChartSeriesPeriodo.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("DATO_NUMERO_UNO")));
							labelValorActualReloj.setText(rs.getString("META_VERDE_FINAL")+"Uds.");
							labelLogroAlcanzado.setText(rs.getString("VALOR_ACUMULATIVO")+"Uds.");
							lineChartMetas.getYAxis().setLabel("Unidades(Uds.)");

							labelFormulaContenido.setText(rs.getString("VAR_NUMERO_UNO"));
						  //xYChartSeriesMetaLogradaPeriodo.getData().add(new XYChart.Data(String.valueOf(rsset.getDate("FECHA")), rs.getDouble("DATO_NUMERO_UNO")));

	           		}else if(Indicadores_controler.TIPO_INDICADOR_PORCENTAJE==rs.getInt("TIPO_FORMULA")){
	           			xYChartSeriesPeriodo.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("DATO_PORCENTAJE_UNO")/rs.getDouble("DATO_PORCENTAJE_DOS")));
						//xYChartSeriesMetaLogradaPeriodo.getData().add(new XYChart.Data(String.valueOf(rsset.getDate("FECHA")), rsset.getDouble("DATO_PORCENTAJE_UNO")/rsset.getDouble("DATO_PORCENTAJE_DOS")));
						labelValorActualReloj.setText(rs.getString("META_VERDE_FINAL")+"%");
						labelLogroAlcanzado.setText(rs.getString("VALOR_ACUMULATIVO")+"%");
						lineChartMetas.getYAxis().setLabel("Porcentaje(%)");
						
						labelFormulaContenido.setText(rs.getString("VAR_PORCENTAJE_UNO")+"/"+rs.getString("VAR_PORCENTAJE_DOS"));


	           		}
					
					

					
					
					//xYChartSeries.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("META_VERDE_FINAL")));
					
					lineChartMetas.setTitle(rs.getString("NOMBRE_INDICADOR"));
					lineChartMetas.setCursor(Cursor.HAND);
					
				
					labelObjetivo.setText("OBJETIVO ESTRATEGICO: \n"+rs.getString("NOMBRE_OBJETIVO"));
					labelAccion.setText("ACCIÓN ESTRATEGICA: \n"+rs.getString("NOMBRE_ACCIONES"));
					labelPerspectiva.setText(ObjetivosEstrategicosWidgetsAnchorPaneControler.nombrePerspectiva(rs.getInt("PERSPECTIVA")));
					labelPerspectiva.setStyle("-fx-background-color: "+ObjetivosEstrategicosWidgetsAnchorPaneControler.colorCSSPerspectiva(rs.getInt("PERSPECTIVA")) +";");
					
					labelValorActualContenido.setText(rs.getString("VALOR_ACTUAL"));
					
					
					if(rs.getDouble("META_ROJO_INICIAL")<rs.getDouble("META_VERDE_FINAL")){
						labelPeligroContenido.setText(rs.getString("META_ROJO_INICIAL")+"<=f(x)<="+rs.getString("META_ROJO_FINAL"));
						labelPrecaucionContenido.setText(rs.getString("META_ROJO_FINAL")+"<f(x)<="+rs.getString("META_AMARILLO_FINAL"));
						labelMetaContenido.setText(rs.getString("META_AMARILLO_FINAL")+"<f(x)<="+rs.getString("META_VERDE_FINAL"));
					}else{
						labelPeligroContenido.setText(rs.getString("META_ROJO_INICIAL")+">=f(x)>="+rs.getString("META_ROJO_FINAL"));
						labelPrecaucionContenido.setText(rs.getString("META_ROJO_FINAL")+">f(x)>="+rs.getString("META_AMARILLO_FINAL"));
						labelMetaContenido.setText(rs.getString("META_AMARILLO_FINAL")+">f(x)>="+rs.getString("META_VERDE_FINAL"));
					}
					
					
					final double metaRojoInicial=rs.getDouble("META_ROJO_INICIAL");
					final double metaRojoFinal=rs.getDouble("META_ROJO_FINAL");
					final double precaucion= rs.getDouble("META_AMARILLO_FINAL");
					final double meta=rs.getDouble("META_VERDE_FINAL");
					final double metalograda=rs.getDouble("VALOR_ACUMULATIVO");
					
					mostrarSemaforo(metaRojoInicial, metaRojoFinal,precaucion, meta, metalograda);

					Thread hilo =new Thread(new Runnable() {
						
						@Override
						public void run() {
							

						}
					}, rs.getString("NOMBRE_INDICADOR"));
					hilo.start();
					
				
					//logica
				
				
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
	
	public void mostrarSemaforo( double peligroIncial, double peligro, double precaucion, double meta, double metaAlcanzada ){
		
		double tamano=0;
		double anguloPeligro=0;
		double anguloPrecaucion=0;
		double anguloMetaVerde=0;
		double anguloMetaSuperadaAzul=0;
		double anguloMetaAlcanzada=0;
		
		if(meta>peligroIncial){
			if(metaAlcanzada>meta){
				tamano=metaAlcanzada-peligroIncial;
				labelderechoReloj.setText(metaAlcanzada+"");
				labelIzquierdoReloj.setText(peligroIncial+"");
			}else{
				tamano=meta-peligroIncial;
				labelIzquierdoReloj.setText(peligroIncial+"");
				labelderechoReloj.setText(meta+"");
			}
			
			//angulo peligro
			anguloPeligro=((peligro-peligroIncial)*180)/tamano;
			ArcMetaPeligro.setStartAngle(180-anguloPeligro);
			ArcMetaPeligro.setLength(anguloPeligro);
			
			//angulo Precaucion
			anguloPrecaucion=((precaucion-peligroIncial)*180)/tamano;
			ArcMetaPrecaucion.setStartAngle(180-anguloPrecaucion);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);
			
			//angulo Meta
			anguloMetaVerde=((meta-peligroIncial)*180)/tamano;
			ArcMetaVerde.setStartAngle(180-anguloMetaVerde);
			ArcMetaVerde.setLength(anguloMetaVerde);
			
			//angulo metaoptima
			
			//angulo meta alcanzada indicasor
			anguloMetaAlcanzada=((metaAlcanzada-peligroIncial)*180)/tamano;
			
			ArcFlechaIndicador.setStartAngle(180-anguloMetaAlcanzada-1);
		
			
			
			//new hilosIndicador(anguloMetaAlcanzada);
			
			
		}else{
			if(metaAlcanzada<meta){
				tamano=peligroIncial-metaAlcanzada;
				labelIzquierdoReloj.setText(metaAlcanzada+"");
				labelderechoReloj.setText(peligroIncial+"");
				
				
			}else{
				tamano=peligroIncial-meta;
				labelIzquierdoReloj.setText(meta+"");
				labelderechoReloj.setText(peligroIncial+"");
			}
			
			
			//angulo peligro
			anguloPeligro=((peligroIncial-peligro)*180)/tamano;
			ArcMetaPeligro.setStartAngle(0);
			ArcMetaPeligro.setLength(anguloPeligro);
			
			//angulo Precaucion
			anguloPrecaucion=((peligroIncial-precaucion)*180)/tamano;
			ArcMetaPrecaucion.setStartAngle(0);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);
			
			//angulo Meta
			anguloMetaVerde=((peligroIncial-meta)*180)/tamano;
			ArcMetaVerde.setStartAngle(0);
			ArcMetaVerde.setLength(anguloMetaVerde);
			
			//angulo metaoptima
			
			//angulo meta alcanzada
			
			anguloMetaAlcanzada=((peligroIncial-metaAlcanzada)*180)/tamano;
			ArcFlechaIndicador.setStartAngle(anguloMetaAlcanzada-1);
			
			
			
			
		}
		
		
		
		
		
	}
	


	
}
