package edu.uam.biblioteca.servicio.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.constantes.EstadoPrestamoEnum;
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
	
	public boolean lend(Prestamo o) throws DaoException {
		if (o.getPreId() == null) {
			try {
				o.setMaterial(o.getLibro());
				if(availableBooks(o.getMaterial().getMatId())==0)
					return false;
				else {
					o.setPreId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.PRESTAMO));
					o.setPreFechaPrestamo(new Date());
					o.setPreEstado(EstadoPrestamoEnum.PRESTADO.getValor());
				//	
				}
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
		
		return false;
	}
	
	public void restore(Prestamo prestamo) throws DaoException{
		prestamo.setPreEstado(EstadoPrestamoEnum.ENTREGADO.getValor());
		prestamoDao.update(prestamo);
		
	}
	
	public int availableBooks(String bookId) throws DaoException{
		return prestamoDao.availableBooks(bookId);
	}
	
	@Override
	public void update(Prestamo o) throws DaoException {
		prestamoDao.update(o);
	}
}
