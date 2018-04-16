package edu.uam.biblioteca.servicio.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.dao.impl.MultimediaDao;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Multimedia;
import edu.uam.biblioteca.servicio.ContadorPkServicio;
import edu.uam.biblioteca.servicio.ServicioApp;

@Stateless
public class MultimediaServicio extends ServicioApp<Multimedia, String> {

	@Inject
	private MultimediaDao multimediaDao;
	
	@Inject
	private ContadorPkServicio contadorPkServicio;
	
	public Multimedia findByPK(String usrId) throws DaoException {
		try {
			return multimediaDao.findByPK(usrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoGenericoApp<Multimedia, String> getDao() {
		return multimediaDao;
	}
	
	@Override
	public void save(Multimedia o) throws DaoException {
		if (o.getMatId() == null) {
			try {
				o.setMatId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.MATERIAL));
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}
}
