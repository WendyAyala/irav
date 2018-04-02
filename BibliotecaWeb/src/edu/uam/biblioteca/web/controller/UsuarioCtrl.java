/**
 * 
 */
package edu.uam.biblioteca.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import edu.uam.biblioteca.persistencia.Usuario;
import edu.uam.biblioteca.servicio.impl.UsuarioServicio;

@ManagedBean 
@ViewScoped
public class UsuarioCtrl {

	@EJB
	private UsuarioServicio usuarioServicio;

	private Usuario usuario;
	private List<Usuario> usuarioList;

	public UsuarioCtrl() {
	}

	public void save() {
		try {
			if (usuario.getUsrId() == null) {
				usuarioServicio.save(usuario);
			} else {
				usuarioServicio.update(usuario);
			}
			find();
		} catch (Exception e) {
			e.printStackTrace();
			//FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void edit() {
		
	}

	public void remove() {
		if (usuario != null) {
			try {
				usuarioServicio.remove(usuario);
				find();
			} catch (Throwable e) {
				e.printStackTrace();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
	}

	public void nuevo() {
		usuario = new Usuario();
	}

	public void find() {
		usuarioList = null;
		try {
			usuarioList = usuarioServicio.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	/**
	 * @return the usuario
	 */
	public Usuario getusuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setusuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

}
