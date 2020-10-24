package acciones;

public class ObjetivoAcciones_Class {
	
	private int id;
	private int nro;
	private String nombre;
	private OpcionesControler opciones;
	
	
	
	
	public OpcionesControler getOpciones() {
		return opciones;
	}



	public void setOpciones(OpcionesControler opciones) {
		this.opciones = opciones;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjetivoAcciones_Class other = (ObjetivoAcciones_Class) obj;
		if (id != other.id)
			return false;
		return true;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getNro() {
		return nro;
	}



	public void setNro(int nro) {
		this.nro = nro;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public ObjetivoAcciones_Class(int id, int nro, String nombre){
		this.id=id;
		this.nro=nro;
		this.nombre=nombre;
		this.opciones=new OpcionesControler();
		
	}
	
	
	public ObjetivoAcciones_Class(int id){
		this.id=id;
	}
	
	
	@Override
	public String toString(){
		return nombre;
	}
	
	
	

}
