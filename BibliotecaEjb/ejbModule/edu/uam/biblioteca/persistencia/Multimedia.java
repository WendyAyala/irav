/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uam.biblioteca.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wen
 */
@Entity
@Table(name = "multimedia")
@XmlRootElement
public class Multimedia extends Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mul_plataforma", nullable = false, length = 100)
    private String mulPlataforma;

    public Multimedia() {
    }


    public String getMulPlataforma() {
        return mulPlataforma;
    }

    public void setMulPlataforma(String mulPlataforma) {
        this.mulPlataforma = mulPlataforma;
    }


    
}
