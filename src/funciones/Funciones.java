package funciones;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;



import org.apache.commons.codec.digest.DigestUtils;




import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Funciones {
	
	
	
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
	
	public File seleccionarPDF(){

		 	FileChooser fileChooser = new FileChooser();
	        FileChooser.ExtensionFilter extFilterpdf = new FileChooser.ExtensionFilter("pdf files (*.pdf)", "*.pdf");
	        fileChooser.getExtensionFilters().addAll(extFilterpdf);
	        File file;
	        file = fileChooser.showOpenDialog(null);
	        if (file != null) {
	            if (file.length() < 6000000) {
	            	return file;
	            	                
	         } else {

	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Alerta");
	                alert.setHeaderText("Alerta");
	                alert.setContentText("Este archivo es demasidado grande :(.. \n");
	                alert.initStyle(StageStyle.UNDECORATED);
	                return null;

	            }

	        }
	       
		return file;
	}
	public static String decimalReducido(String value)
	{
	    int integerPlaces = 0;
	    int decimalPlaces = 0;
	    String aux;
	    if (value.contains("."))
	    {
	    	value=value+"000";
	     
	    }else{
	    	value=value+".00000";
	    }
	    integerPlaces = value.indexOf('.');
        decimalPlaces = value.length() - integerPlaces - 1;
	    
	     aux=value.substring(0, integerPlaces+3);
	    return aux;
	}
	
	
	
	public Image seleccionarImage(){
		 FileChooser fileChooser = new FileChooser();
	        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
	        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
	        Image image=null;
	        fileChooser.getExtensionFilters().addAll(extFilterjpg, extFilterpng);
	        File file;
	        file = fileChooser.showOpenDialog(null);

	        if (file != null) {
	            if (file.length() < 6000000) {
					try {
						InputStream inputStream = new FileInputStream(file.getAbsolutePath());
		                 image = new Image(inputStream);
					} catch (IOException e) {
						e.printStackTrace();
					}
	                
	         } else {

	        	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Alerta");
	                alert.setHeaderText("Alerta");
	                alert.setContentText("Este archivo es demasidado grande :(.. \n");
	                alert.initStyle(StageStyle.UNDECORATED);
	                return null;

	            }

	        }
	        return image;
		
	}

	public String encriptar(String texto){
		
		return DigestUtils.md5Hex(texto); 
	
	}
	
	public void  mostrarInterfazModal(String urlFxml, String css) throws IOException{
		
		
		System.out.println("Hola estoy dento de, metodo de modal :D");
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource(urlFxml));
		fXMLLoader.load();
		Parent parent= fXMLLoader.getRoot();
		Scene scene=new Scene(parent);
		scene.setFill(new Color(0,0,0,0));
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		
	}
	
	public void mostrarInterfazModalShowAndWait(String urlFxml, String css) throws IOException{
		System.out.println("Hola estoy dento de, metodo de modal :D");
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource(urlFxml));
		fXMLLoader.load();
		Parent parent= fXMLLoader.getRoot();
		Scene scene=new Scene(parent);
		scene.setFill(new Color(0,0,0,0));
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.showAndWait();;
		
	}
	
	
	

public static boolean isValid_forDouble( String oldText, String newChar, double limit )
{
    boolean valid = false;
    String newText = oldText + newChar;
    int maxDecimals = getDecimals(String.valueOf(limit));
    int decimals = 0;

    if ( newChar.matches("[0-9]") )
    {
        decimals = getDecimals(newText);
        if ( Double.valueOf(newText) <= limit )
            if ( decimals <= maxDecimals )
                valid = true;     
    }

    if ( newChar.equals(".") )
    {
        if ( !oldText.contains(".") )
                valid = true;
    }                

    return valid;
} 


private static int getDecimals(String value)
{
    int integerPlaces = 0;
    int decimalPlaces = 0;

    if (value.contains("."))
    {
        integerPlaces = value.indexOf('.');
        decimalPlaces = value.length() - integerPlaces - 1;
    }

    return decimalPlaces;
}
	
	
}
