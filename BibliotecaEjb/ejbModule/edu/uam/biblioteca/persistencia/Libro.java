/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uam.biblioteca.persistencia;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wen
 */
@Entity
@Table(name = "libro")
@XmlRootElement
public class Libro extends Material implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Size(max = 50)
    @Column(name = "lib_genero", length = 50)
    private String libGenero;
    @JoinColumn(name = "aut_id", referencedColumnName = "aut_id")
    @ManyToOne
    private Autor autor;
    @JoinColumn(name = "edi_id", referencedColumnName = "edi_id")
    @ManyToOne
    private Editorial editorial;
    
    @Column(name = "lib_imagen", length = 50)
    private String libImagen;
    
    @Transient
    private InputStream file;
    
    public Libro() {
    }

    @Override
    public String toString() {
        return "persistence.Libro[ matId=" + getMatId() + " ]";
    }

    public String getLibGenero() {
        return libGenero;
    }

    public void setLibGenero(String libGenero) {
        this.libGenero = libGenero;
    }


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	public Editorial getEditorial() {
		return editorial;
	}


	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}


	public String getLibImagen() {
		return libImagen;
	}


	public void setLibImagen(String libImagen) {
		this.libImagen = libImagen;
	}


	public InputStream getFile() {
		return file;
	}


	public void setFile(InputStream file) {
		this.file = file;
	}



    
}
