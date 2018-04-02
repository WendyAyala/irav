package edu.uam.biblioteca.servicio.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.dao.impl.AutorDao;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Autor;
import edu.uam.biblioteca.servicio.ContadorPkServicio;
import edu.uam.biblioteca.servicio.ServicioApp;

@Stateless
public class AutorServicio extends ServicioApp<Autor, String> {

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private ContadorPkServicio contadorPkServicio;

	public Autor findByPK(String usrId) throws DaoException {
		try {
			return autorDao.findByPK(usrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoGenericoApp<Autor, String> getDao() {
		return autorDao;
	}
	
	@Override
	public void save(Autor o) throws DaoException {
		if (o.getAutId() == null) {
			try {
				o.setAutId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.AUTOR));
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}
}
