<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*,beans.*,controlador.*,modelo.* "%>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
 //obtiene los productos del controlador (Servlet)

 List<Productos> losProductos=(List<Productos>) request.getAttribute("LISTAPRODUCTOS");
 
%>	

<body>

<%=losProductos %><br>

</body>
</html>