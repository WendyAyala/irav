package edu.uam.biblioteca.servicio;

import java.io.Serializable;
import java.util.List;

import edu.uam.biblioteca.dao.DaoGenericoEjb;
import edu.uam.biblioteca.exception.DaoException;

public abstract class ServicioApp<T extends Serializable, PK extends Serializable> {

	public abstract DaoGenericoEjb<T, PK> getDao();

	/**
	 * @return
	 * @throws DaoException
	 */
	public List<T> getAll() throws DaoException {
			List<T> list = extracted();
			return list;
	}

	private List<T> extracted() throws DaoException {
		List<T> list=getDao().find(null, null);
		return list;
	}

	/**
	 * @param usuario
	 * @throws DaoException
	 */
	public void save(T o) throws DaoException {
			getDao().guardar(o);
	}

	/**
	 * @param Usuario
	 * @throws DaoException
	 */
	public void update(T o) throws DaoException {
			getDao().update(o);
	}

	/**
	 * @param Usuario
	 * @throws DaoException
	 */
	public void remove(T o) throws DaoException {
			getDao().remove(o);
	}

}
