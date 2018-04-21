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

import edu.uam.biblioteca.persistencia.Multimedia;
import edu.uam.biblioteca.servicio.impl.MultimediaServicio;

@ManagedBean
@ViewScoped
public class MultimediaCtrl {

	@EJB
	private MultimediaServicio multimediaServicio;

	private Multimedia multimedia;
	private List<Multimedia> multimediaList;
	private List<Multimedia> multimediaFilterList;


	public MultimediaCtrl() {
	}

	@PostConstruct
	void init() {
		find();
	}

	public void save() {
		try {

			if (multimedia.getMatId() == null) {
				multimediaServicio.save(multimedia);
			} else {
				multimediaServicio.update(multimedia);
			}

			find();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public void remove() {
		if (multimedia != null) {
			try {
				multimediaServicio.remove(multimedia);
				find();
			} catch (Throwable e) {
				e.printStackTrace();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
	}

	public void nuevo() {
		multimedia = new Multimedia();
	}

	public void find() {
		multimediaList = new ArrayList<Multimedia>();
		try {
			multimediaList = multimediaServicio.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}


	/**
	 * @return the multimedia
	 */
	public Multimedia getmultimedia() {
		return multimedia;
	}

	/**
	 * @param multimedia
	 *            the multimedia to set
	 */
	public void setmultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}

	public Multimedia getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}

	public List<Multimedia> getMultimediaList() {
		return multimediaList;
	}

	public void setMultimediaList(List<Multimedia> multimediaList) {
		this.multimediaList = multimediaList;
	}

	public List<Multimedia> getMultimediaFilterList() {
		return multimediaFilterList;
	}

	public void setMultimediaFilterList(List<Multimedia> multimediaFilterList) {
		this.multimediaFilterList = multimediaFilterList;
	}


}
