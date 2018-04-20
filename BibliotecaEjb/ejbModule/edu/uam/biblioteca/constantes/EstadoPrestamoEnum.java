package edu.uam.biblioteca.constantes;

public enum EstadoPrestamoEnum {
	
	ENTREGADO("Entregado"), ATRASADO("Atrasado"), PRESTADO("Prestado");
	
	private String valor;

	
	private EstadoPrestamoEnum(String valor) {
		this.valor = valor;
	}


	public String getValor() {
		return valor;
	}
	
	

}
