package funciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static String MENSAJE_CONEXION=null;
	
	public static String URL_INICIAL="jdbc:mysql://";
	public static  String IP_SERVIDOR=getIpServidor();
	//public static  String IP_SERVIDOR="192.168.1.10";
	//public static  String URL_BASE_DATOS ="jdbc:mysql://localhost/DB_BSC_MILPOCOOP_V4";
	//public static String BASE_DATOS="db_bsc_milpocoop_v3";
	//M1LP0C00P2OI8

	//http://192.168.1.10/milpocoop_bsc.jnlp
	public static String BASE_DATOS="MILPOCOOP_BSC_V1";
	public static final String UNICODE="?useUnicode=yes&characterEncoding=UTF-8";
	//public static final String USER="MILPOCOOP";
	//public static final String PASS="M1LP0C00P2OI8";
	public static final String USER="root";
	public static final String PASS="";

	public static Connection CONN=null;
	


	public static String getIpServidor(){
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	         String texto="";


	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	    	 // File hola=new File("carpeta x<D");
	    	  
	         archivo = new File ("servidor/ip"); //"C:\\archivo.txt"
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         //texto
	         String linea;
	         while((linea=br.readLine())!=null){
	        	 texto=texto+linea;
	         }
	         
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		
	      return texto;
	}

	
	public static void conectarDB(){
		
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	CONN = DriverManager.getConnection(URL_INICIAL+ IP_SERVIDOR+"/"+ BASE_DATOS+UNICODE, USER, PASS);
	            
	        } catch (ClassNotFoundException | SQLException ex) {
	          System.out.println("Problemas al realizar la conexión");
	        	Conexion.MENSAJE_CONEXION=ex.getMessage();
	          System.out.println(ex.getMessage());
	          ex.printStackTrace();
	        	
	        }
	      
	    }
	
	
public static Connection getConexion(){
	
	return CONN;
}


public static void close(){
	try {
		if(CONN!=null){
		CONN.close();
		}
	} catch (SQLException e) {
		System.out.println("Problemas al cerrar la conexion :(");
		e.printStackTrace();
	}
}






}



