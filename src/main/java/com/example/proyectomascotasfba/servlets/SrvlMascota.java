package com.example.proyectomascotasfba.servlets;

import com.example.proyectomascotasfba.entities.Mascota;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@WebServlet(name = "SrvlMascota", value = "/SrvlMascota")
public class SrvlMascota extends HttpServlet {

    public static ArrayList<Mascota> MASCOTAS = new ArrayList<>(Arrays.asList(
            new Mascota("1", "Pepito","Cariñoso", "Criollo",true,true,2),
            new Mascota("2", "Lucas","Tierno", "Criollo",false,true,4)
    ));

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /*
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        if(request.getParameter("id")==null){
            for (int i=0; i<this.MASCOTAS.size();i++){
                out.print(MASCOTAS.get(i).getId()+" ");
                out.print(MASCOTAS.get(i).getNombre()+" ");
                out.print(MASCOTAS.get(i).getDescripcion()+" ");
                out.print(MASCOTAS.get(i).getRaza()+" ");
                out.print(MASCOTAS.get(i).getVacunado()+" ");
                out.print(MASCOTAS.get(i).getEsterilizado()+" ");
                out.print(MASCOTAS.get(i).getEdad()+" ");
                out.println();
            }
        }else{
            Mascota mascota =this.searchMascota(request.getParameter("id"));
            out.print(mascota.getId()+" ");
            out.print(mascota.getNombre()+" ");
            out.print(mascota.getDescripcion()+" ");
            out.print(mascota.getRaza()+" ");
            out.print(mascota.getVacunado()+" ");
            out.print(mascota.getEsterilizado()+" ");
            out.print(mascota.getEdad()+" ");
        }
        */
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();

        if(request.getParameter("mascotaId")==null){
            out.print(gson.toJson(this.MASCOTAS));
            out.println(gson.toJson("Listado de las mascotas registradas"));
        }else {
            Mascota mas=this.searchMascota(request.getParameter("mascotaId"));
            out.print(gson.toJson(mas));
            out.print("La mascota con el id " + mas.getId() + " fue obtenida de la lista.");
        }
        out.flush();
    }

    private Mascota searchMascota(String mascotaId) {
         /*
        for(int i=0; i<this.MASCOTAS.size();i++){
            if(this.MASCOTAS.get(i).getId().equals(id)){
                return this.MASCOTAS.get(i);
            }
        }
        return null;
        */
        for(int i=0;i<MASCOTAS.size();++i){
            if(MASCOTAS.get(i).getId().equals(mascotaId)){
                return (this.MASCOTAS).get(i);
            }
        }
        return null;
    }
    private String getParamsFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        String params = sb.toString();
        return params;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /*
        Mascota mascota = new Mascota(
                request.getParameter("id"),
                request.getParameter("nombre"),
                request.getParameter("descripcion"),
                request.getParameter("raza"),
                request.getParameter("vacunado"),
                request.getParameter("esterilizado"),
                request.getParameter("edad")
        );
        this.MASCOTAS.add(mascota);
        request.setAttribute("mascotas", this.MASCOTAS);
        request.getRequestDispatcher("views/mascotas/list.jsp").forward(request,response);
        */
        ServletOutputStream out=response.getOutputStream();
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();
        JsonObject body= JsonParser.parseString(this.getParamsFromPost(request)).getAsJsonObject();
        int min=0, max=10000;
        Random rd=new Random();

        Mascota mascota = new Mascota(
                String.valueOf(rd.nextInt(max-min)+min),
                body.get("nombre").getAsString(),
                body.get("descripcion").getAsString(),
                body.get("raza").getAsString(),
                body.get("vacunado").getAsBoolean(),
                body.get("esterilizado").getAsBoolean(),
                body.get("edad").getAsInt()
        );

        this.MASCOTAS.add(mascota);
        out.print(gson.toJson(mascota));
        out.println(gson.toJson("La mascota ha sido creada"));
        out.flush();

    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();

        if(request.getParameter("mascotaId")==null){
            out.print(gson.toJson(this.MASCOTAS));
        }else {
            Mascota mascota=this.searchMascotaDelete(request.getParameter("mascotaId"));
            this.MASCOTAS.remove(mascota);
            out.print(gson.toJson(mascota));
            out.print("La mascota con el id " + mascota.getId() + " fue eliminada de la lista.");
        }
        out.flush();
    }
    private Mascota searchMascotaDelete(String mascotaId) {
        for(int i=0;i<MASCOTAS.size();++i){
            if(MASCOTAS.get(i).getId().equals(mascotaId)){
                return (this.MASCOTAS).get(i);
            }
        }
        return null;
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();

        JsonObject body= JsonParser.parseString(this.getParamsFromPost(request)).getAsJsonObject();

        if(request.getParameter("mascotaId")==null){
            out.print(gson.toJson(this.MASCOTAS));
        }else {
            Mascota mascota=this.searchMascota(request.getParameter("mascotaId"));
            mascota.setNombre(body.get("nombre").getAsString());
            mascota.setDescripcion(body.get("descripcion").getAsString());
            mascota.setRaza(body.get("raza").getAsString());
            mascota.setVacunado(body.get("vacunado").getAsBoolean());
            mascota.setEsterilizado(body.get("esterilizado").getAsBoolean());
            mascota.setEdad(body.get("edad").getAsInt());
            out.print(gson.toJson(mascota));
            out.print("La mascota con el id " + mascota.getId() + " fue actualizada de la lista.");
        }
        out.flush();

    }

}