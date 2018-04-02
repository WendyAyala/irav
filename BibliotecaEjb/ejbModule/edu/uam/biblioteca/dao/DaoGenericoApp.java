/**
 * 
 */
package edu.uam.biblioteca.dao;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 * @author wayala
 *
 */
public abstract class DaoGenericoApp<T extends Serializable, PK extends Serializable> extends DaoGenericoEjb<T, PK> {

	public DaoGenericoApp(Class<T> type) {
		super(type);
	}
	
	/**
	 * Unidad de persistencia
	 */
	@PersistenceContext(unitName = "bibliotecaPU")
	private EntityManager em;
	/**
	 * datasource inyectado, se obtiene del contenedor EJB
	 */
	@Resource(mappedName = "java:jboss/datasources/bibliotecaDS")
	private DataSource dataSource;
	
	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

}
