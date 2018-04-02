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
@Table(name = "editorial", catalog = "Biblioteca", schema = "public")
@XmlRootElement
public class Editorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "edi_id", nullable = false,columnDefinition = "serial")
    private String ediId;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "edi_nombre", nullable = false, length = 100)
    private String ediNombre;
    @OneToMany(mappedBy = "ediId")
    private List<Libro> libroList;

    public Editorial() {
    }


    public String getEdiNombre() {
        return ediNombre;
    }

    public void setEdiNombre(String ediNombre) {
        this.ediNombre = ediNombre;
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
        hash += (ediId != null ? ediId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editorial)) {
            return false;
        }
        Editorial other = (Editorial) object;
        if ((this.ediId == null && other.ediId != null) || (this.ediId != null && !this.ediId.equals(other.ediId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Editorial[ ediId=" + ediId + " ]";
    }


	public String getEdiId() {
		return ediId;
	}


	public void setEdiId(String ediId) {
		this.ediId = ediId;
	}


    
}
