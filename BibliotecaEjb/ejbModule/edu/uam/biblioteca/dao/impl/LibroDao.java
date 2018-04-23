/**
 * 
 */
package edu.uam.biblioteca.dao.impl;

import java.util.List;

import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Libro;

/**
 * @author wayala
 * 
 */
public class LibroDao extends DaoGenericoApp<Libro, String> {
	

	public LibroDao() {
		super(Libro.class);

	}
	
	@SuppressWarnings("unchecked")
	public List<Libro> getAll()throws DaoException{
		try {
			
			String sql =" Select p from Libro p";
			return this.getEntityManager().createQuery(sql).getResultList();
		} catch (Exception e) {
			throw new DaoException();
		}
		
	}


}
