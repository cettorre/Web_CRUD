package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import beans.*;
import controlador.*;
import modelo.*;

public class ModeloProductos {
	
	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos) {
		
		this.origenDatos=origenDatos;
		
	}

	
	public List<Productos> getProductos() throws Exception{
		
		List<Productos> productos=new ArrayList<>();
		
		Connection miConexion=null;
		Statement miStatement =null;
		ResultSet miResultset=null;
		try {
		miConexion=origenDatos.getConnection();
		String instruccionSql="SELECT * FROM PRODUCTOS";
		miStatement=miConexion.createStatement();
		miResultset=miStatement.executeQuery(instruccionSql);//tabla virtual obtenida
		
		while(miResultset.next()) {
			String cArt=miResultset.getString("CÓDIGOARTÍCULO");
			String seccion=miResultset.getString("SECCIÓN");
			String nArt=miResultset.getString("NOMBREARTÍCULO");
			double precio=miResultset.getDouble("PRECIO");
			Date fecha=miResultset.getDate("FECHA");
			String importado=miResultset.getString("IMPORTADO");
			String pOrigen=miResultset.getString("PAÍSDEORIGEN");
			
			Productos temProd = new Productos(cArt, seccion, nArt, precio, fecha, importado, pOrigen);
			
			productos.add(temProd);
		}
		}catch (Exception e) {
            e.printStackTrace();}finally {
            	miStatement.close();
    			miConexion.close();
			}
		return productos; 
		
	}


	public void agregarElNuevoProducto(Productos nuevoProducto) throws SQLException {
		//obtener conexion BBDD
		
	Connection miConexion=null;
	PreparedStatement miStatement=null;
				
	try {miConexion=origenDatos.getConnection();
			
		//Crear insert SQL y prepared statement
		
String sql="INSERT INTO PRODUCTOS (CÓDIGOARTÍCULO, SECCIÓN, NOMBREARTÍCULO, PRECIO, FECHA, IMPORTADO, PAÍSDEORIGEN)" +
"VALUES(?,?,?,?,?,?,?)";
		
		miStatement=miConexion.prepareStatement(sql);
				
		//Establecer parametros para productopara almacenar info en instruccion sql
		
		miStatement.setString(1, nuevoProducto.getcArt());
		miStatement.setString(2, nuevoProducto.getSeccion());
		miStatement.setString(3, nuevoProducto.getnArt());
		miStatement.setDouble(4, nuevoProducto.getPrecio());
		
		//setDate nos devuleve tipo java.sql.Date mientras que nuestro objeto teiene java.util.Date 
		java.util.Date utilDAte=nuevoProducto.getFecha();
		java.sql.Date fechaConvertida=new java.sql.Date(utilDAte.getTime());
		miStatement.setDate(5, fechaConvertida);
		
		miStatement.setString(6, nuevoProducto.getImportado());
		miStatement.setString(7, nuevoProducto.getpOrigen());
		
		//ejecutar instruccion SQL
		miStatement.execute();
			}catch(Exception e) {e.printStackTrace();
		}finally {
			miStatement.close();
			miConexion.close();
		}
	}


	public Productos getProducto(String codigoArticulo) throws SQLException {
		Productos elProducto=null;
		Connection miConnection=null;
		PreparedStatement miPreparedStatement=null;
		ResultSet miResultSet=null;
		String cArticulo=codigoArticulo;
		
	try {
		//conexion
		miConnection=origenDatos.getConnection();
		//sql
		String sql="SELECT * FROM PRODUCTOS WHERE CÓDIGOARTÍCULO=?";
		//prepared statement
		miPreparedStatement=miConnection.prepareStatement(sql);
		miPreparedStatement.setString(1, cArticulo);
		//ejecutar consulta
		miResultSet =miPreparedStatement.executeQuery();
		
		//obtener datos respuesta
		if(miResultSet.next()) {
			String c_art=miResultSet.getString("CÓDIGOARTÍCULO");
			String seccion=miResultSet.getString("SECCIÓN");
			String nArt=miResultSet.getString("NOMBREARTÍCULO");
			double precio=miResultSet.getDouble("PRECIO");
			Date fecha=miResultSet.getDate("FECHA");
			String importado=miResultSet.getString("IMPORTADO");
			String pOrigen=miResultSet.getString("PAÍSDEORIGEN");
			
			elProducto = new Productos(c_art, seccion, nArt, precio, fecha, importado, pOrigen);//hace falta campo clave 
		}else {
			throw new Exception("No hemos encontrado el producto con código articulo"+cArticulo);
		}
		

	}catch(Exception e) {e.printStackTrace();}finally {
		miPreparedStatement.close();
		miConnection.close();
	}
		
		return elProducto;
	}


	public void actualizarProducto(Productos productoActualizado) throws SQLException {
		//conexion
		Connection miConnection=null;
		PreparedStatement miStatement=null;
		try {
			miConnection=origenDatos.getConnection();
		
		
		//sentencia
			
			String sql="UPDATE PRODUCTOS SET SECCIÓN=?, NOMBREARTÍCULO=?, PRECIO=?, FECHA=?, IMPORTADO=?, PAÍSDEORIGEN=? WHERE CÓDIGOARTÍCULO=?";
		
		//prepared stat
			miStatement=miConnection.prepareStatement(sql);
		
		//establecer parametros
			miStatement.setString(1, productoActualizado.getSeccion());
			miStatement.setString(2, productoActualizado.getnArt());
			miStatement.setDouble(3, productoActualizado.getPrecio());
			
			java.util.Date utilDAte=productoActualizado.getFecha();
			java.sql.Date fechaConvertida=new java.sql.Date(utilDAte.getTime());
			miStatement.setDate(4, fechaConvertida);
			
			miStatement.setString(5, productoActualizado.getImportado());
			miStatement.setString(6, productoActualizado.getpOrigen());
			miStatement.setString(7, productoActualizado.getcArt());
		
		//ejecutar instruccion
			
			miStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			miStatement.close();
			miConnection.close();
			
		}
	}


	public void eliminarProducto(String productoEliminado) throws Exception{
		Connection miConnection=null;
		PreparedStatement miStatement=null;
		try {
			miConnection=origenDatos.getConnection();
		//sentencia
			String sql="DELETE FROM PRODUCTOS WHERE CÓDIGOARTÍCULO=?";
		//prepared stat
			miStatement=miConnection.prepareStatement(sql);
			miStatement.setString(1, productoEliminado);
		
		//ejecutar instruccion
			
			miStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			miStatement.close();
			miConnection.close();
		}
				
	}
}
