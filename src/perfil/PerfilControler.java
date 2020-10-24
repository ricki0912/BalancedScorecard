package perfil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class PerfilControler implements Initializable {

    @FXML
    private Label labelOpcionSeleccionada;

   // @FXML
 //   private ToggleButton toggleButtonAnadirPersonal;

    @FXML
    private ToggleButton toggleButtonCuenta;
    

    
    @FXML
    private BorderPane borderPane_ventanaInterna_arcPerm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.print("Hola estoy aqui :(:");
		labelOpcionSeleccionada.setText("Perfil");
		String url="/perfil/cuenta/CuentaInterfaz.fxml";
		String css="/perfil/cuenta/cuentaInterfaz.css";
		try {
			//System.out.print("Hola estoy aqui :(");
			setInterfazInterna(borderPane_ventanaInterna_arcPerm, url,css);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		toggleButtonCuenta.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				
				if(toggleButton.isSelected()){
					String url="/perfil/cuenta/CuentaInterfaz.fxml";
					String css="";
					try {
						//System.out.print("Hola estoy aqui :(");
						setInterfazInterna(borderPane_ventanaInterna_arcPerm, url,css);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					toggleButton.setSelected(true);
				}
				
			}
		});
		
		/*
		toggleButtonAnadirPersonal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToggleButton hyperLinkCambiarContrasena=(ToggleButton)event.getSource();
				
				Stage stage=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
				StackPane stackPane=(StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(1);
				stackPane.setVisible(true);
				System.out.println(stage.getScene().getRoot().getChildrenUnmodifiable().get(1));

				String urlFxml="/personal/verPersonal/anadirPersonal/PersonalAnadirInterfaz.fxml";
				
				try {
					mostrarInterfazModal(urlFxml);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stackPane.setVisible(false);

				
			}
		});*/
		
	
	}
	
	
	public void setInterfazInterna (BorderPane stPane_ventana, String url , String css) throws IOException{
		
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource(url).openStream());
		BorderPane borderPane =fXMLLoader.getRoot();
		borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
        stPane_ventana.getChildren().clear();
          borderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        borderPane.setPrefSize(400, 600);
        borderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        stPane_ventana.setCenter(borderPane);
       
	}
	public void  mostrarInterfazModal(String urlFxml) throws IOException, InterruptedException{
		
		
		//System.out.println("Hola estoy dento de, metodo de modal :D");
		//Thread prceso=new Thread("khkjh"){
			//@Override
			//public void run(){
				//System.out.println("esto es una preuba con fe funciona xD");
			//}
		//};
		//prceso.start();
		
		//System.out.println("Cerrado el modal xD");
		
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource(urlFxml));
		fXMLLoader.load();
		Parent parent= fXMLLoader.getRoot();
		Scene scene=new Scene(parent);
		scene.setFill(new Color(0,0,0,0));
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.showAndWait();;
		
		
	}
  

	


  

}