package balancedScorecard;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDatePicker;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class PrincipalInterfazMetasMensualesControler implements Initializable {
	
	@FXML private JFXDatePicker datePickerFecha;
	
	
    @FXML
    private ScrollPane scrollPaneIndicadores;

    @FXML
    private BorderPane borderPaneIndicadores;

    @FXML
    private ScrollPane scrollPaneMetas;

    @FXML
    private BorderPane borderPaneMetas;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println();
		cargarIndicadores();
		cargarMeta();
		
		
		
		scrollPaneIndicadores.vvalueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("hola xDmmm");
				scrollPaneMetas.setVvalue(scrollPaneIndicadores.getVvalue());		
			}
		});		
		scrollPaneMetas.vvalueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("hola xDmmm");
				scrollPaneIndicadores.setVvalue(scrollPaneMetas.getVvalue());		
			}
		});	
	}
	
	public void cargarIndicadores(){
		System.out.println("Hola esttoy estoy dentro de este metodo xD");
		String url="/balancedScorecard/PrincipalInterfazIndicadoresInterfaz.fxml";
		String css="";
		try {
			System.out.print("Hola estoy aqui :(");
			
			setInterfazInterna(borderPaneIndicadores, url,css);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void cargarMeta(){
		System.out.println("Hola esttoy estoy dentro de este metodo xD");
		String url="/balancedScorecard/PrincipalInterfazMetasInterfaz.fxml";
		String css="";
		try {
			System.out.print("Hola estoy aqui :(");
			
			setInterfazInterna_2(borderPaneMetas, url,css);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	

	
public void setInterfazInterna (BorderPane stPane_ventana, String url , String css) throws IOException{
		
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource(url).openStream());
		
		AnchorPane borderPane =fXMLLoader.getRoot();
		borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
        stPane_ventana.getChildren().clear();
          borderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        borderPane.setPrefSize(400, 600);
        borderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
       // stPane_ventana.getChildren().add(borderPane);
        stPane_ventana.setCenter(borderPane);

}

public void setInterfazInterna_2 (BorderPane stPane_ventana, String url , String css) throws IOException{
	
	FXMLLoader fXMLLoader = new FXMLLoader();
	fXMLLoader.load(getClass().getResource(url).openStream());
	
	PrincipalInterfazIMetasMensualesSubInternoControler interno=fXMLLoader.getController();
	datePickerFecha.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {

			
			
			
			LocalDate date = datePickerFecha.getValue();
	         System.err.println("Selected date hhh: " + date);
	         if(datePickerFecha.getValue()!=null){
	        	 interno.mostrarMetasBaseDeDatos(date);
	         }else{
	        	 interno.mostrarMetasBaseDeDatos();
	         }
			
		}
	});
	
	
	interno.setjFXDatePickerFechaMeta(datePickerFecha);
	
	AnchorPane borderPane =fXMLLoader.getRoot();
	borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
    stPane_ventana.getChildren().clear();
      borderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    borderPane.setPrefSize(400, 600);
    borderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
   // stPane_ventana.getChildren().add(borderPane);
    stPane_ventana.setCenter(borderPane);

}
    
}
