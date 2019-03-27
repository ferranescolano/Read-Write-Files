import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.AlphaComposite;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	private static BufferedReader br;

	// Definimos los ficheros necesarios para el programa.
	static File Futbol = new File("futbol.txt");
	static File FutbolSala = new File("futbol_sala.txt");
	static File Basquet = new File("basket.txt");
	static File Badminton = new File("badminton.txt");
	static File VoleyPlaya = new File("voley_playa.txt");

	static FileReader leer = null;
	static BufferedReader buff = null;
	static String lector = null;
	static Scanner scaner = new Scanner(System.in);

	// Creamos los Arrays para los alumnos y los deportes.
	private static ArrayList<Alumno> alumnos = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		alumnos = new ArrayList<>();
		int opcion;

		////////////////////////////////////////////// (0) INICIO DE LA
		////////////////////////////////////////////// APLICACIÓN/////////////////////////////////////////////////////////////////////

		/*
		 * Justo al principio de iniciar la aplicación, recogeremos los valores de los
		 * ficheros para juardarlos en el ArrayList y así poder disponer de todos los
		 * objetos creados una vez ejecutemos el programa.
		 */

		recogerValores();

		// Ejecutaremos este bucle hasta que se pulse el número 7
		do {

			// Aquí sacaremos por pantalla las opciones principales, cada una con sus
			// metodos correspondientes.
			mostrarmenu();
			System.out.println("");
			System.out.println("**************************");
			System.out.println("");
			
			opcion = pedirEntero("Elige una opcion");

			switch (opcion) {
			case 1:
				/*
				 * Este metodo nos permite recoger todos los datos del alumno y guardarlos en el
				 * ArrayList, sobreescribirlos en el fichero y crear el fichero, en caso de que
				 * no exista.
				 */
				registrarAlumno();

				break;

			case 2:
				// Muestra un listado de los cursos y al seleccionar uno de ellos se muestran
				// los alumnos inscritos.
				mostrarAlumnos();

				break;

			case 3:
				/*
				 * Cada uno de estos metodos hace un recuento de los alumnos inscritos en cada
				 * deporte y saca por pantalla las incidencias que se hayan producido en cada
				 * uno de los deportes.
				 */
				calcularFutbol();
				calcularBasquet();
				calcularFutbol_Sala();
				calcularBadminton();
				calcularVoley();

				break;

			case 4:
				// Nos permite eliminar un alumno del ArrayList y de los ficheros creados,
				// sobreescriviendo estos.
				eliminarAlumno();
				break;

			case 5:
				// Nos a mostrar cada deporte con sus respectivos alumnos inscritos y ordenados
				// alfabéticamente.
				listadoInscripciones();

				break;

			case 6:

				/*
				 * Con este metodo eliminamos por completo el fichero de un deporte y mostramos
				 * por pantalla los alumnos que estaban inscrito
				 */
				menuAnularDeporte();

				break;

			default:
				
			}

		} while (opcion != 7);

	}

	///////////////////////////////////////////// (1) REGISTRO DEL
	///////////////////////////////////////////// USUARIO///////////////////////////////////////////////////////////

	public static void registrarAlumno() throws IOException {
		// Guardamos todos los datos de cada usuario

		String Nombre = pedirCadena("Nombre: ");

		String Apellidos = pedirCadena("Apellidos: ");

		String Curso = pedirCadena("Curso: (minuscula)");
		System.out.println("Sexo: ");
		String Sexo = pedirCadena("[M] Masculino   [F] Femenino");
		int Edad = pedirEntero("Edad: ");
		System.out.println("Deporte:");
		String Deporte = pedirCadena("[1]Futbol   [2]Futbol Sala   [3]Básquet   [4]Badminton    [5]Vóley Playa");

		// Añadimos todos los campos al ArrayList de Alumno y lo creamos
		Alumno a = new Alumno(Nombre, Apellidos, Curso, Sexo, Edad, Deporte);
		alumnos.add(a);
		System.out.println("Alumno Inscrito");
		/*
		 * Con este bloque condicional guardamos los datos del alumno en el fichero del
		 * deporte elegido con los metodos de registrarDeporte.
		 */

		if (a.Deporte.equals("futbol") || a.Deporte.equals("1")) {
			registrarFutbol(Nombre, Apellidos, Curso, Sexo, Edad);

		} else if (a.Deporte.equals("futbol sala") || a.Deporte.equals("2")) {
			registrarFutbolSala(Nombre, Apellidos, Curso, Sexo, Edad);
		} else if (a.Deporte.equals("basquet") || a.Deporte.equals("3")) {
			registrarBasquet(Nombre, Apellidos, Curso, Sexo, Edad);
		} else if (a.Deporte.equals("badminton") || a.Deporte.equals("4")) {
			registrarBadminton(Nombre, Apellidos, Curso, Sexo, Edad);
		} else if (a.Deporte.equals("voley playa") || a.Deporte.equals("5")) {
			registrarVoley(Nombre, Apellidos, Curso, Sexo, Edad);
		}

	}
	// *********************************************************************************************

	////////////////////////////////////////////////// (1.2) GUARDADO DE DATOS EN EL
	////////////////////////////////////////////////// FICHERO DE
	////////////////////////////////////////////////// DEPORTE//////////////////////////////////////////////////

	public static void registrarBasquet(String Nombre, String Apellidos, String Curso, String Sexo, int Edad)
			throws IOException {
		// Si el fichero de basquet no existe, lo crea
		if (!Basquet.exists()) {
			Basquet.createNewFile();

		}
		// Si existe sobreescribe los datos del usuario inscrito
		if (Basquet.exists()) {
			String Jugador = Nombre + "," + Apellidos + "," + Curso + "," + Sexo + "," + Edad;

			System.out.println(Jugador);

			// Con el FileWriter y el BufferedWriter podemos sobreescribir los datos
			FileWriter fw = new FileWriter(Basquet, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(Jugador);
			bw.newLine();

			bw.close();

		}
	}

	// Todos los metodos de GUARDADO DE DATOS EN EL FICHERO DE DEPORTE hacen la
	// misma función.
	public static void registrarFutbolSala(String Nombre, String Apellidos, String Curso, String Sexo, int Edad)
			throws IOException {

		if (!FutbolSala.exists()) {
			FutbolSala.createNewFile();
			System.out.println("El fichero de futbol sala ha sido creado");
		}

		if (FutbolSala.exists()) {
			String Jugador = Nombre + "," + Apellidos + "," + Curso + "," + Sexo + "," + Edad;

			FileWriter fw = new FileWriter(FutbolSala, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(Jugador);
			bw.newLine();

			bw.close();

		}
	}

	// Todos los metodos de GUARDADO DE DATOS EN EL FICHERO DE DEPORTE hacen la
	// misma función.
	public static void registrarVoley(String Nombre, String Apellidos, String Curso, String Sexo, int Edad)
			throws IOException {

		if (!VoleyPlaya.exists()) {
			VoleyPlaya.createNewFile();
			System.out.println("El fichero de voley playa ha sido creado");

		}

		if (VoleyPlaya.exists()) {
			String Jugador = Nombre + "," + Apellidos + "," + Curso + "," + Sexo + "," + Edad;

			FileWriter fw = new FileWriter(VoleyPlaya, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(Jugador);
			bw.newLine();

			bw.close();

		}

	}

	// Todos los metodos de GUARDADO DE DATOS EN EL FICHERO DE DEPORTE hacen la
	// misma función.
	public static void registrarBadminton(String Nombre, String Apellidos, String Curso, String Sexo, int Edad)
			throws IOException {

		if (!Badminton.exists()) {
			Badminton.createNewFile();
			System.out.println("El fichero de badminton ha sido creado");

		}

		if (Badminton.exists()) {
			String Jugador = Nombre + "," + Apellidos + "," + Curso + "," + Sexo + "," + Edad;

			FileWriter fw = new FileWriter(Badminton, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(Jugador);
			bw.newLine();

			bw.close();

		}

	}

	// Todos los metodos de GUARDADO DE DATOS EN EL FICHERO DE DEPORTE hacen la
	// misma función.
	public static void registrarFutbol(String Nombre, String Apellidos, String Curso, String Sexo, int Edad)
			throws IOException {
		if (!Futbol.exists()) {
			Futbol.createNewFile();
			System.out.println("El fichero de futbol ha sido creado");
		}

		if (Futbol.exists()) {
			String Jugador = Nombre + "," + Apellidos + "," + Curso + "," + Sexo + "," + Edad;

			FileWriter fw = new FileWriter(Futbol, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(Jugador);
			bw.newLine();

			bw.close();

		}
	}

	/***********************************************************************************************************************/

	/////////////////////////////////////////////// (2)CONSULTAR LOS ALUMNOS POR
	/////////////////////////////////////////////// CURSO/////////////////////////////////////////////////////

	public static void mostrarAlumnos() throws IOException {

		System.out.println("Elige un curso");
		System.out.println("[1]1BAT1");
		System.out.println("[2]1BAT2");
		System.out.println("[3]DAM2T1");
		System.out.println("[4]DAM2T2");
		System.out.println("[5]DAW1M");
		System.out.println("[6]DAW2M");

		String cursillo = br.readLine();

		/*
		 * Recogemos el curso a mostrar y recorre el campo de curso en el ArrayList
		 * buscando los alumnos con el curso equivalent y los muestra por pantalla
		 */

		if (cursillo.equals("1") || cursillo.equalsIgnoreCase("1bat1")) {

			for (Alumno a : alumnos) {
				if (a.getCurso().equals("1bat1")) {
					System.out.println(a);
				}
			}
		}
		// Cada uno de estos condicionales hace la misma funcionalidad variando el
		// curso.
		if (cursillo.equals("2") || cursillo.equalsIgnoreCase("1bat2")) {

			for (Alumno a : alumnos) {
				if (a.getCurso().equals("1bat2")) {
					System.out.println(a);
				}
			}
		}

		if (cursillo.equals("3") || cursillo.equalsIgnoreCase("dam2t1")) {

			for (Alumno a : alumnos) {
				if (a.getCurso().equalsIgnoreCase("dam2t1")) {
					System.out.println(a);
				}
			}
		}

		if (cursillo.equals("4") || cursillo.equalsIgnoreCase("dam2t2")) {

			for (Alumno a : alumnos) {
				if (a.getCurso().equalsIgnoreCase("dam2t2")) {
					System.out.println(a);
				}
			}
		}

		if (cursillo.equals("5") || cursillo.equalsIgnoreCase("daw1m")) {

			for (Alumno a : alumnos) {
				if (a.getCurso().equalsIgnoreCase("daw1m")) {

					System.out.println(a);
				}
			}
		}

		if (cursillo.equals("6") || cursillo.equalsIgnoreCase("daw2m")) {

			for (Alumno a : alumnos) {
				if (a.getCurso().equalsIgnoreCase("daw2m")) {
					System.out.println(a);
				}
			}
		}

	}

	/**********************************************************************************************************************/

	////////////////////////////////////////////////////// (3) CONSULTAR
	////////////////////////////////////////////////////// INCIDENCIAS/////////////////////////////////////////////////////

	// Este metodo nos va a calcular las incidencias de este deporte en concreto

	public static void calcularFutbol() {

		int participantesFutbol = 0;

		// Recorremos el ArrayList incrementando la variable contra más alumnos
		// alistados de a futbol vaya recogiendo del ArrayList
		for (Alumno a : alumnos) {
			if (a.getDeportes().equalsIgnoreCase("futbol") || a.getDeportes().equals("1")) {
				participantesFutbol++;

			}

		}
		// Dependiendo del número de participantes nos va a avisar de la cantidad de
		// jugadores que necesitemos para completar los equipos
		if (participantesFutbol != 22) {

			System.out.println("Incidencia Futbol: Tienes: " + participantesFutbol + " jugadores, y necesitas 22");
		}

	}

	// Cada uno de estos metodos de la sección CONSULTAR INCIDENCIAS hace los mismo
	// variando el deporte.
	public static void calcularFutbol_Sala() {

		int participantesFutbolSala = 0;

		for (Alumno a : alumnos) {
			if (a.getDeportes().equalsIgnoreCase("futbol sala") || a.getDeportes().equals("2")) {
				participantesFutbolSala++;

			}

		}
		if (participantesFutbolSala != 10) {

			System.out.println(
					"Incidencia Futbol Sala: Tienes: " + participantesFutbolSala + " jugadores, y necesitas 10");
		}

	}

	public static void calcularBasquet() {

		int participantesBasquet = 0;

		for (Alumno a : alumnos) {
			if (a.getDeportes().equalsIgnoreCase("basquet") || a.getDeportes().equals("3")) {
				participantesBasquet++;

			}

		}
		if (participantesBasquet < 10) {

			System.out.println("Incidencia Basquet: Tienes: " + participantesBasquet + " jugadores, y necesitas 10");
		}

	}

	public static void calcularBadminton() {

		int participantesBadminton = 0;

		for (Alumno a : alumnos) {
			if (a.getDeportes().equalsIgnoreCase("badminton") || a.getDeportes().equals("4")) {
				participantesBadminton++;

			}

		}
		if (participantesBadminton < 2 || participantesBadminton % 2 != 0) {

			System.out.println("Incidencia Badminton: Tienes: " + participantesBadminton
					+ " jugadores, y necesitas 2 o que sean pares");
		}

	}

	public static void calcularVoley() {

		int participantesVoley = 0;

		for (Alumno a : alumnos) {
			if (a.getDeportes().equalsIgnoreCase("voley playa") || a.getDeportes().equals("5")
					|| a.getDeportes().equalsIgnoreCase("voley")) {
				participantesVoley++;

			}

		}
		if (participantesVoley < 4 || participantesVoley % 2 != 0) {

			System.out.println(
					"Incidencia Voley: Tienes: " + participantesVoley + " jugadores, y necesitas 4 o que sean pares");
		}

	}

	/*********************************************************************************************************************/

	///////////////////////////////////////////////// (4) ELIMINAR INSCRIPCIÓN DE
	///////////////////////////////////////////////// ALUMNO//////////////////////////////////////////////////

	// El siguiente metodo nos permite eliminar un usuario, recogiendo su nombre y
	// apellido, del ArrayList y sobreescriviendo el fichero de deporte.
	public static void eliminarAlumno() throws IOException {

		String nombreAlumno = pedirCadena("Nombre del Alumno");

		String apellidosAlumno = pedirCadena("Apellidos del Alumno");
		String deporteAlumnoEliminado = ("");
		String confirmacion = pedirCadena("¿Seguro que desea eliminar el alumno?");
		
		if(confirmacion.equalsIgnoreCase("y")) {

		// Con este bucle vamos recorriendo todo el ArrayList asignando una posicion a
		// cada alumno.

		for (int i = 0; i < alumnos.size(); i++) {

			Alumno a = alumnos.get(i);
			// Filtramos el alumno por su nombre y su apellido idéntico y lo eliminamos del
			// array.
			if (a.getNombre().equalsIgnoreCase(nombreAlumno) && (a.getApellidos().equalsIgnoreCase(apellidosAlumno))) {

				alumnos.remove(a);
				// Recogemos el deporte del cual ha sido eliminado.
				deporteAlumnoEliminado = a.getDeportes();

			}

		}

		// Con esta condicional marcamos si el alumno existe o no.
		if (deporteAlumnoEliminado.equals("")) {

			System.out.println("El Alumno no existe");

		} else {

			/*
			 * En caso de que el String este rellenado con algún deporte, vamos a
			 * sobreescrivir el fichero del deporte de nuevo pero esta vez sin el alumno
			 */
			FileWriter fr = null;

			switch (deporteAlumnoEliminado.toLowerCase()) {

			case "futbol":

				fr = new FileWriter(Futbol);

				break;

			case "futbol sala":
				fr = new FileWriter(FutbolSala);

				break;

			case "basquet":

				fr = new FileWriter(Basquet);
				break;

			case "badminton":

				fr = new FileWriter(Badminton);
				break;

			case "voley":

				fr = new FileWriter(VoleyPlaya);
				break;

			}

			BufferedWriter bw = new BufferedWriter(fr);

			for (Alumno a : alumnos) {

				// Recorremos el ArrayList cada alumno que pertenezca a esa variable de deporte
				// será sobreescrito en el fichero del respectivo deporte.
				if (a.getDeportes().equalsIgnoreCase(deporteAlumnoEliminado)) {

					String Jugador = a.getNombre() + "," + a.getApellidos() + "," + a.getCurso() + "," + a.getSexo()
							+ "," + a.getEdad();

					bw.write(Jugador);
					bw.newLine();

				}

			}
			bw.close();
		}
	}
	}

	/***************************************************************************************************************/

	////////////////////////////////////////////////////// (5) VER LISTADO DE TODAS
	////////////////////////////////////////////////////// LAS
	////////////////////////////////////////////////////// INSCRIPCIONES/////////////////////////////////////////////////

	// Con este metodo nos va a aparecer un listado de todas las inscripciones de
	// cada deporte correspondiente agrupadas, y ordenadas alfabéticamente.

	public static void listadoInscripciones() {
		//Guardamos en el arraylist de cada deporte los alumnos que esten inscritos a ese deporte
		ArrayList<Alumno> ListaFutbol = new ArrayList<>();
		ArrayList<Alumno> ListaBasquet = new ArrayList<>();
		ArrayList<Alumno> ListaBadminton = new ArrayList<>();
		ArrayList<Alumno> ListaFutbolSala = new ArrayList<>();
		ArrayList<Alumno> ListaVoley = new ArrayList<>();

		System.out.println("INSCRIPCIONES DE FUTBOL");
		System.out.println("***************************");

		Collections.sort(alumnos);
		for (Alumno a : alumnos) {
			if (a.getDeportes().equalsIgnoreCase("futbol")) {
				ListaFutbol.add(a);
			}
				else if(a.getDeportes().equalsIgnoreCase("futbol sala")) {
			
					ListaFutbolSala.add(a);
					
				}else if(a.getDeportes().equalsIgnoreCase("badminton")) {
					
					ListaBadminton.add(a);
					
				}else if(a.getDeportes().equalsIgnoreCase("basquet")) {
					ListaBasquet.add(a);
					
				}else if(a.getDeportes().equalsIgnoreCase("voley")|| a.getDeportes().equalsIgnoreCase("voley playa"))
//				Comparator<Alumno> comp = new Comparator<Alumno>() {
				{
					
					ListaVoley.add(a);
				};
				//
		}
				//Los vamos mostrando por pantalla
				System.out.println("**************Futbol**************");
				System.out.println("");
				for(Alumno a: ListaFutbol) {
					System.out.println(a);
					
				}
				System.out.println("___________________________________________");
				System.out.println("");
				System.out.println("**************Futbol Sala**************");
				System.out.println("");

				for(Alumno a: ListaFutbolSala) {
					System.out.println(a);
					
				}
				System.out.println("___________________________________________");
				System.out.println("");
				System.out.println("**************Badminton**************");
				System.out.println("");
				
				
				for(Alumno a: ListaBadminton) {
					System.out.println(a);
					
				}
				
				System.out.println("___________________________________________");
				System.out.println("");
				System.out.println("**************Baloncesto**************");
				System.out.println("");
				
				for(Alumno a: ListaBasquet) {
					System.out.println(a);
					
				}
				System.out.println("___________________________________________");
				System.out.println("");
				System.out.println("**************Voley Playa**************");
				System.out.println("");

				for(Alumno a: ListaVoley) {
					System.out.println(a);
					
				}
				System.out.println("___________________________________________");
				System.out.println("");
		}

	

	/**************************************************************************************************************/

	///////////////////////////////////////////// (6) ANULAR DEPORTE DE LAS
	///////////////////////////////////////////// JORNADAS/////////////////////////////////////////////////

	// Este metodo nos muestra un menú para elegir el deporte que queramos eliminar.
	public static void menuAnularDeporte() {
		System.out.println("Elige el deporte que quieres eliminar");
		String Deporte = pedirCadena("[1]Futbol   [2]Futbol Sala   [3]Básquet   [4]Badminton    [5]Vóley Playa");

		if (Deporte.equals("futbol") || Deporte.equals("1")) {
			anularFutbol();
		}
		if (Deporte.equals("futbol sala") || Deporte.equals("2")) {
			anularFutbolSala();
		}
		if (Deporte.equals("basquet") || Deporte.equals("3")) {
			anularBasquet();
		}
		if (Deporte.equals("badminton") || Deporte.equals("4")) {
			anularBadminton();
		}
		if (Deporte.equals("voley playa") || Deporte.equals("voley") || Deporte.equals("5")) {
			anularVoley();
		}

	}

	// Cada uno de estos metodos nos elimina el fichero creado y nos muestra por
	// pantalla los alumnos que estaban previamente inscritos.

	public static void anularFutbol() {
		
		
		
	
		System.out.println("Los alumnos alistados a Futbol son:");


	
		if (Futbol.exists()) {

			for(int i = 0; i < alumnos.size(); i++){
				Alumno a = alumnos.get(i);
				
				if(a.getDeportes().equalsIgnoreCase("Futbol")) {
					System.out.println(a);
					alumnos.remove(a);
				}
		
			}
			Futbol.delete();
		}else{
			System.out.println("El fichero no existe");
		}
		

	}

	
	
	// Todos los metodos de la sección ANULAR DEPORTE DE LAS JORNADAS hace lo mismo
	// con su respectivo deporte.
	public static void anularFutbolSala() {
		System.out.println("Los alumnos alistados a Futbol Sala son:");


		if (FutbolSala.exists()) {

			for(int i = 0; i < alumnos.size(); i++){
				Alumno a = alumnos.get(i);
				
				if(a.getDeportes().equalsIgnoreCase("Futbol sala")) {
					System.out.println(a);
					alumnos.remove(a);
				}
		
			}
			FutbolSala.delete();
		}else{
			System.out.println("El fichero no existe");
		}
		
	}

	public static void anularBasquet() {
		System.out.println("Los alumnos alistados a Basquet son:");


		if (Basquet.exists()) {

			for(int i = 0; i < alumnos.size(); i++){
				Alumno a = alumnos.get(i);
				
				if(a.getDeportes().equalsIgnoreCase("basquet")) {
					System.out.println(a);
					alumnos.remove(a);
				}
		
			}
			Basquet.delete();
		}else{
			System.out.println("El fichero no existe");
		}
		

	}

	public static void anularBadminton() {
		System.out.println("Los alumnos alistados a Badminton son:");

		if (Badminton.exists()) {

			for(int i = 0; i < alumnos.size(); i++){
				Alumno a = alumnos.get(i);
				
				if(a.getDeportes().equalsIgnoreCase("badminton")) {
					System.out.println(a);
					alumnos.remove(a);
				}
		
			}
			Badminton.delete();
		}else{
			System.out.println("El fichero no existe");
		}
		

	}

	public static void anularVoley() {
		System.out.println("Los alumnos alistados a Voley son:");


		if (VoleyPlaya.exists()) {

			for(int i = 0; i < alumnos.size(); i++){
				Alumno a = alumnos.get(i);
				
				if(a.getDeportes().equalsIgnoreCase("voley")) {
					System.out.println(a);
					alumnos.remove(a);
				}
		
			}
			VoleyPlaya.delete();
		}else{
			System.out.println("El fichero no existe");
		}
		

	}

	/************************************************************************************************************/

	///////////////////////////////////////////////// RECOGIDA DE VALORES AL INICIAR
	///////////////////////////////////////////////// EL
	///////////////////////////////////////////////// PROGRAMA//////////////////////////////////////////////

	// Este metodo esta compuesto de submetodos en los cuales haran una función
	// dependiendo de si los ficheros de deportes existen
	public static void recogerValores() throws IOException {
		if (Futbol.exists()) {
			recogerValorFutbol();

		}
		if (Basquet.exists()) {
			recogerValorBasquet();
		}
		if (VoleyPlaya.exists()) {
			recogerValorVoley();
		}
		if (Badminton.exists()) {
			recogerValorBadminton();
		}
		if (FutbolSala.exists()) {
			recogerValorFutbolSala();

		}
	}

	// Este metodo nos permite buscar en la ruta de cada usuario si se encuentran
	// los ficheros de deporte,
	// en caso de que los encuentre va a guardarlos en el ArrayList de alumnos para
	// poder interaccionar más tarde con ellos.
	public static void recogerValorFutbolSala() throws NumberFormatException, IOException {
		// Con la ruta user.dir y el separador nos permite identificar la ruta del
		// programa para buscar ficheros existentes.
		String rutaActual = System.getProperty("user.dir");
		String separador = File.separator;
		String rutaFichero = rutaActual + separador + "futbol_sala.txt";
		File f = new File(rutaFichero);

		// Si la ruta del fichero con el fichero existe se procede a leer dato por dato.
		if (f.exists()) {
			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				// Guardamos los datos en un Array de strings.
				String[] datos = linea.split(",");
				String nombre = datos[0];
				String apellido = datos[1];
				String curso = datos[2];
				String sexo = datos[3];
				int edad = Integer.parseInt(datos[4]);
				// Luego pasamos los campos de ese Array de Strings al ArrayList de alumnos.
				Alumno a = new Alumno(nombre, apellido, curso, sexo, edad, "futbol sala");
				alumnos.add(a);

			}

			if (fr != null) {
				fr.close();
			}

		}
	}

	// Todos los metodos de la sección RECOGIDA DE DATOS hacen la misma
	// funcionalidad variando en el deporte
	public static void recogerValorBadminton() throws NumberFormatException, IOException {
		String rutaActual = System.getProperty("user.dir");
		String separador = File.separator;
		String rutaFichero = rutaActual + separador + "badminton.txt";
		File f = new File(rutaFichero);
		if (f.exists()) {
			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(",");
				String nombre = datos[0];
				String apellido = datos[1];
				String curso = datos[2];
				String sexo = datos[3];
				int edad = Integer.parseInt(datos[4]);

				Alumno a = new Alumno(nombre, apellido, curso, sexo, edad, "badminton");
				alumnos.add(a);

			}

			if (fr != null) {
				fr.close();
			}

		}
	}

	public static void recogerValorVoley() throws NumberFormatException, IOException {
		String rutaActual = System.getProperty("user.dir");
		String separador = File.separator;
		String rutaFichero = rutaActual + separador + "voley_playa.txt";
		File f = new File(rutaFichero);

		if (f.exists()) {
			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(",");
				String nombre = datos[0];
				String apellido = datos[1];
				String curso = datos[2];
				String sexo = datos[3];
				int edad = Integer.parseInt(datos[4]);

				Alumno a = new Alumno(nombre, apellido, curso, sexo, edad, "voley");
				alumnos.add(a);

			}

			if (fr != null) {
				fr.close();
			}

		}
	}

	public static void recogerValorBasquet() throws NumberFormatException, IOException {
		String rutaActual = System.getProperty("user.dir");
		String separador = File.separator;
		String rutaFichero = rutaActual + separador + "basket.txt";
		File f = new File(rutaFichero);
		if (f.exists()) {
			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(",");
				String nombre = datos[0];
				String apellido = datos[1];
				String curso = datos[2];
				String sexo = datos[3];
				int edad = Integer.parseInt(datos[4]);

				Alumno a = new Alumno(nombre, apellido, curso, sexo, edad, "basquet");
				alumnos.add(a);

			}

			if (fr != null) {
				fr.close();
			}

		}
	}

	public static void recogerValorFutbol() throws NumberFormatException, IOException {

		String rutaActual = System.getProperty("user.dir");
		String separador = File.separator;
		String rutaFichero = rutaActual + separador + "futbol.txt";
		File f = new File(rutaFichero);
		if (f.exists()) {
			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(",");
				String nombre = datos[0];
				String apellido = datos[1];
				String curso = datos[2];
				String sexo = datos[3];
				int edad = Integer.parseInt(datos[4]);

				Alumno a = new Alumno(nombre, apellido, curso, sexo, edad, "futbol");
				alumnos.add(a);

			}

			if (fr != null) {
				fr.close();
			}

		}
	}

	/******************************************************************************************************/

	public static void mostrarmenu() {

		System.out.println("**************************");
		System.out.println("[1] Inscribir Alumno");
		System.out.println("[2] Consultar Alumno");
		System.out.println("[3] Incidencias");
		System.out.println("[4] Eliminar Inscripción");
		System.out.println("[5] Listado de Inscripciones");
		System.out.println("[6] Anular Deporte");
		System.out.println("[7] Salir");
	}

	public static String pedirCadena(String texto) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cadena = "";
		do {
			try {
				System.out.println(texto);
				cadena = br.readLine();
				if (cadena.equals("")) {
					System.out.println("No se puede dejar el campo en blanco.");
				}
			} catch (IOException ex) {
				System.out.println("Error de entrada / salida.");
			}
		} while (cadena.equals(""));
		return cadena;
	}

	public static int pedirEntero(String texto) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = 0;
		boolean error;
		do {
			try {
				System.out.println(texto);
				num = Integer.parseInt(br.readLine());
				error = false;
			} catch (IOException ex) {
				System.out.println("Error de entrada / salida.");
				error = true;
			} catch (NumberFormatException ex) {
				System.out.println("Debes introducir un número entero.");
				error = true;
			}
		} while (error);
		return num;
	}

}
