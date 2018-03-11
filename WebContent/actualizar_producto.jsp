<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<head>

<title>Actualizar producto</title>
<link rel="stylesheet" type="text/css" href="view.css" media="all">
<script type="text/javascript" src="view.js"></script>


<body id="main_body" >
	
	<img id="top" src="top.png" alt="">
	<div id="form_container">
	
		<h1><a>Actualizar productos</a></h1>
		<form id="form_74820" class="appnitro"  method="get" action="ControladorProductos">
					<div class="form_description">
		<input type="hidden" name="instruccion" value="cargar">	
		<input type="hidden" name="CArt" value="${ProductoActualizar.cArt }">		<!-- 261 -->	
					
			<h2>Actualizar productos</h2>
			<p>Introduce la informacion de los productos para actualizar el registro</p>
		</div>						
			<ul >
			
<!-- 					<li id="li_1" >
		<label class="description" for="element_1">Codigo Articulo </label>
		<div>
			<input id="element_1" name="element_1" class="element text medium" type="text" maxlength="255" value=""/> 
		</div><p class="guidelines" id="guide_1"><small>insert article code</small></p> 
		</li>    -->	
			<li id="li_2" >
		<label class="description" for="element_2">Seccion </label>
		<div>
			<input id="element_2" name="element_2" class="element text medium" type="text" maxlength="255" value="${ProductoActualizar.seccion}" /> 
		</div><p class="guidelines" id="guide_2"><small>insert article category</small></p> 
		</li>		<li id="li_3" >
		<label class="description" for="element_3">Nombre articulo </label>
		<div>
			<input id="element_3" name="element_3" class="element text medium" type="text" maxlength="255" value="${ProductoActualizar.nArt }"/> 
		</div><p class="guidelines" id="guide_3"><small>insert article number</small></p> 
		</li>		<li id="li_4" >
		<label class="description" for="element_4">Fecha </label>
		<div>
			<input id="element_4" name="element_4" class="element text medium" type="text" maxlength="255" value="${ProductoActualizar.fecha }"/> 
		</div><p class="guidelines" id="guide_4"><small>insert date</small></p> 
		</li>		<li id="li_5" >
		<label class="description" for="element_5">Precio </label>
		<div>
			<input id="element_5" name="element_5" class="element text medium" type="text" maxlength="255" value="${ProductoActualizar.precio }"/> 
		</div><p class="guidelines" id="guide_5"><small>insert article price</small></p> 
		</li>		<li id="li_6" >
		<label class="description" for="element_6">Importado </label>
		<div>
			<input id="element_6" name="element_6" class="element text medium" type="text" maxlength="255" value="${ProductoActualizar.importado }"/> 
		</div> 
		</li>		<li id="li_7" >
		<label class="description" for="element_7">Pais de origen </label>
		<div>
			<input id="element_7" name="element_7" class="element text medium" type="text" maxlength="255" value="${ProductoActualizar.pOrigen }" /> 
		</div> 
		</li>
			
					<li class="buttons">
			    <input type="hidden" name="form_id" value="74820" />
			    
				<input id="saveForm" class="button_text" type="submit" name="submit" value="Submit" />
		</li>
			</ul>
		</form>	
	</div>
	<img id="bottom" src="bottom.png" alt="">
	</body>
</html>