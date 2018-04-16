package edu.uam.biblioteca.web.constantes;

public enum LibroEnum {
	
	ESTUDIANTE("Estudiante"), ADMINISTRATIVO("Administrativo");
	
	private String valor;

	private LibroEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	

}
