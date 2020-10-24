package balancedScorecard.indicadores;

public class TipoIndicador {
	
	private int id;
	private String nombre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		TipoIndicador other = (TipoIndicador) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public TipoIndicador(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	} 

	
	@Override
	public String toString(){
		return nombre;
	}
	
}
