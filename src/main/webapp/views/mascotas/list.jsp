<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.proyectomascotasfba.entities.Mascota" %>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<Mascota> listMascotas = (ArrayList<Mascota> )request.getAttribute("mascotas");
%>

<html>
<head>
    <title>Lista de mascotas</title>
</head>
<body>
<table id="tbl_empleyees">
    <thead>
    <tr>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Raza</th>
        <th>¿Esta vacunado?</th>
        <th>¿Esta esterilizado?</th>
        <th>Edad</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Mascota x: listMascotas){
    %>
    <tr>
        <td><%= x.getId() %></td>
        <td><%= x.getNombre() %></td>
        <td><%= x.getDescripcion() %></td>
        <td><%= x.getRaza() %></td>
        <td><%= x.isVacunado() %></td>
        <td><%= x.isEsterilizado() %></td>
        <td><%= x.getEdad() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
