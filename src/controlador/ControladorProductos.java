package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.oracle.jrockit.jfr.RequestDelegate;

import beans.Productos;
import modelo.ModeloProductos;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ModeloProductos modeloProductos;
	
	@Resource(name="jdbc/PRODUCTOS")
	private DataSource miPool;
	
	
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
		
		modeloProductos=new ModeloProductos(miPool);
		
		}catch(Exception e){
			throw new ServletException(e);	
		}
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorProductos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		//leer parametro que llega desde formulario (hidden input)
		String elParametro=request.getParameter("instruccion");
		
		
		
		//si no se envia el parametro, listar productos
		if(elParametro==null)elParametro="listar";
		
		
		//redirigir flujo de ejecucion adecuado
		
		switch(elParametro) {
		case "listar":	obtenerProductos(request, response);break;
		case "insertarBBDD": agregarProductos(request, response);break;
		case "cargar": try {
				cargaProductos(request, response);
			} catch (Exception e1) {e1.printStackTrace();} break;
		
		case "actualizarBBDD":try {
				actualizarProductos(request,response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		 break;
		default: try {throw new Exception(); } catch (Exception e) {e.printStackTrace();	}
		//default: obtenerProductos(request, response);break;
		}
		
	
	}


	
	private void actualizarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//leer datos que vienen del formulario de actualizar
	String CodArticlulo=request.getParameter("element_1");
	String Seccion=request.getParameter("element_2");
	String NombreArticulo=request.getParameter("element_3");
	
	  SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
	  Date Fecha=null;
	try {
	Fecha=formatoFecha.parse(request.getParameter("element_4"));
	}catch (ParseException e) {e.printStackTrace();}
	
	double Precio=Double.parseDouble(request.getParameter("element_5"));
	String Importado=request.getParameter("element_6");
	String PaisOrigen=request.getParameter("element_7");
		
		//crear un objeto de tipo producto con la info del formulario
		
	Productos productoActualizado=new Productos(CodArticlulo, Seccion, NombreArticulo, Precio, Fecha, Importado, PaisOrigen);
		//actualizar la BBDD con la info del obj Producto
		
	modeloProductos.actualizarProducto(productoActualizado);
	
		//volver al listado con la info actualizada
		
	obtenerProductos(request, response);	
		
	}

	private void cargaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//leer el codigo articulo que viene del listado--260
		String codigoArticulo=request.getParameter("CArticulo");
		
		//comunicar con el modelo para que haya consulta a BBDD con CArticulo del caso
		
		
		
		//Enviar codigo articulo a modelo
		Productos elProducto=modeloProductos.getProducto(codigoArticulo);
		
		
		//colocar atributo correspondiente al CArticulo
		request.setAttribute("ProductoActualizar", elProducto);
		
		
		//Enviar Producto al formulario de actualizar
		RequestDispatcher dispatcher=request.getRequestDispatcher("actualizar_producto.jsp");
		dispatcher.forward(request, response);

		
	}






	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		
			//leer info que llega desde el formulario
		String CodArticlulo=request.getParameter("element_1");
		String Seccion=request.getParameter("element_2");
		String NombreArticulo=request.getParameter("element_3");
		
		  SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		  Date Fecha=null;
		try {
		Fecha=formatoFecha.parse(request.getParameter("element_4"));
		}catch (ParseException e) {e.printStackTrace();}
		
		double Precio=Double.parseDouble(request.getParameter("element_5"));
		String Importado=request.getParameter("element_6");
		String PaisOrigen=request.getParameter("element_7");
		
		
			//crear objeto de tipo producto
		Productos nuevoProducto=new Productos(CodArticlulo, Seccion, NombreArticulo, Precio, Fecha, Importado, PaisOrigen);
		
				
		
			//enviar el objeto al modelo y despues insertar el objeto Producto en la base de datos
		modeloProductos.agregarElNuevoProducto(nuevoProducto);
		
		
			//volver al listado de productos
		
		obtenerProductos(request, response);
		
		
	}






	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
	
		//obtener la lista de productos desde el modelo
		
		List<Productos> productos;
		
		try {
		
			productos=modeloProductos.getProductos();
			
			
		//agregar lista de productos a request
			
			request.setAttribute("LISTAPRODUCTOS", productos);
		
		//enviar request a la pagina JSP
		
			RequestDispatcher miDispatcher= request.getRequestDispatcher("/ListaProductos.jsp");
			miDispatcher.forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}






	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
