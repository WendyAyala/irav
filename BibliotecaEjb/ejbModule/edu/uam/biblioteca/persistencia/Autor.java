/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uam.biblioteca.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wen
 */
@Entity
@Table(name = "autor", catalog = "Biblioteca", schema = "public")
@XmlRootElement
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "aut_id", nullable = false)
    private String autId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "aut_nombre", nullable = false, length = 100)
    private String autNombre;
    @OneToMany(mappedBy = "autId")
    private List<Libro> libroList;

    public Autor() {
    }


    public String getAutNombre() {
        return autNombre;
    }

    public void setAutNombre(String autNombre) {
        this.autNombre = autNombre;
    }

    @XmlTransient
    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autId != null ? autId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.autId == null && other.autId != null) || (this.autId != null && !this.autId.equals(other.autId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Autor[ autId=" + autId + " ]";
    }


	public String getAutId() {
		return autId;
	}


	public void setAutId(String autId) {
		this.autId = autId;
	}
    
}
