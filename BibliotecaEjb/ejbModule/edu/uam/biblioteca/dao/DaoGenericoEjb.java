/**
 * 
 */
package edu.uam.biblioteca.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import edu.uam.biblioteca.exception.DaoException;

/**
 * @author wayala
 *
 */
public abstract class DaoGenericoEjb<T extends Serializable, PK extends Serializable> {

	private final Class<T> type;
	protected Query query;

	/**
	 * Constructor, mediante este se indica el tipo de clase por el cual se
	 * realizara el acceso a datos
	 *
	 * @param type
	 *            tipo de objeto del DAO.
	 */
	public DaoGenericoEjb(Class<T> type) {
		this.type = type;
	}

	/**
	 * Permite recuperar el objeto de consulta
	 *
	 * @return
	 */
	public Query getQuery() {
		return query;
	}

	/**
	 *
	 * @param query
	 */
	public void setQuery(Query query) {
		this.query = query;
	}

	public void guardar(T o) throws DaoException {
		this.getEntityManager().persist(o);

	}

	/**
	 * Metodo Generico que realiza un find by PK de una clase de Entity
	 *
	 * @param <T>
	 *            Objecto sobre el que se raliza la operacion
	 * @param primaryKey
	 *            de la clase
	 * @return <T> Entity encontrada
	 */
	public T findByPK(PK primaryKey) {
		T entity = this.getEntityManager().find(type, primaryKey);
		return entity;
	}

	public void update(T o) throws DaoException {
		this.getEntityManager().merge(o);
	}

	public void refresh(T arg0) {
		this.getEntityManager().refresh(arg0);
	}

	public void remove(T o) throws DaoException {
		this.getEntityManager().remove(this.getEntityManager().merge(o));
	}

	/**
	 * Permite crear una cadena de consulta de todos los registros, si existe el
	 * campo de estado lo usara en la consulta
	 *
	 * @return {@link String} con la cadena de consulta JPA
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String condicion, Map<String, String> sortFields) throws DaoException {
		String sql = "select p from ".concat(type.getSimpleName()).concat(" p ");
		if (condicion != null) {
			sql = sql.concat(" WHERE ").concat(condicion);
		}
		if (sortFields != null) {
			sql = sql.concat(crearOrdenamiento("p", sortFields));
		}
		return this.getEntityManager().createQuery(sql).getResultList();

	}

	/**
	 * @return permite obtener el {@link EntityManager} desde el contenedor
	 */
	public abstract EntityManager getEntityManager();

	/**
	 * Permite recupera el datasorce asociado, se lo recupera desde el contenedor
	 *
	 * @return {@link DataSource}
	 */
	public abstract DataSource getDataSource();

	/**
	 * Crea la sentencia de orden para el sql
	 *
	 * @param entity
	 * @param sortFields
	 * @return
	 */
	protected String crearOrdenamiento(String entity, Map<String, String> sortFields) {
		if (sortFields == null || sortFields.entrySet().isEmpty()) {
			return "";
		}
		Set<Map.Entry<String, String>> entrySet = sortFields.entrySet();
		StringBuilder result = new StringBuilder(" order by ");

		int i = 1;
		for (Map.Entry<String, String> e : entrySet) {
			result.append(entity);
			result.append(".");
			result.append(e.getKey());
			result.append(" ");
			result.append(e.getValue());
			if (entrySet.size() > i++) {
				result.append(", ");
			}
		}
		return result.toString();
	}

}
