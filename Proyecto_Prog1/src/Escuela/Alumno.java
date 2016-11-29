package Escuela;

/*
 * @Author Aitor Ugarte
 */
public class Alumno implements Comparable<Alumno> {

	private String nombre;
	private double nota;
	private boolean aprobado;
	private char genero;

	// Constructor con argumentos
	public Alumno(String nombre, double nota, boolean aprobado, char genero) {

		this.nombre = nombre;
		this.nota = nota;
		this.aprobado = aprobado;
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}
	
	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String toString() {
		String estado = "suspendido";
		String mensaje = null;
		String sexo = null;

		if (isAprobado() == true) {
			estado = "aprobado";
		} 
		if (genero == 'M') {
			sexo = "El alumno ";
		} else {
			sexo = "La alumna ";
		}

		mensaje = sexo + nombre + " tiene un " + nota + " y ha " + estado + ".";

		return mensaje;
	}
	/*
	 * Método que compara el orden alfabético de 2 objetos
	 */
	@Override
	public int compareTo(Alumno alum) {

        return nombre.compareTo(alum.getNombre());
	}

}
