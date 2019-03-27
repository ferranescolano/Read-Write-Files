public class Alumno implements Comparable<Alumno> {

	String Nombre;
	String Apellidos;
	String Curso;
	String Sexo;
	int Edad;
	String Deporte;
	
	
	public Alumno() {
		
	}
	
	public Alumno(
			 String Nombre,
			 String Apellidos,
			 String Curso,
			 String Sexo,
			 int Edad,
			 String Deporte) {
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Curso = Curso;
		this.Sexo = Sexo;
		this.Edad = Edad;
		this.Deporte = Deporte;
		
	}
	

	public String getNombre() {
		return Nombre;
	}
	
	public String getApellidos() {
		return Apellidos;
	}
	public String getCurso() {
		return Curso;
	}
	public String getSexo() {
		return Sexo;
	}
	public int getEdad() {
		return Edad;
	}
	public String getDeportes() {
		return Deporte;
	}

	@Override
	public String toString() { 
		return Nombre + ","+ Apellidos + "," + Curso + "," + Sexo
				+ "," + Edad + "," + Deporte;
	}

	@Override
	public int compareTo(Alumno a) {
		// TODO Auto-generated method stub
		return Nombre.compareToIgnoreCase(a.getNombre());
	}
	
	
	
	

	
}
