/**
 * 
 */
package edu.uam.biblioteca.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import edu.uam.biblioteca.persistencia.Libro;
import edu.uam.biblioteca.persistencia.Prestamo;
import edu.uam.biblioteca.servicio.impl.LibroServicio;
import edu.uam.biblioteca.servicio.impl.PrestamoServicio;

@ManagedBean
@ViewScoped
public class PrestamoCtrl {

	@EJB
	private PrestamoServicio prestamoServicio;
	
	@EJB
	private LibroServicio libroServicio;
	
	private Prestamo prestamo;
	private List<Prestamo> prestamoList;
	private List<Libro> libroList;
	
	public PrestamoCtrl() {
	}

	@PostConstruct
	void init() {
		libroList = new ArrayList<>();
		find();
	}

	public void save() {
		try {

			if (prestamo.getPreId()== null) {
				prestamoServicio.save(prestamo);
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


}
