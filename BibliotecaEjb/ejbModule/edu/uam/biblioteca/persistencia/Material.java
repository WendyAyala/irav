/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uam.biblioteca.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "material", catalog = "Biblioteca", schema = "public")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mat_id", nullable = false)
    private String matId;
   
    @Column(name = "mat_cantidad")
    private BigDecimal matCantidad;
    
    @Size(max = 200)
    @Column(name = "mat_nombre", length = 200)
    private String matNombre;
    
    @OneToMany(mappedBy = "material")
    private List<Prestamo> prestamoList;

    public Material() {
    }



    public String getMatNombre() {
        return matNombre;
    }

    public void setMatNombre(String matNombre) {
        this.matNombre = matNombre;
    }


    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matId != null ? matId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.matId == null && other.matId != null) || (this.matId != null && !this.matId.equals(other.matId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Material[ matId=" + matId + " ]";
    }




	public BigDecimal getMatCantidad() {
		return matCantidad;
	}



	public void setMatCantidad(BigDecimal matCantidad) {
		this.matCantidad = matCantidad;
	}



	public String getMatId() {
		return matId;
	}



	public void setMatId(String matId) {
		this.matId = matId;
	}




}
