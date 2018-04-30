/**
 * 
 */
package edu.uam.biblioteca.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.bouncycastle.asn1.x509.qualified.TypeOfBiometricData;
import org.primefaces.context.RequestContext;

import edu.uam.biblioteca.persistencia.Libro;
import edu.uam.biblioteca.persistencia.Multimedia;
import edu.uam.biblioteca.persistencia.Prestamo;
import edu.uam.biblioteca.persistencia.Usuario;
import edu.uam.biblioteca.servicio.impl.LibroServicio;
import edu.uam.biblioteca.servicio.impl.MultimediaServicio;
import edu.uam.biblioteca.servicio.impl.PrestamoServicio;
import edu.uam.biblioteca.servicio.impl.UsuarioServicio;

@ManagedBean
@ViewScoped
public class PrestamoCtrl {

	@EJB
	private PrestamoServicio prestamoServicio;
	
	@EJB
	private LibroServicio libroServicio;
	
	@EJB
	private MultimediaServicio multimediaServicio;
	
	@EJB
	private UsuarioServicio usuarioServicio;
	
	private Prestamo prestamo;
	private List<Prestamo> prestamoList;
	private List<Libro> libroList;
	private List<Multimedia> multimediaList;
	private List<Usuario> usuarioList;
	private String fechaActual;
	private boolean libro;
	
	public PrestamoCtrl() {
	}

	@PostConstruct
	void init() {
		libroList = new ArrayList<>();
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy"); 
		fechaActual = dt.format(new Date());
		find();
	}

	public void save() {
		try {

			if (prestamo.getPreId()== null) {
				prestamoServicio.lend(prestamo);
			} else {
				prestamoServicio.update(prestamo);
			}

			find();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}


	public void restore() {
		if (prestamo != null) {
			try {
				prestamoServicio.restore(prestamo);
				find();
			} catch (Throwable e) {
				e.printStackTrace();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
	}

	public void nuevoLibro() {
		libro = true;
		nuevo();
	}
	
	public void nuevoMultimedia() {
		libro = false;
		nuevo();
	}
	
	protected void nuevo() {
		prestamo = new Prestamo();
	}
	
	public void extend() {
		libro=false;
		if(prestamo!=null ) {
			if(prestamo.getMaterial() instanceof Libro)
				libro=true;
		}
		
	}
	
	public void find() {
		prestamoList = new ArrayList<>();
		try {
			prestamoList = prestamoServicio.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public List<Prestamo> getPrestamoList() {
		return prestamoList;
	}

	public void setPrestamoList(List<Prestamo> prestamoList) {
		this.prestamoList = prestamoList;
	}

	public List<Libro> getLibroList() {
		try {
			if(libroList == null || libroList.isEmpty())
				libroList = libroServicio.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return libroList;
	}

	public void setLibroList(List<Libro> libroList) {
		this.libroList = libroList;
	}

	public List<Usuario> getUsuarioList() {
		try {
			if(usuarioList == null || usuarioList.isEmpty())
				usuarioList = usuarioServicio.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<Multimedia> getMultimediaList() {
		try {
			if(multimediaList == null || multimediaList.isEmpty())
				multimediaList = multimediaServicio.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return multimediaList;
	}

	public void setMultimediaList(List<Multimedia> multimediaList) {
		this.multimediaList = multimediaList;
	}

	public boolean isLibro() {
		return libro;
	}

	public void setLibro(boolean libro) {
		this.libro = libro;
	}


}
