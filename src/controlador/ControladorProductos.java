package controlador;

import java.io.IOException;
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
