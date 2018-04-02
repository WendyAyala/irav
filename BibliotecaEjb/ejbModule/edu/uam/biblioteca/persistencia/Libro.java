/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uam.biblioteca.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wen
 */
@Entity
@Table(name = "libro", catalog = "Biblioteca", schema = "public")
@XmlRootElement
public class Libro extends Material implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Size(max = 50)
    @Column(name = "lib_genero", length = 50)
    private String libGenero;
    @JoinColumn(name = "aut_id", referencedColumnName = "aut_id")
    @ManyToOne
    private Autor autId;
    @JoinColumn(name = "edi_id", referencedColumnName = "edi_id")
    @ManyToOne
    private Editorial ediId;

    public Libro() {
    }


    public String getLibGenero() {
        return libGenero;
    }

    public void setLibGenero(String libGenero) {
        this.libGenero = libGenero;
    }

    public Autor getAutId() {
        return autId;
    }

    public void setAutId(Autor autId) {
        this.autId = autId;
    }

    public Editorial getEdiId() {
        return ediId;
    }

    public void setEdiId(Editorial ediId) {
        this.ediId = ediId;
    }

    
}
