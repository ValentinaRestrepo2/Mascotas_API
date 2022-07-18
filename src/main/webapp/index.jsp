<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.proyectomascotasfba.entities.Mascota"%>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<Mascota> mascota = new ArrayList();

%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <title>Mascotas</title>
</head>
<body>
<h1><%= "Mascotas de la fundación Bello Animal" %>
</h1>
<br/>

<a href="hello-servlet">Mascotas</a>
<%
    int a = 15;
    Mascota lola = new Mascota("3", "Lola","Cariñosao", "Poddle",true,true,2);
    lola.getId();
    lola.setEdad(3);
    mascota.add(lola);
    mascota.add(new Mascota("4", "Tomy","Humilde", "Golden",false,true,2));
%>
<!--<p>a vale: <%= a %></p>
<b>Nombre: </b><%= mascota.get(0).getNombre() %> -->

<form role="form" action="SrvlMascota" method="post">
    <fieldset>
        <legend>Información de la mascota</legend>
        <label>Nombre</label>
        <input type="text" name="name" id="name"/><br/><br/>
        <label>Descripción</label>
        <input type="text" name="descripcion" id="descripcion"/><br/><br/>
        <label>Raza</label>
        <input type="text" name="raza" id="raza"/><br/><br/>
        <label>¿Esta vacunado?</label>
        <input type="text" name="vacunado" id="vacunado"/><br/><br/>
        <label>¿Esta esterilizado?</label>
        <input type="text" name="esterilizado" id="esterilizado"/><br/><br/>
        <label>Edad</label>
        <input type="number" name="edad" id="edad"/><br/><br/>
        <input class="boton" type="submit" value="Guardar"/>
    </fieldset>
</form>
<form role="form" action="SrvlMascota" method="delete">
    <fieldset>
        <legend>Eliminar mascota</legend>

        <label>Identificación</label>
        <input type="text" name="id" id="idM"/><br/><br/>

        <input class="boton" type="submit" value="Borrar"/>
    </fieldset>
</form>
<hr/>
<table id="tbl_mascotas">
    <thead>
    <tr>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Raza</th>
        <th>Edad</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Mascota x: mascota){
    %>
    <tr>
        <td><%= x.getId() %></td>
        <td><%= x.getNombre() %></td>
        <td><%= x.getDescripcion() %></td>
        <td><%= x.getRaza() %></td>
        <td><%= x.getEdad() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>