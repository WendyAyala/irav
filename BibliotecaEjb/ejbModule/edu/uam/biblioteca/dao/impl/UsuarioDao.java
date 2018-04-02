/**
 * 
 */
package edu.uam.biblioteca.dao.impl;

import java.util.List;

import javax.persistence.Query;

import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Usuario;

/**
 * @author wayala
 * 
 */
public class UsuarioDao extends DaoGenericoApp<Usuario, Integer> {

	public UsuarioDao() {
		super(Usuario.class);

	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() throws DaoException {
		Query q = getEntityManager().createQuery("SELECT p FROM Usuario p  ");
		return q.getResultList();

	}
	


}
