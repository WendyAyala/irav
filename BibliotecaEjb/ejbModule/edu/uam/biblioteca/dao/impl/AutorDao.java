/**
 * 
 */
package edu.uam.biblioteca.dao.impl;

import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.persistencia.Autor;

/**
 * @author wayala
 * 
 */
public class AutorDao extends DaoGenericoApp<Autor, String> {

	public AutorDao() {
		super(Autor.class);

	}


}
