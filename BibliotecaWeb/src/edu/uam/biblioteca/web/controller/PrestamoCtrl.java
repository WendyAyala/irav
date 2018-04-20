/**
 * 
 */
package edu.uam.biblioteca.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import edu.uam.biblioteca.persistencia.Libro;
import edu.uam.biblioteca.persistencia.Prestamo;
import edu.uam.biblioteca.persistencia.Usuario;
import edu.uam.biblioteca.servicio.impl.LibroServicio;
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
	private UsuarioServicio usuarioServicio;
	
	private Prestamo prestamo;
	private List<Prestamo> prestamoList;
	private List<Libro> libroList;
	private List<Usuario> usuarioList;
	private String fechaActual;
	
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

	public void nuevo() {
		prestamo = new Prestamo();
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


}
