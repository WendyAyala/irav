package edu.uam.biblioteca.servicio.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.dao.impl.PrestamoDao;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Prestamo;
import edu.uam.biblioteca.servicio.ContadorPkServicio;
import edu.uam.biblioteca.servicio.ServicioApp;


@Stateless
public class PrestamoServicio extends ServicioApp<Prestamo, String> {

	@Inject
	private PrestamoDao prestamoDao;
	
	@Inject
	private ContadorPkServicio contadorPkServicio;

	public Prestamo findByPK(String usrId) throws DaoException {
		try {
			return prestamoDao.findByPK(usrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoGenericoApp<Prestamo, String> getDao() {
		return prestamoDao;
	}
	
	@Override
	public void save(Prestamo o) throws DaoException {
		if (o.getPreId() == null) {
			try {
				o.setPreId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.PRESTAMO));
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}
}
