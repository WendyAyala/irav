package edu.uam.biblioteca.servicio.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.dao.impl.EditorialDao;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Editorial;
import edu.uam.biblioteca.servicio.ContadorPkServicio;
import edu.uam.biblioteca.servicio.ServicioApp;


@Stateless
public class EditorialServicio extends ServicioApp<Editorial, String> {

	@Inject
	private EditorialDao editorialDao;
	
	@Inject
	private ContadorPkServicio contadorPkServicio;

	public Editorial findByPK(String usrId) throws DaoException {
		try {
			return editorialDao.findByPK(usrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoGenericoApp<Editorial, String> getDao() {
		return editorialDao;
	}
	
	@Override
	public void save(Editorial o) throws DaoException {
		if (o.getEdiId() == null) {
			try {
				o.setEdiId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.EDITORIAL));
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}
}
