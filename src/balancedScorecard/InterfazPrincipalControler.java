
package balancedScorecard;

import com.jfoenix.controls.JFXButton;

import balancedScorecard.semaforo_modal.Semaforo_modal;
import cargando.Cargando;
import funciones.Conexion;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sesion.Sesion;


public class InterfazPrincipalControler implements Initializable {
	
	@FXML private JFXButton jFXButtonSlideshow;

    @FXML
    private JFXButton jFXButtonInciarSesion;

    @FXML
    private MenuButton menuButtonIncicioSesion;

    @FXML
    private Circle circleFotoPerfilGrande;

    
    
    @FXML
    private Button buttonSalir;

    @FXML
    private Circle CircleFotoPerfil;

    @FXML
    private Label labelNombreUsuario;

    @FXML
    private Label labelCargoUsuario;

    @FXML
    private ImageView imageViewLogoInstitucional;

    @FXML
    private Hyperlink hyperlinkEditarImagen;

    @FXML
    private Label labelNombreInstitucional;

    @FXML
    private Hyperlink hyperlinkEditarNombre;

   @FXML AnchorPane anchorPaneSemiTransparente;

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnMapaProcesos;
    @FXML
    private JFXButton btnPricing;
    @FXML
    private JFXButton btnContacts;
    @FXML
    private JFXButton btnWidgets;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnAlerts;
    
    @FXML JFXButton btnUsuarios;
    
    @FXML JFXButton btnPerfil;
    @FXML private JFXButton btnAcercaDe;
    
    @FXML private VBox vBoxMenu;
    
    AnchorPane contacts,alerts,pricing,profiles,widgets,controls, usuarios, mapaProcesos;
    @FXML
    private JFXButton btnControls;
    
    private VBox arrayCopiaSeguridad;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	
    	
    	//	 arrayCopiaSeguridad=vBoxMenu;
    	
    	LocalDate date = LocalDate.of(2017, Month.DECEMBER, 26);
    	LocalDate datePlusOneDay = date.plus(1, ChronoUnit.MONTHS);
    	
    	
    	if(LocalDate.now().isAfter(datePlusOneDay)){
    		
    	}else{
    		vBoxMenu.getChildren().remove(btnAcercaDe);
    	}
    	
    	
    	//Visible 
    	mostrarPrivilegios();
    	priviligios();

   
    	
    	//

    	seleccionarLogoInstitucional();
    	seleccionarNombresInstitucional();
    	
 
    	jFXButtonSlideshow.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				 Stage stagess=(Stage)jFXButtonSlideshow.getScene().getWindow();
			      AnchorPane stackPane=(AnchorPane) stagess.getScene().getRoot().getChildrenUnmodifiable().get(3);
				  stackPane.setVisible(true);

					String urlFxml = "/balancedScorecard/semaforo_modal/Semaforo.fxml";
					String css = "/balancedScorecard/semaforo_modal/semaforo_modal.css";
					try {
						
						
						FXMLLoader fXMLLoader=new FXMLLoader();
						fXMLLoader.setLocation(getClass().getResource(urlFxml));
						fXMLLoader.load();
						
						Semaforo_modal mvc=fXMLLoader.getController();
						

						
						
						Parent parent= fXMLLoader.getRoot();
						Scene scene=new Scene(parent);
						scene.setFill(new Color(0,0,0,0));
						scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
						Stage stage=new Stage();
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initStyle(StageStyle.TRANSPARENT);
						
						
						
						stage.showAndWait();;
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					stackPane.setVisible(false);			
				
			}
		});
    	
    	btnMapaProcesos.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				mostrarPrivilegios();
		    	
		    	 try {
		             mapaProcesos= FXMLLoader.load(getClass().getResource("PrincipalInterfazMapaEstrategicoInterfaz.fxml"));

		        } catch (IOException ex) {
		            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
		        }
		        setNode(mapaProcesos);
			}
		});
    	
    	imageViewLogoInstitucional.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
            	hyperlinkEditarImagen.setVisible((Sesion.ROL==1)?true:false);
				
			}
		});
    	
    	imageViewLogoInstitucional.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
            	hyperlinkEditarImagen.setVisible(false);
				
			}
		});
    	
    	
    	hyperlinkEditarImagen.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
            	hyperlinkEditarImagen.setVisible((Sesion.ROL==1)?true:false);
				
			}
		});
    	
    	hyperlinkEditarImagen.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
            	hyperlinkEditarImagen.setVisible(false);
				
			}
		});
    	
    	
        labelNombreInstitucional.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	hyperlinkEditarNombre.setVisible((Sesion.ROL==1)?true:false);
            }
          });

        labelNombreInstitucional.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	hyperlinkEditarNombre.setVisible(false);

            }
          });
    	
        
        hyperlinkEditarNombre.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	hyperlinkEditarNombre.setVisible((Sesion.ROL==1)?true:false);
            }
          });

        hyperlinkEditarNombre.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	hyperlinkEditarNombre.setVisible(false);

            }
          });
    	
        btnAcercaDe.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(AlertType.INFORMATION, "Desarrollado por:\n"
															+ "Ricardo J. Solis Almerco e-mail: ricki.0912@gmail.com, Nro. contacto: 930616061\n"
															+ "Julio Condor Chacon e-mail: Nro. contacto: 923313696\n"
															+ "Robert Mamani Cordova\n"
															+ "",ButtonType.CANCEL);
				alert.showAndWait();
        	  
             
			}
		});;
    	
    	buttonSalir.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(cerrarVentana()){
					//Sesion.ROL=0;
					//priviligios();
					//JFXButton hyperLinkCambiarContrasena=(JFXButton)arg0.getSource();
					Stage stage=(Stage)jFXButtonInciarSesion.getScene().getWindow();

					stage.close();
					
					Sesion.ROL=0;
					Parent parent2=null;
					try {
						parent2 = FXMLLoader.load(getClass().getResource("/balancedScorecard/InterfazPrincipalInterfaz.fxml"));
						Stage stageLogin = new Stage();
						Scene scene2=new Scene(parent2);
						stageLogin.setScene(scene2);
						stageLogin.setMaximized(true);
						//stageLogin.initStyle(StageStyle.UNDECORATED);
						stageLogin.show();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
		});
    	
    	hyperlinkEditarImagen.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Image image=seleccionarImage();
				if(image!=null){
					imageViewLogoInstitucional.setImage(image);
					insertarLogoInstitucional();
				}
								
			}
		});
    	
    	hyperlinkEditarNombre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Nombre Institucional");
				dialog.setHeaderText("");
				dialog.setContentText("Nuevo Nombre Institucional ");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					
				    
				    insertarNombreInstitucional(result.get().trim());
				    seleccionarNombresInstitucional();
				}
			}
		});
    	
    	
    	btnPerfil.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mostrarPrivilegios();
		    	
		    	
		    	String url="/perfil/PerfilInterfaz.fxml";
		    	String css="/perfil/PerfilInterfaz.css";
		    	try {
		    		FXMLLoader fXMLLoader = new FXMLLoader();
		    		fXMLLoader.load(getClass().getResource(url).openStream());
		    		BorderPane borderPane =fXMLLoader.getRoot();
		    		borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
		            //stPane_ventana.getChildren().clear();
		    		holderPane.getChildren().clear();  
		    		
		    		AnchorPane.setTopAnchor(borderPane, 0.0);
		           	AnchorPane.setRightAnchor(borderPane, 0.0);
		           	AnchorPane.setLeftAnchor(borderPane, 0.0);
		           	AnchorPane.setBottomAnchor(borderPane, 0.0);
		    		
		    		borderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		            borderPane.setPrefSize(400, 600);
		            borderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		            holderPane.getChildren().add(borderPane);
		            //stPane_ventana.setCenter(borderPane);
		          } catch (IOException ex) {
		              Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
		          }
				
				
				
				
			}
		});
    	
    	jFXButtonInciarSesion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				JFXButton hyperLinkCambiarContrasena=(JFXButton)arg0.getSource();
				
				Stage stage=(Stage)hyperLinkCambiarContrasena.getScene().getWindow();
				AnchorPane stackPane=(AnchorPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(3);
				stackPane.setVisible(true);
				
				
				
				String urlFxml = "/login/Login.fxml";
				String css = "";
				try {
					
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					//Mision_Vision_controler mvc=fXMLLoader.getController();
					//mvc.setTapOpcion(Mision_Vision_controler.TAP_MISION);
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stages=new Stage();
					stages.setScene(scene);
					stages.initModality(Modality.APPLICATION_MODAL);
					stages.initStyle(StageStyle.TRANSPARENT);
					stages.showAndWait();;
					
					stackPane.setVisible(false);
					
					stage.close();
					
					Stage stageCargando=Cargando.cargando(100,100);
					stageCargando.centerOnScreen();
					stageCargando.show();
					
					
					Task<Void> task=new Task<Void>(){

						@Override
						protected Void call() throws Exception {
							
							try {
								
								
								
								Parent parent2=null;

								parent2 = FXMLLoader.load(getClass().getResource("/balancedScorecard/InterfazPrincipalInterfaz.fxml"));
								Scene scene2=new Scene(parent2);
								Platform.runLater(new Runnable() {
									
									@Override
									public void run() {
										stageCargando.close();
										Stage stageLogin = new Stage();
										stageLogin.setScene(scene2);
										stageLogin.setMaximized(true);
										//stageLogin.initStyle(StageStyle.UNDECORATED);
										stageLogin.show();
										
										
									}
								});
							} catch (IOException e) {
								
								e.printStackTrace();
							}
							

							return null;
						}
						
					};
					
					Thread hilo=new Thread(task);
					hilo.setDaemon(true);
					hilo.start();
					
					
					
					
					
					
					
					
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
    	
    	
        //Load all fxmls in a cache
        try {
             contacts = FXMLLoader.load(getClass().getResource("PrincipalInterfazObjetivosEstrategicosInterfaz.fxml"));
             alerts = FXMLLoader.load(getClass().getResource("PrincipalInterfazCuadrosEstadisticosInterfaz.fxml"));
             pricing = FXMLLoader.load(getClass().getResource("PrincipalInterfazMisionVisionInterfaz.fxml"));
             profiles = FXMLLoader.load(getClass().getResource("PrincipalInterfazIndicadoresInterfaz.fxml"));
             widgets = FXMLLoader.load(getClass().getResource("PrincipalAcciones.fxml"));
             controls = FXMLLoader.load(getClass().getResource("PrincipalInterfazMetasMensualesInterfaz.fxml"));
             //usuarios= FXMLLoader.load(getClass().getResource("/personal/PersonalInterfaz.fxml"));
             mapaProcesos= FXMLLoader.load(getClass().getResource("PrincipalInterfazMapaEstrategicoInterfaz.fxml"));
            
        if(Sesion.ROL!=0){     
             setNode(pricing);
        }else{
        	setNode(alerts);
        }
        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        
        
    }

    
    private void mostrarPrivilegios(){
    	//vBoxMenu=arrayCopiaSeguridad;
    		if(Sesion.ROL==0){
    			jFXButtonInciarSesion.setVisible(true);
    			menuButtonIncicioSesion.setVisible(false);
    		}else{
    			jFXButtonInciarSesion.setVisible(false);
    			menuButtonIncicioSesion.setVisible(true);
    		}
    		
    		if(Sesion.ROL!=1){
    			vBoxMenu.getChildren().remove(btnMapaProcesos);
			}
    		
    		if(Sesion.ROL==0){
    			vBoxMenu.getChildren().remove(btnContacts);
    			
    		}
    		
    		if(Sesion.ROL==0){
    			vBoxMenu.getChildren().remove(btnWidgets);
    			
    		}
    		if(Sesion.ROL==0){
    			vBoxMenu.getChildren().remove(btnProfile);
    			
    		}
        	
        	if(Sesion.ROL==0){
        		 vBoxMenu.getChildren().remove(btnControls);
        	}
        	
        	if(Sesion.ROL!=1){
        		vBoxMenu.getChildren().remove(btnUsuarios);
        	}
        	
        	if(Sesion.ROL==0){
        		vBoxMenu.getChildren().remove(btnPerfil);
        	}
    }
    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(2000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    
    

    @FXML
    private void switchUsuarios(ActionEvent event) {
    	mostrarPrivilegios();
    	
    	
    	String url="/personal/PersonalInterfaz.fxml";
    	String css="/personal/personalInterfaz.css";
    	try {
    		FXMLLoader fXMLLoader = new FXMLLoader();
    		fXMLLoader.load(getClass().getResource(url).openStream());
    		BorderPane borderPane =fXMLLoader.getRoot();
    		borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
            //stPane_ventana.getChildren().clear();
    		holderPane.getChildren().clear();  
    		
    		AnchorPane.setTopAnchor(borderPane, 0.0);
           	AnchorPane.setRightAnchor(borderPane, 0.0);
           	AnchorPane.setLeftAnchor(borderPane, 0.0);
           	AnchorPane.setBottomAnchor(borderPane, 0.0);
    		
    		borderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            borderPane.setPrefSize(400, 600);
            borderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            holderPane.getChildren().add(borderPane);
            //stPane_ventana.setCenter(borderPane);
          } catch (IOException ex) {
              Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
          }
       	
       	
       	
       	
       
    	
    	
    	
    	
    	
        
    }
    
    @FXML
    private void switchPricing(ActionEvent event) {
    	mostrarPrivilegios();
    	 try {
             pricing = FXMLLoader.load(getClass().getResource("PrincipalInterfazMisionVisionInterfaz.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(pricing);
    }

    @FXML
    private void switchContacts(ActionEvent event) {
    	mostrarPrivilegios();
    	
    	 try {
             contacts = FXMLLoader.load(getClass().getResource("PrincipalInterfazObjetivosEstrategicosInterfaz.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(contacts);
    }

    @FXML
    private void switchWidget(ActionEvent event) {
    	mostrarPrivilegios();
    	
    	 try {
             widgets = FXMLLoader.load(getClass().getResource("PrincipalAcciones.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(widgets);
    }

    @FXML
    private void switchProfile(ActionEvent event) {
    	mostrarPrivilegios();
    	 try {
             profiles = FXMLLoader.load(getClass().getResource("PrincipalInterfazIndicadoresInterfaz.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(profiles);
    }

    @FXML
    private void switchAlert(ActionEvent event) {
    	mostrarPrivilegios();
    	 try {
             alerts = FXMLLoader.load(getClass().getResource("PrincipalInterfazCuadrosEstadisticosInterfaz.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(alerts);
    }

    @FXML
    private void switchControls(ActionEvent event) {
    	mostrarPrivilegios();
    	try {
   		 controls = FXMLLoader.load(getClass().getResource("PrincipalInterfazMetasMensualesInterfaz.fxml"));

       } catch (IOException ex) {
           Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
       }
    	
    	
    	
    	AnchorPane.setTopAnchor(controls, 0.0);
    	AnchorPane.setRightAnchor(controls, 0.0);
    	AnchorPane.setLeftAnchor(controls, 0.0);
    	AnchorPane.setBottomAnchor(controls, 0.0);
    	 
        setNode(controls);
        
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
	
	
	public void priviligios(){
		menuButtonIncicioSesion.setVisible((Sesion.ROL!=0)?true:false);
		jFXButtonInciarSesion.setVisible((Sesion.ROL==0)?true:false);
		hyperlinkEditarImagen.setVisible(false);		
		hyperlinkEditarNombre.setVisible(false);

		
		
		if(Sesion.ROL!=0){
			labelNombreUsuario.setText(Sesion.NOMBRES);
			labelCargoUsuario.setText(Sesion.CARGO);
			CircleFotoPerfil.setFill(new ImagePattern(Sesion.FOTO));	
			circleFotoPerfilGrande.setFill(new ImagePattern(Sesion.FOTO));
			
		}else{
			
		}
		
		
		   try {
	            // contacts = FXMLLoader.load(getClass().getResource("PrincipalInterfazObjetivosEstrategicosInterfaz.fxml"));
	             //alerts = FXMLLoader.load(getClass().getResource("PrincipalInterfazCuadrosEstadisticosInterfaz.fxml"));
	             pricing = FXMLLoader.load(getClass().getResource("PrincipalInterfazMisionVisionInterfaz.fxml"));
	             //profiles = FXMLLoader.load(getClass().getResource("PrincipalInterfazIndicadoresInterfaz.fxml"));
	             //widgets = FXMLLoader.load(getClass().getResource("PrincipalAcciones.fxml"));
	             //controls = FXMLLoader.load(getClass().getResource("PrincipalInterfazMetasMensualesInterfaz.fxml"));
	             setNode(pricing);
	        } catch (IOException ex) {
	            Logger.getLogger(InterfazPrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
	        }
		
	
	}
	
	
	public boolean cerrarVentana(){
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea salir :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return true;
		}else {
			return false;
		}
	}
	
	public void insertarLogoInstitucional(){
        String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("UPDATE DATOS_INFORMATIVOS SET LOGO=? WHERE ID=1;"); 
               if (imageViewLogoInstitucional.getImage() != null) {
            	   BufferedImage image = SwingFXUtils.fromFXImage(imageViewLogoInstitucional.getImage(), null);
            	   
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

                   pst.setBlob(1, bais);
               } else {
                   pst.setBlob(1, (Blob) null);
               }
               
               int rs = pst.executeUpdate();
          
               conn.close();
               pst.close();
             
               
               
               
           }   
           catch(Exception e){
        	   
        	   
        	   Alert alert = new Alert(AlertType.ERROR);
        	   alert.setTitle("Error Dialog");
        	   alert.setHeaderText("");
        	   alert.setContentText("error"+e.getMessage());

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
	
	public void insertarNombreInstitucional(String nombre){
        String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("UPDATE DATOS_INFORMATIVOS SET NOMBRE_EMPRESA=? WHERE ID=1;"); 
               if(!nombre.isEmpty()){
            	   pst.setString(1, nombre);
               }else{
            	   pst.setNull(1, java.sql.Types.VARCHAR);
               }
               
               int rs = pst.executeUpdate();
          
               conn.close();
               pst.close();
             
               
               
               
           }   
           catch(Exception e){
        	   
        	   
        	   Alert alert = new Alert(AlertType.ERROR);
        	   alert.setTitle("Error Dialog");
        	   alert.setHeaderText("");
        	   alert.setContentText("error"+e.getMessage());

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
	

	public void seleccionarLogoInstitucional(){
        String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("SELECT LOGO FROM DATOS_INFORMATIVOS WHERE ID=1;");                
               rsset = pst.executeQuery();
               while(rsset.next()){
            	   Blob imageBlob=rsset.getBlob("LOGO");
            	   Image logo;
            	   if(imageBlob!=null){
               		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
               		 logo= new Image(byteArrayInputStream);
               		imageViewLogoInstitucional.setImage(logo);
                  	}else {
                  		logo=new Image("/balancedScorecard/logo.png");
                  		
                  		imageViewLogoInstitucional.setImage(logo);
                  		//Sesion.FOTO=new Image("/src/personal/verPersonal/anadirPersonal/foto0.jpg");
                  	}
               }

               conn.close();
               pst.close();
             
               
               
               
           }   
           catch(Exception e){
        	   
        	   
        	   Alert alert = new Alert(AlertType.ERROR);
        	   alert.setTitle("Error Dialog");
        	   alert.setHeaderText("");
        	   alert.setContentText("error"+e.getMessage());

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
	
	


	public void seleccionarNombresInstitucional(){
        String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("SELECT NOMBRE_EMPRESA FROM DATOS_INFORMATIVOS WHERE ID=1;");                
               rsset = pst.executeQuery();
               while(rsset.next()){
            	   labelNombreInstitucional.setText(rsset.getString("NOMBRE_EMPRESA"));
            	
               }

               conn.close();
               pst.close();
             
               
               
               
           }   
           catch(Exception e){
        	   
        	   
        	   Alert alert = new Alert(AlertType.ERROR);
        	   alert.setTitle("Error Dialog");
        	   alert.setHeaderText("");
        	   alert.setContentText("error"+e.getMessage());

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


	
	
	
	
}
