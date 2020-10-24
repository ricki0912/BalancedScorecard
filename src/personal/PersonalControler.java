package personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import funciones.Funciones;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;





public class PersonalControler extends Funciones implements Initializable {

    @FXML
    private Label labelOpcionSeleccionada;

    @FXML
    private ToggleButton toggleButtonAnadirPersonal;

    @FXML
    private ToggleButton toggleButtonVerPersonal;
    

    
    @FXML
    private BorderPane borderPane_ventanaInterna_arcPerm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.print("Hola estoy aqui :(:");
		labelOpcionSeleccionada.setText("Personal");
		
		String url="/personal/verPersonal/VerPersonalInterfaz.fxml";
		String css="/personal/verPersonal/verPersonalInterfaz.css";
		try {
			System.out.print("Hola estoy aqui :(");
			
			setInterfazInterna(borderPane_ventanaInterna_arcPerm, url,css);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		toggleButtonVerPersonal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				
				if(toggleButton.isSelected()){
					String url="/personal/verPersonal/VerPersonalInterfaz.fxml";
					String css="/personal/verPersonal/verPersonalInterfaz.css";
					try {
						System.out.print("Hola estoy aqui :(");
						setInterfazInterna(borderPane_ventanaInterna_arcPerm, url,css);
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}else {
					toggleButton.setSelected(true);
					
				}
				
			}
		});
		
		
		toggleButtonAnadirPersonal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				Stage stagess=(Stage)toggleButtonAnadirPersonal.getScene().getWindow();
				AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				stackPane.setVisible(true);
				System.out.println(stagess.getScene().getRoot().getChildrenUnmodifiable().get(3));
				
				
				ToggleButton tg=(ToggleButton)event.getSource();
				tg.setSelected(true);
				//Stage stage=(Stage)tg.getScene().getWindow();
				//StackPane stackPane=(StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(1);
				//stackPane.setVisible(true);
				//System.out.println(stage.getScene().getRoot().getChildrenUnmodifiable().get(1));
				
						
				String urlFxml="/personal/verPersonal/anadirPersonal/PersonalAnadirInterfaz.fxml";
				String css="/personal/verPersonal/anadirPersonal/anadirPersonalInterfaz.css";
				try {
					mostrarInterfazModalShowAndWait(urlFxml, css);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				stackPane.setVisible(false);
				tg.setSelected(false);
				String url1="/personal/verPersonal/VerPersonalInterfaz.fxml";
				String css1="/personal/verPersonal/verPersonalInterfaz.css";
				try {
					System.out.print("Hola estoy aqui :(");
					setInterfazInterna(borderPane_ventanaInterna_arcPerm, url1,css1);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		
	
	}
	
	

	

	


  

}