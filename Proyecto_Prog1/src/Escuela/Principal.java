package Escuela;

import java.util.ArrayList;
import java.util.Collections;

/*
 * @author Aitor Ugarte
 */
public class Principal {

	private static ArrayList<Alumno> registro = new ArrayList<Alumno>();

	public static void main(String[] args) {
		System.out.println("Bienvenido " + System.getProperty("user.name"));
		Menu();

	}

	/*
	 * Menú del programa
	 */
	public static void Menu() {

		int cont = 0;
		do {
			System.out.println("\n.:Menú:.");
			System.out.println("1.Introducir alumnos." + "\n2.Visualizar contenido."
					+ "\n3.Mostrar en orden alfabético." + "\n4.Mostrar aprobados."
					+ "\n5.Porcentaje aprobados según género." + "\n6.Eliminar alumno." + "\n7.Salir.");
			System.out.print("Introduzca una opción: ");
			int opcion = Utilidades.leerEntero();

			switch (opcion) {

			case 1:
				Alumnos();
				break;
			case 2:
				Visualizar();
				break;
			case 3:
				OrdenAlfabtico();
				break;
			case 4:
				mostrarAprobados();
				break;
			case 5:
				estadistica();
				break;
			case 6:
				System.out.print("Introduzca el nombre del alumno: ");
				String name = Utilidades.leerCadena();
				eliminar(name);
				break;
			case 7:
				System.out.println("Gracias por utilizar este programa.");
				cont = 1;
				break;
			default:
				System.err.println("Error, seleccione una opción válida.");

			}
		} while (cont == 0);
	}

	/*
	 * Método que pide introducir los datos a guardar
	 */
	public static void Alumnos() {

		String nombre = null;
		double nota = 0;
		char genero;
		boolean aprobado = false;
		Alumno alumno;
		char seguir;
		boolean error = false;

		do {

			System.out.print("Nombre: ");
			nombre = Utilidades.leerCadena();

			do {
				System.out.print("Nota: ");
				nota = Utilidades.leerReal();
				if (nota > 10 || nota <= 0) {
					System.err.println("\nNota fuera de los límites establecidos.");
					error = true;
				} else {
					error = false;
				}
			} while (error == true);
			do {
				System.out.print("Género (M/F):");
				genero = Utilidades.leerCaracter();
				if (genero == 'M' || genero == 'F') {
					error = false;
				} else {
					System.err.print("\nIntroduzca un carácter válido.");
					error = true;
				}
			} while (error == true);

			// Creamos el objeto alumno
			alumno = new Alumno(nombre, nota, aprobado, genero);

			// Asignamos los valores a los atributos
			if (nota >= 5) {
				aprobado = true;
			}
			alumno.setAprobado(aprobado);
			alumno.setNombre(nombre);
			alumno.setNota(nota);
			alumno.setGenero(genero);

			// Guardamos el alumno
			registro.add(alumno);

			System.out.print("¿Desea introducir más alumnos?(S/N): ");
			seguir = Utilidades.leerCaracter();

		} while (seguir == 'S' || seguir == 's');
	}

	/*
	 * Método para visualizar los datos introducidos
	 */
	public static void Visualizar() {

		System.out.println();
		for (int i = 0; i < registro.size(); i++)
			System.out.println(registro.get(i)); // Se invoca el método ToString
													// de la clase Alumno
	}

	/*
	 * Método para mostrar los alumnos ordenador por órden alfabético
	 */
	public static void OrdenAlfabtico() {

		Collections.sort(registro);

		System.out.println();
		for (int i = 0; i < registro.size(); i++) {
			System.out.println(registro.get(i).getNombre() + " " + registro.get(i).getNota());
		}

	}

	/*
	 * Método para mostrar todos los alumnos por aprobados
	 */
	public static void mostrarAprobados() {
		int cont = 0;

		
		System.out.println("\nAlumnos aprobados: ");
		for (int i = 0; i < registro.size(); i++) {

			if (registro.get(i).isAprobado()) {
				System.out.println(registro.get(i).getNombre() + " " + registro.get(i).getNota());
				cont++;
			}
		}
		if(registro.size() == 0){
			
		}
		else if (cont == 0) {
			System.err.println("Ningún alumno ha aprobado.");
		}
	}

	/*
	 * Método que calcula el porcentaje de aprobados masculino y femenino
	 */
	public static void estadistica() {
		float chicos = 0; // Cantidad de chicos
		float chicas = 0; // Cantidad de chicas
		float aprobados = 0; // Cantidad de chicos aprobados
		float aprobadas = 0; // Cantidad de chicas aprobadas
		float porcentajeM = 0; // Porcentaje de chicos aprobados
		float porcentajeF = 0; // Porcentaje de chicas aprobadas

		for (int i = 0; i < registro.size(); i++) {

			if (registro.get(i).getGenero() == 'M') {
				chicos++;
				if (registro.get(i).getNota() >= 5) {
					aprobados++;
				}
			}
			if (registro.get(i).getGenero() == 'F') {
				chicas++;
				if (registro.get(i).getNota() >= 5) {
					aprobadas++;
				}
			}

		}
		if (chicos == 0) {
			porcentajeM = 0;
		} else {
			porcentajeM = (aprobados / chicos) * 100;
		}
		if (chicas == 0) {
			porcentajeF = 0;

		} else {
			porcentajeF = (aprobadas / chicas) * 100;
		}
		System.out.println("\nPorcentaje de aprobados: " + "\nChicos " + porcentajeM 
				+ "%" + "\nChicas " + porcentajeF + "%");
	}

	/*
	 * Método que elimina al alumno introducido
	 */
	public static void eliminar(String nombre) {
		
		boolean encontrado = false;
		
		for (int i = 0; i < registro.size(); i++) {
			if (nombre.equals(registro.get(i).getNombre())) {
				registro.remove(i);
				encontrado = true;
			}
		}
		if(encontrado == false){
			System.err.println("El usuario " + nombre + " no existe.");
		}
	}
}
