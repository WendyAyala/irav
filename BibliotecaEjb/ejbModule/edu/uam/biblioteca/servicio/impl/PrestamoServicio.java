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
	
	public boolean lend(Prestamo o) throws DaoException {
		if (o.getPreId() == null) {
			try {
				//TODO: verificar que se tenga la cantidad de libros disponible (no prestados) contar los prestados y sacar la diferencia de los existentes
				if(availableBooks(o.getMaterial().getMatId())==0)
					return false;
				else
					o.setPreId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.PRESTAMO));
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
		
		return false;
	}
	
	public void restore(Prestamo prestamo) throws DaoException{
		//TODO elimino el registro de prestamos o lo pongo en estado entregado
		
	}
	
	public int availableBooks(String bookId) throws DaoException{
		return 0;
	}
}
