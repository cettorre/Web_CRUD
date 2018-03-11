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
		
		return productos; 
		
	}


	public void agregarElNuevoProducto(Productos nuevoProducto) {
		//obtener conexion BBDD
		
		Connection miConexion=null;
		PreparedStatement miStatement=null;
				
				try {
					miConexion=origenDatos.getConnection();
			
		
		
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
		}
		
	}


	public Productos getProducto(String codigoArticulo) {
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
			
			String seccion=miResultSet.getString("SECCIÓN");
			String nArt=miResultSet.getString("NOMBREARTÍCULO");
			double precio=miResultSet.getDouble("PRECIO");
			Date fecha=miResultSet.getDate("FECHA");
			String importado=miResultSet.getString("IMPORTADO");
			String pOrigen=miResultSet.getString("PAÍSDEORIGEN");
			
			elProducto = new Productos(seccion, nArt, precio, fecha, importado, pOrigen);
		}else {
			throw new Exception("No hemos encontrado el producto con código articulo"+cArticulo);
		}
		

	}catch(Exception e) {e.printStackTrace();}
		
		return elProducto;
	}
	
}
