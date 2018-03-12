<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*,beans.*,controlador.*,modelo.* "%>    
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

 .cabecera{
	font-size: 1.2em;
	font-weight: bold;
	color:#ccffff;
	background-color: #33cc33	
	}
.filas{
	text-align: center;
	background-color: #ccffcc
	}
table{
/*	float: right;*/
}

#contenedor_boton_{
	/*margin-left: 1000px*/
	float: left;
}

.btn {
  background: #3498db;
  background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
  background-image: -moz-linear-gradient(top, #3498db, #2980b9);
  background-image: -ms-linear-gradient(top, #3498db, #2980b9);
  background-image: -o-linear-gradient(top, #3498db, #2980b9);
  background-image: linear-gradient(to bottom, #3498db, #2980b9);
  -webkit-border-radius: 28;
  -moz-border-radius: 28;
  border-radius: 28px;
  font-family: Arial;
  color: #ffffff;
  font-size: 20px;
  padding: 10px 20px 10px 20px;
  text-decoration: none;
}

.btn:hover {
  background: #3cb0fd;
  background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
  background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
  text-decoration: none;

</style>

</head>

	

<body>

<div id="contenedor_boton">

<input class="btn" type="button" value="Añadir Registro" onclick="window.location.href='inserta_producto.jsp'"/>


</div>

<table cellspacing="0" cellpadding="3">

<tr>
<td class="cabecera" >Códigp articulo</td>
<td class="cabecera" >Sección</td>
<td class="cabecera" >Nombre Artículo</td>
<td class="cabecera" >Fecha</td>
<td class="cabecera" >Precio</td>
<td class="cabecera" >Importado</td>
<td class="cabecera" >País de origen</td>
<td class="cabecera" >Acción</td>
</tr>

<c:forEach var="tempProd" items="${LISTAPRODUCTOS}">

<!--!LINK ACTUALIZAR PARA CADA PRODUCTO CON SU CAMPO CLAVE -->
<c:url var="linkTemp" value="ControladorProductos">
	<c:param name="instruccion" value="cargar"></c:param>
	<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
</c:url>
<!--!LINK ELIMINAR PARA CADA PRODUCTO CON SU CAMPO CLAVE -->
<c:url var="linkTempEliminar" value="ControladorProductos">
	<c:param name="instruccion" value="eliminar"></c:param>
	<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
</c:url>



<tr>
<td class="filas">${tempProd.cArt}</td>
<td class="filas">${tempProd.seccion}</td>
<td class="filas">${tempProd.nArt}</td>
<td class="filas">${tempProd.fecha}</td>
<td class="filas">${tempProd.precio}</td>
<td class="filas">${tempProd.importado}</td>
<td class="filas">${tempProd.pOrigen}</td>
<td class="filas"><a href="${linkTemp }">Actualizar</a>&nbsp;&nbsp;<a href="${linkTempEliminar}">Eliminar </a> </td>
</tr>

</c:forEach>

</table>



</body>
</html>