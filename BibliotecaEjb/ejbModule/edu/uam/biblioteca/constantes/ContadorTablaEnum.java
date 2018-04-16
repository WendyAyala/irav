/**
 * 
 */
package edu.uam.biblioteca.constantes;

public enum ContadorTablaEnum  {

	USUARIO("USUARIO"),EDITORIAL("EDITORIAL"),AUTOR("AUTOR"),MATERIAL("MATERIAL"),LIBRO("LIBRO"),PRESTAMO("PRESTAMO"),MULTIMEDIA("MULTIMEDIA");

	private String nombreTabla;

	private ContadorTablaEnum(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

}
