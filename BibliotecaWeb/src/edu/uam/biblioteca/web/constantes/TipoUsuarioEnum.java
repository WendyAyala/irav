package edu.uam.biblioteca.web.constantes;

import java.util.Arrays;
import java.util.List;

public enum TipoUsuarioEnum {
	
	ESTUDIANTE("Estudiante"), ADMINISTRATIVO("Administrativo");
	
	private String valor;

	private TipoUsuarioEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static List<TipoUsuarioEnum> getTipoUsuarioList() {
		return Arrays.asList(TipoUsuarioEnum.values()) ;
		
	}

}
