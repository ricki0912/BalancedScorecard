package balancedScorecard.objetivos;

public class Perpectiva {
	private int id;
	private String nombre;
	public Perpectiva(int id, String nombre){
		this.id=id;
		this.nombre=nombre;
	}
	public Perpectiva (int id){
		this.id=id;
	}

	@Override
	public String toString(){
		return nombre;
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
		Perpectiva other = (Perpectiva) obj;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
