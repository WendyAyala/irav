/**
 * 
 */
package edu.uam.biblioteca.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import edu.uam.biblioteca.constantes.ContadorTablaEnum;
import edu.uam.biblioteca.exception.DaoException;

/**
 * @author wayala
 * 
 *         Generador de PK de las tablas
 * 
 * @version 1.0
 * 
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ContadorPkServicio {

	@Resource(mappedName = "java:jboss/datasources/bibliotecaDS")
	private DataSource dataSource;

	public ContadorPkServicio() {
	}

	
	@Lock(LockType.WRITE)
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String generarContadorTabla(ContadorTablaEnum tablaContador) throws DaoException {


		Connection cnn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		String sql = "SELECT valor FROM CONTADOR WHERE nombre=?";

		Long valor = null;
		try {

			cnn = dataSource.getConnection();

			stm = cnn.prepareStatement(sql);
			stm.setString(1, tablaContador.getNombreTabla());

			rs = stm.executeQuery();

			while (rs.next()) {
				valor = rs.getLong("valor");
				break;
			}

		} catch (Exception e) {
			throw new DaoException("Error consulta: ", e);
		} finally {
			try {
				rs.close();
				stm.close();
				cnn.close();
			} catch (Exception e2) {
			}
		}

		sql = "UPDATE CONTADOR SET valor=? WHERE nombre=?";

		try {
			cnn = dataSource.getConnection();
			valor += 1;

			stm = cnn.prepareStatement(sql);
			stm.setLong(1, valor);
			stm.setString(2, tablaContador.getNombreTabla());

			stm.executeUpdate();

		} catch (Exception e) {
			throw new DaoException("Error Actualizar : ", e);
		} finally {
			try {
				stm.close();
				cnn.close();
			} catch (Exception e2) {
			}
		}

		return valor + "";

	}
}
