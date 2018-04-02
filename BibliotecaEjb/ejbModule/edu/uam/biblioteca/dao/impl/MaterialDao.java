/**
 * 
 */
package edu.uam.biblioteca.dao.impl;

import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.persistencia.Material;

/**
 * @author wayala
 * 
 */
public class MaterialDao extends DaoGenericoApp<Material, String> {

	public MaterialDao() {
		super(Material.class);

	}


}
