package edu.uam.biblioteca.servicio.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.dao.impl.MaterialDao;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Material;
import edu.uam.biblioteca.servicio.ContadorPkServicio;
import edu.uam.biblioteca.servicio.ServicioApp;

@Stateless
public class MaterialServicio extends ServicioApp<Material, String> {

	@Inject
	private MaterialDao materialDao;
	
	@Inject
	private ContadorPkServicio contadorPkServicio;

	public Material findByPK(String usrId) throws DaoException {
		try {
			return materialDao.findByPK(usrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoGenericoApp<Material, String> getDao() {
		return materialDao;
	}
	
	@Override
	public void save(Material o) throws DaoException {
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
