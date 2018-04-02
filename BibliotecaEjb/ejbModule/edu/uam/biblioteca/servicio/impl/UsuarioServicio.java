package edu.uam.biblioteca.servicio.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.dao.impl.UsuarioDao;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Usuario;
import edu.uam.biblioteca.servicio.ContadorPkServicio;
import edu.uam.biblioteca.servicio.ServicioApp;

@Stateless
public class UsuarioServicio extends ServicioApp<Usuario, Integer> {

	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private ContadorPkServicio contadorPkServicio;

	public Usuario findByPK(int usrId) throws DaoException {
		try {
			return usuarioDao.findByPK(usrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DaoGenericoApp<Usuario, Integer> getDao() {
		return usuarioDao;
	}
	
	@Override
	public void save(Usuario o) throws DaoException {
		if (o.getUsrId() == null) {
			try {
				o.setUsrId(contadorPkServicio.generarContadorTabla(ContadorTablaEnum.USUARIO));
				super.save(o);
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}
}
