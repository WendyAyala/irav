/**
 * 
 */
package edu.uam.biblioteca.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Autor;
import edu.uam.biblioteca.persistencia.Editorial;
import edu.uam.biblioteca.persistencia.Libro;
import edu.uam.biblioteca.servicio.impl.AutorServicio;
import edu.uam.biblioteca.servicio.impl.EditorialServicio;
import edu.uam.biblioteca.servicio.impl.LibroServicio;
import edu.uam.biblioteca.web.constantes.GeneroLibroEnum;

@ManagedBean
@ViewScoped
public class LibroCtrl {

	@EJB
	private LibroServicio libroServicio;

	@EJB
	private AutorServicio autorServicio;

	@EJB
	private EditorialServicio editorialServicio;

	private Libro libro;
	private List<Libro> libroList;
	private List<Libro> libroFilterList;
	private List<Autor> autorList;
	private List<Editorial> editorialList;

	private List<GeneroLibroEnum> generoLibroEnumList;

	private static final String RESOURCE_BOOK_PATH = "resources/img/libros/";
	private static final String CLASSES_FOLDER_NAME = "WEB-INF";
	private static final String CONTROLLER_SOURCE_PATH = LibroCtrl.class.getResource(".").getPath();

	public LibroCtrl() {
	}

	@PostConstruct
	void init() {
		find();
		autorList = new ArrayList<Autor>();
		editorialList = new ArrayList<Editorial>();
		generoLibroEnumList = GeneroLibroEnum.getGeneroList();
	}

	public void save() {
		try {

			if (libro.getFile() != null) {
				try {

					String url = (String) CONTROLLER_SOURCE_PATH.subSequence(0,
							CONTROLLER_SOURCE_PATH.indexOf(CLASSES_FOLDER_NAME)) + RESOURCE_BOOK_PATH
							+ libro.getLibImagen();
					File file = new File(url);

					FileUtils.copyInputStreamToFile(libro.getFile(), file);

				} catch (IOException e) {

					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"Error al cargar el archivo");
					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
			}

			if (libro.getMatId() == null) {
				libroServicio.save(libro);
			} else {
				libroServicio.update(libro);
			}

			find();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public void seleccionar() {
		String claveLibro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		libro = libroList.stream().filter(l-> l.getMatId().equals(claveLibro)).findFirst().get();
	}
	
	public void removeImgContent() {
		seleccionar();
		remove();
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

	public void handleFileUpload(FileUploadEvent event) {
		if (libro != null) {
			try {
				libro.setFile(event.getFile().getInputstream());
				libro.setLibImagen(event.getFile().getFileName());
				FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (IOException e) {
				e.printStackTrace();
			}

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

	public List<Autor> getAutorList() {
		if (autorList == null || autorList.isEmpty())
			try {
				autorList = autorServicio.getAll();
			} catch (DaoException e) {
				e.printStackTrace();
			}
		return autorList;
	}

	public void setAutorList(List<Autor> autorList) {
		this.autorList = autorList;
	}

	public List<Editorial> getEditorialList() {
		if (editorialList == null || editorialList.isEmpty())
			try {
				editorialList = editorialServicio.getAll();
			} catch (DaoException e) {
				e.printStackTrace();
			}
		return editorialList;
	}

	public void setEditorialList(List<Editorial> editorialList) {
		this.editorialList = editorialList;
	}

	public List<GeneroLibroEnum> getGeneroLibroEnumList() {
		return generoLibroEnumList;
	}

	public void setGeneroLibroEnumList(List<GeneroLibroEnum> generoLibroEnumList) {
		this.generoLibroEnumList = generoLibroEnumList;
	}

}
