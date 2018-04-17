package edu.uam.biblioteca.web.constantes;

import java.util.Arrays;
import java.util.List;

public enum GeneroLibroEnum {
	
	TERROR("Terror"), NOVELA("Novela");
	
	private String valor;

	private GeneroLibroEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public List<GeneroLibroEnum> getGeneroList() {
		return Arrays.asList(GeneroLibroEnum.values()) ;
		
	}
	

}
