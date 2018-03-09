package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
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
	
}
