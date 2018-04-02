/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uam.biblioteca.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wen
 */
@Entity
@Table(name = "prestamo", catalog = "Biblioteca", schema = "public")
@XmlRootElement
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pre_id", nullable = false,columnDefinition = "serial")
    private String preId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pre_fecha_prestamo", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date preFechaPrestamo;
    @Size(max = 50)
    @Column(name = "pre_estado", length = 50)
    private String preEstado;
    @Column(name = "pre_fecha_devolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date preFechaDevolucion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pre_multa", precision = 10, scale = 2)
    private BigDecimal preMulta;
    @JoinColumn(name = "mat_id", referencedColumnName = "mat_id")
    @ManyToOne
    private Material material;
    
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne
    private Usuario usrId;

    public Prestamo() {
    }


    public Date getPreFechaPrestamo() {
        return preFechaPrestamo;
    }

    public void setPreFechaPrestamo(Date preFechaPrestamo) {
        this.preFechaPrestamo = preFechaPrestamo;
    }

    public String getPreEstado() {
        return preEstado;
    }

    public void setPreEstado(String preEstado) {
        this.preEstado = preEstado;
    }

    public Date getPreFechaDevolucion() {
        return preFechaDevolucion;
    }

    public void setPreFechaDevolucion(Date preFechaDevolucion) {
        this.preFechaDevolucion = preFechaDevolucion;
    }

    public BigDecimal getPreMulta() {
        return preMulta;
    }

    public void setPreMulta(BigDecimal preMulta) {
        this.preMulta = preMulta;
    }


    public Usuario getUsrId() {
        return usrId;
    }

    public void setUsrId(Usuario usrId) {
        this.usrId = usrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preId != null ? preId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.preId == null && other.preId != null) || (this.preId != null && !this.preId.equals(other.preId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Prestamo[ preId=" + preId + " ]";
    }

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}


	public String getPreId() {
		return preId;
	}


	public void setPreId(String preId) {
		this.preId = preId;
	}
    
}
