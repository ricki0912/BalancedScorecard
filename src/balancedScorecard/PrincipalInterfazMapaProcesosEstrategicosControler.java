package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import balancedScorecard.mision_vision.Mision_Vision_controler;
import balancedScorecard.objetivos.Objetivos_controler;
import funciones.Conexion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sesion.Sesion;

public class PrincipalInterfazMapaProcesosEstrategicosControler implements Initializable{

	  @FXML
	    private JFXButton jFXButton_agregar;

	    @FXML
	    private AnchorPane anchorPaneContenedor;
	    
	    
	    
	    private double  primeroSetStartX, segundoSetStartX;
	    private double primeroSetControlX1, SegundoSetControlX1;
	    private double primeroSetControlY1, segundoSetControlY1;
	    private double primeroSetControlX2, segundoSetControlX2;
	    private double primeroSetControlY2, segundoSetControlY2;
	    private double primeroSetEndX, segundoSetEndX;
	    private double primeroSetEndY, segundoSetEndY;
	    


	    private ProcesoContenidoWidgetsAnchorPaneControler procesoInicio, procesoFinal;
	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		jFXButton_agregar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				
				ProcesoContenidoWidgetsAnchorPaneControler proceso = new ProcesoContenidoWidgetsAnchorPaneControler();
				
				/*
				 Group root = new Group();

				    // bending curve
				    Rectangle srcRect1 = new Rectangle(100,100,50,50);
				    Rectangle dstRect1 = new Rectangle(300,300,50,50);

				 //   CubicCurve curve1 = new CubicCurve( 125, 150, 125, 225, 325, 225, 325, 300);
				    CubicCurve curve1 = new CubicCurve( 125, 150, 55, 285, 375, 155, 325, 300);
				    //curve1.setStartX(400);

				    curve1.setStroke(Color.FORESTGREEN);
				    curve1.setStrokeWidth(4);
				    curve1.setStrokeLineCap(StrokeLineCap.ROUND);
				    curve1.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
				 

				    double size=Math.max(curve1.getBoundsInLocal().getWidth(),
				                         curve1.getBoundsInLocal().getHeight());
				    double scale=size/4d;

				    Point2D ori=eval(curve1,0);
				    Point2D tan=evalDt(curve1,0).normalize().multiply(scale);
				    Path arrowIni=new Path();
				    arrowIni.getElements().add(new MoveTo(ori.getX()+0.2*tan.getX()-0.2*tan.getY(),
				                                        ori.getY()+0.2*tan.getY()+0.2*tan.getX()));
				    arrowIni.getElements().add(new LineTo(ori.getX(), ori.getY()));
				    arrowIni.getElements().add(new LineTo(ori.getX()+0.2*tan.getX()+0.2*tan.getY(),
				                                        ori.getY()+0.2*tan.getY()-0.2*tan.getX()));
				                                        

				    ori=eval(curve1,1);
				    tan=evalDt(curve1,1).normalize().multiply(scale);
				    
				    Path arrowEnd=new Path();
				    arrowEnd.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
				                                        ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
				    arrowEnd.getElements().add(new LineTo(ori.getX(), ori.getY()));
				    arrowEnd.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
				                                        ori.getY()-0.2*tan.getY()-0.2*tan.getX()));

				    root.getChildren().addAll(srcRect1, dstRect1, curve1, arrowEnd);
				    
				    curve1.setStartX(400);

				  */
				
				
				
				
				
				proceso.setOnMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						
						if(arg0.isPrimaryButtonDown()){
							System.out.println(arg0.getSceneX());
							System.out.println(arg0.getX());
							System.out.println(proceso.getWidth());
							//double d = proceso.g - arg0.getX();
					          //dragDelta.y = getCenterY() - arg0.getY();
					          proceso.setCursor(Cursor.MOVE);
						}else if(arg0.isSecondaryButtonDown()){
							proceso.setCursor(Cursor.CROSSHAIR);
							
							procesoInicio=proceso;
							System.out.println(procesoInicio);
						}
						
						
					}
				});
				
				proceso.setOnMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						
						proceso.setCursor(Cursor.HAND);

					}
				});
				
				
				
				proceso.setOnMouseDragged(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						if(arg0.isPrimaryButtonDown()){
							proceso.setLayoutX(arg0.getSceneX()-206-(proceso.getWidth()/2));
							proceso.setLayoutY(arg0.getSceneY()-150-(proceso.getHeight()/2));
						} else if(arg0.isSecondaryButtonDown()){
							
						}
					}
				});
			
			
					proceso.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						proceso.setCursor(Cursor.HAND);
						System.out.println("---  "+procesoInicio);

						if(procesoInicio!=null && procesoInicio!=proceso){
							procesoFinal=proceso;
							
							System.out.println(procesoInicio);
							System.out.println(procesoFinal);
							
							double setLayoutX=0;
							double setLayoutY=0;
							double SetStartX=procesoInicio.getLayoutX()+(procesoInicio.getWidth()/2);;
							double SetStartY=procesoInicio.getLayoutY()+(procesoInicio.getHeight()/2);
						
							double SetEndX=procesoFinal.getLayoutX()+(procesoFinal.getWidth()/2);
							double SetEndY=procesoFinal.getLayoutY()+(procesoFinal.getHeight()/2);
							double SetControlX1 = 125; 
							double SetControlY1=225;// 125, 225, 325, 225
							double SetControlX2= 325; // 55, 285, 375, 155,
							double  SetControlY2=225;
							
							proceso.getArrayCubicCuerve().add(dibujarFlecha(setLayoutX, setLayoutY, SetStartX, SetStartY, SetControlX1, SetControlY1, SetControlX2, SetControlY2, SetEndX, SetEndY));
							
							procesoInicio=null;//anchorPaneContenedor.getChildren().add(arg0)
							procesoFinal=null;
						}
						
						
					
			
						
					}
				});
				   
				proceso.setOnMouseExited(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						proceso.setCursor(Cursor.DEFAULT);						
					}
				});    
				     
				
				
				anchorPaneContenedor.getChildren().addAll(proceso);
			}
		});
		
	}
	

	private CubicCurve dibujarFlecha ( double setLayoutX, double setLayoutY, double SetStartX ,double SetStartY,  double SetControlX1, double SetControlY1,
double SetControlX2, 
    double SetControlY2, 
    double SetEndX, 
     double SetEndY ){
		
		Group root = new Group();

	    // bending curve
	   // Rectangle srcRect1 = new Rectangle(100,100,50,50);
	    //Rectangle dstRect1 = new Rectangle(300,300,50,50);

	    CubicCurve curve1=new CubicCurve(SetStartX, SetStartY, SetControlX1, SetControlY1, SetControlX2, SetControlY2, SetEndX, SetEndY);
	    curve1.setLayoutX(setLayoutX);
	    curve1.setLayoutY(setLayoutY);
	    //   CubicCurve curve1 = new CubicCurve( 125, 150, 125, 225, 325, 225, 325, 300);
	  //  CubicCurve curve1 = new CubicCurve( 125, 150, 55, 285, 375, 155, 325, 300);
	    //curve1.setStartX(400);

	    curve1.setStroke(Color.FORESTGREEN);
	    curve1.setStrokeWidth(4);
	    curve1.setStrokeLineCap(StrokeLineCap.ROUND);
	    curve1.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
	 

	    double size=Math.max(curve1.getBoundsInLocal().getWidth(),
	                         curve1.getBoundsInLocal().getHeight());
	    double scale=size/4d;

	    Point2D ori=eval(curve1,0);
	    Point2D tan=evalDt(curve1,0).normalize().multiply(scale);
	    Path arrowIni=new Path();
	    arrowIni.getElements().add(new MoveTo(ori.getX()+0.2*tan.getX()-0.2*tan.getY(),
	                                        ori.getY()+0.2*tan.getY()+0.2*tan.getX()));
	    arrowIni.getElements().add(new LineTo(ori.getX(), ori.getY()));
	    arrowIni.getElements().add(new LineTo(ori.getX()+0.2*tan.getX()+0.2*tan.getY(),
	                                        ori.getY()+0.2*tan.getY()-0.2*tan.getX()));
	                                        

	    ori=eval(curve1,1);
	    tan=evalDt(curve1,1).normalize().multiply(scale);
	    
	    Path arrowEnd=new Path();
	    arrowEnd.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
	                                        ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
	    arrowEnd.getElements().add(new LineTo(ori.getX(), ori.getY()));
	    arrowEnd.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
	                                        ori.getY()-0.2*tan.getY()-0.2*tan.getX()));

	    root.getChildren().addAll( curve1, arrowEnd);
	    
	    //curve1.setStartX(400);
	    anchorPaneContenedor.getChildren().add(root);
	    
	    return curve1;

	}
	
	
	private Point2D eval(CubicCurve c, float t){
	    Point2D p=new Point2D(Math.pow(1-t,3)*c.getStartX()+
	            3*t*Math.pow(1-t,2)*c.getControlX1()+
	            3*(1-t)*t*t*c.getControlX2()+
	            Math.pow(t, 3)*c.getEndX(),
	            Math.pow(1-t,3)*c.getStartY()+
	            3*t*Math.pow(1-t, 2)*c.getControlY1()+
	            3*(1-t)*t*t*c.getControlY2()+
	            Math.pow(t, 3)*c.getEndY());
	    return p;
	}

	/**
	 * Evaluate the tangent of the cubic curve at a parameter 0<=t<=1, returns a Point2D
	 * @param c the CubicCurve 
	 * @param t param between 0 and 1
	 * @return a Point2D 
	 */
	private Point2D evalDt(CubicCurve c, float t){
	    Point2D p=new Point2D(-3*Math.pow(1-t,2)*c.getStartX()+
	            3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlX1()+
	            3*((1-t)*2*t-t*t)*c.getControlX2()+
	            3*Math.pow(t, 2)*c.getEndX(),
	            -3*Math.pow(1-t,2)*c.getStartY()+
	            3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlY1()+
	            3*((1-t)*2*t-t*t)*c.getControlY2()+
	            3*Math.pow(t, 2)*c.getEndY());
	    return p;
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
