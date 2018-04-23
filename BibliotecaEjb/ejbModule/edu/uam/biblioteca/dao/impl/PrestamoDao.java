/**
 * 
 */
package edu.uam.biblioteca.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;

import edu.uam.biblioteca.constantes.EstadoPrestamoEnum;
import edu.uam.biblioteca.dao.DaoGenericoApp;
import edu.uam.biblioteca.exception.DaoException;
import edu.uam.biblioteca.persistencia.Prestamo;

/**
 * @author wayala
 * 
 */
public class PrestamoDao extends DaoGenericoApp<Prestamo, String> {

	public PrestamoDao() {
		super(Prestamo.class);

	}

	/**
	 * @param idLibro
	 * @return
	 * @throws DaoException
	 */
	public int availableBooks(String idLibro) throws DaoException {
		String sql = "Select  (l.mat_cantidad-coalesce(count(p.pre_estado),0)  ) as disponible " + 
				"from public.material l " + 
				"left join prestamo p on l.mat_id = p.mat_id\r\n" + 
				"where l.mat_id = '"+ idLibro + "'and ( coalesce (p.pre_estado,'') )!= ('"+EstadoPrestamoEnum.ENTREGADO.getValor()+"') " + 
				"group by l.mat_cantidad";
		
		return  ((BigDecimal) getEntityManager().createNativeQuery(sql).getSingleResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see edu.uam.biblioteca.dao.DaoGenericoEjb#update(java.io.Serializable)
	 */
	@Override
	public void update(Prestamo o) throws DaoException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd"); 
		String sql="UPDATE public.prestamo " + 
				"	SET   usr_id='"+o.getUsuario().getUsrId()+"', mat_id='"+o.getMaterial().getMatId()+"',  pre_estado='"+o.getPreEstado()+"'  ,pre_fecha_prestamo='"+dt.format(o.getPreFechaPrestamo())+"', pre_fecha_devolucion='"+dt.format(o.getPreFechaDevolucion())+"', pre_multa="+o.getPreMulta()+" " + 
				"	WHERE pre_id='"+o.getPreId()+"' ";
		getEntityManager().createNativeQuery(sql).executeUpdate();
	}
	
	/**
	 * @param matId
	 * @return
	 * @throws DaoException
	 */
	public int exist(String matId)throws DaoException{
		try {
			String sql =" Select count(*) from Prestamo p where p.mat_id = '"+matId+"' ";
			return ((BigInteger) this.getEntityManager().createNativeQuery(sql).getSingleResult()).intValue();
		} catch (Exception e) {
			throw new DaoException();
		}
		
	}
}
