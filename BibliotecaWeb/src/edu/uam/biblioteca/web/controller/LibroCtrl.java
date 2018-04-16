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
import edu.uam.biblioteca.servicio.impl.LibroServicio;

@ManagedBean 
@ViewScoped
public class LibroCtrl {

	@EJB
	private LibroServicio libroServicio;

	private Libro libro;
	private List<Libro> libroList;
	private List<Libro> libroFilterList;

	public LibroCtrl() {
	}
	
	@PostConstruct
	void init() {
		find();
	}

	public void save() {
		try {
			if (libro.getMatId() == null) {
				libroServicio.save(libro);
			} else {
				libroServicio.update(libro);
			}
			find();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void edit() {
		
	}

	public void remove() {
		if (libro != null) {
			try {
				libroServicio.remove(libro);
				find();
			} catch (Throwable e) {
				e.printStackTrace();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
	}

	public void nuevo() {
		libro = new Libro();
	}

	public void find() {
		libroList = new ArrayList<Libro>();
		try {
			libroList = libroServicio.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	/**
	 * @return the libro
	 */
	public Libro getlibro() {
		return libro;
	}

	/**
	 * @param libro
	 *            the libro to set
	 */
	public void setlibro(Libro libro) {
		this.libro = libro;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public List<Libro> getLibroList() {
		return libroList;
	}

	public void setLibroList(List<Libro> libroList) {
		this.libroList = libroList;
	}

	public List<Libro> getLibroFilterList() {
		return libroFilterList;
	}

	public void setLibroFilterList(List<Libro> libroFilterList) {
		this.libroFilterList = libroFilterList;
	}



}
