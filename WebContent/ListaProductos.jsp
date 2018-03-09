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
	float: left;
}
/*
#contenedor_boton_{
	margin-left: 1000px
}
*/

</style>

</head>

	

<body>

<table cellspacing="0" border="1" cellpadding="5">

<tr>
<td class="cabecera" >Códigp articulo</td>
<td class="cabecera" >Sección</td>
<td class="cabecera" >Nombre Artículo</td>
<td class="cabecera" >Fecha</td>
<td class="cabecera" >precio</td>
<td class="cabecera" >importado</td>
<td class="cabecera" >país de origen</td>
</tr>

<c:forEach var="tempProd" items="${LISTAPRODUCTOS}">

<tr>
<td class="filas">${tempProd.cArt}</td>
<td class="filas">${tempProd.seccion}</td>
<td class="filas">${tempProd.nArt}</td>
<td class="filas">${tempProd.fecha}</td>
<td class="filas">${tempProd.precio}</td>
<td class="filas">${tempProd.importado}</td>
<td class="filas">${tempProd.pOrigen}</td>
</tr>

</c:forEach>

</table>

<div id="contenedor_boton">

<input type="button" value="Insertar Registro" onclick="window.location.href='inserta_producto.jsp'"/>


</div>

</body>
</html>