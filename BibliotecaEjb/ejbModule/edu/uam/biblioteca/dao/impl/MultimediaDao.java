/**
 * 
 */
package edu.uam.biblioteca.dao.impl;

import java.util.List;

import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Multimedia;

/**
 * @author wayala
 * 
 */
public class MultimediaDao extends DaoGenericoApp<Multimedia, String> {

	public MultimediaDao() {
		super(Multimedia.class);

	}

	@SuppressWarnings("unchecked")
	public List<Multimedia> getAll()throws DaoException{
		try {
			
			String sql =" Select * from Multimedia";
			return this.getEntityManager().createNativeQuery(sql).getResultList();
		} catch (Exception e) {
			throw new DaoException();
		}
		
	}
	

}
