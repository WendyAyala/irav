package edu.uam.biblioteca.servicio.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.dao.impl.LibroDao;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Libro;
import edu.uam.biblioteca.persistencia.Material;
import edu.uam.biblioteca.servicio.ContadorPkServicio;
import edu.uam.biblioteca.servicio.ServicioApp;

@Stateless
public class LibroServicio extends ServicioApp<Libro, String> {

	@Inject
	private LibroDao libroDao;
	
	@Inject
	private ContadorPkServicio contadorPkServicio;

	public Material findByPK(String usrId) throws DaoException {
		try {
			return libroDao.findByPK(usrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoGenericoApp<Libro, String> getDao() {
		return libroDao;
	}
	
	@Override
	public void save(Libro o) throws DaoException {
		if (o.getMatId() == null) {
			try {
				o.setMatId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.MATERIAL));
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}
	
	public List<Libro>getAll() throws DaoException{
		return libroDao.getAll();
	}
}
