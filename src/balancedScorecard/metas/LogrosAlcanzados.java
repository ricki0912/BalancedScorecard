package balancedScorecard.metas;

import acciones.OpcionesControler;

public class LogrosAlcanzados {
	private int id;
	private String logroAlcanzadoPeriodoFraccion;
	
	public String getLogroAlcanzadoPeriodoFraccion() {
		return logroAlcanzadoPeriodoFraccion;
	}
	public void setLogroAlcanzadoPeriodoFraccion(String logroAlcanzadoPeriodoFraccion) {
		this.logroAlcanzadoPeriodoFraccion = logroAlcanzadoPeriodoFraccion;
	}
	private String logroAlcanzadoPeriodo;
	public LogrosAlcanzados(int id, int nro, String fecha,String logroAlcanzadoPeriodoFraccion, String logroAlcanzadoPeriodo,  String logroAlcanzado) {
		super();
		this.id = id;
		this.logroAlcanzadoPeriodo = logroAlcanzadoPeriodo;
		this.nro = nro;
		this.logroAlcanzadoPeriodoFraccion=logroAlcanzadoPeriodoFraccion;
		this.fecha = fecha;
		this.logroAlcanzado = logroAlcanzado;
		this.opciones=new OpcionesControler();
	}
	public String getLogroAlcanzadoPeriodo() {
		return logroAlcanzadoPeriodo;
	}
	public void setLogroAlcanzadoPeriodo(String logroAlcanzadoPeriodo) {
		this.logroAlcanzadoPeriodo = logroAlcanzadoPeriodo;
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
		LogrosAlcanzados other = (LogrosAlcanzados) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public LogrosAlcanzados(int id, int nro, String fecha) {
		super();
		this.id = id;
		this.nro = nro;
		this.fecha = fecha;
	}
	public LogrosAlcanzados(int id) {
		super();
		this.id = id;
	}
	public LogrosAlcanzados(int id, int nro, String fecha, String logroAlcanzado) {
		super();
		this.id = id;
		this.nro = nro;
		this.fecha = fecha;
		this.logroAlcanzado = logroAlcanzado;
		this.opciones=new OpcionesControler();
	}
	public OpcionesControler getOpciones() {
		return opciones;
	}
	public void setOpciones(OpcionesControler opciones) {
		this.opciones = opciones;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int nro;
	private String fecha;
	private String logroAlcanzado;
	private OpcionesControler opciones;
	
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getLogroAlcanzado() {
		return logroAlcanzado;
	}
	public void setLogroAlcanzado(String logroAlcanzado) {
		this.logroAlcanzado = logroAlcanzado;
	}
	

}
