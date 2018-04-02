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
@Table(name = "usuario", catalog = "Biblioteca", schema = "public")
@XmlRootElement
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "usr_id", nullable = false)
	private String usrId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "usr_nombre", nullable = false, length = 100)
	private String usrNombre;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "usr_identificacion", nullable = false, length = 20)
	private String usrIdentificacion;
	@Size(max = 50)
	@Column(name = "usr_email", length = 50)
	private String usrEmail;
	@Size(max = 20)
	@Column(name = "usr_tipo", length = 20)
	private String usrTipo;
	@OneToMany(mappedBy = "usrId")
	private List<Prestamo> prestamoList;

	public Usuario() {
	}

	public String getUsrNombre() {
		return usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public String getUsrIdentificacion() {
		return usrIdentificacion;
	}

	public void setUsrIdentificacion(String usrIdentificacion) {
		this.usrIdentificacion = usrIdentificacion;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public String getUsrTipo() {
		return usrTipo;
	}

	public void setUsrTipo(String usrTipo) {
		this.usrTipo = usrTipo;
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
		hash += (usrId != null ? usrId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "persistence.Usuario[ usrId=" + usrId + " ]";
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}


}
