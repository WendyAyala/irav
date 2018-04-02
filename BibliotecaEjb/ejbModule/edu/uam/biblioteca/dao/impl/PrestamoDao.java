/**
 * 
 */
package edu.uam.biblioteca.dao.impl;

import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.persistencia.Prestamo;

/**
 * @author wayala
 * 
 */
public class PrestamoDao extends DaoGenericoApp<Prestamo, String> {

	public PrestamoDao() {
		super(Prestamo.class);

	}


}
