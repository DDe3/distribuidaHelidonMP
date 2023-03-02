package com.distribuida.auth.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import com.distribuida.auth.service.IUsuarioService;
import com.distribuida.auth.to.UsuarioTo;
import com.google.gson.Gson;

import io.helidon.microprofile.cors.CrossOrigin;
import io.helidon.security.annotations.Authenticated;

import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.Response.ok;


@RequestScoped
@Path("/usuarios")
public class UsuarioRestController {


    private static final Gson gson = new Gson();
    @Inject
    private IUsuarioService usuarioService;


    @OPTIONS
    @CrossOrigin()
    public void options() {
    }

    @Counted(unit = MetricUnits.NONE,
       name = "num_registro",
       absolute = true,
       displayName = "Registros de usuarios",
       description = "Metrica para ver cuantas veces el metodo registerUsuario fue llamado",
       tags = {"registro=usuarios"})
    @Timed(name = "tiempo_registro",
       description = "Metrica para monitorear el tiempo de respuesta del registro de usuarios.",
       unit = MetricUnits.SECONDS,
       absolute = true)
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUsuario(UsuarioTo usuario) {
        return ok(gson.toJson(this.usuarioService.registerUsuario(usuario))).build();
    }
    
    @Counted(unit = MetricUnits.NONE,
       name = "num_authenticate",
       absolute = true,
       displayName = "Autenticacion de usuarios",
       description = "Metrica para ver cuantas veces el metodo authenticateUsuario fue llamado",
       tags = {"auth=usuarios"})
    @Timed(name = "tiempo_authenticate",
       description = "Metrica para monitorear el tiempo de respuesta de la autenticación de usuarios.",
       unit = MetricUnits.SECONDS,
       absolute = true)
    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUsuario(UsuarioTo usuario) {
        return ok(gson.toJson(this.usuarioService.authenticateUsuario(usuario))).build();
    }


    @Authenticated
    @Counted(unit = MetricUnits.NONE,
       name = "num_update",
       absolute = true,
       displayName = "Update de contraseñas de usuarios",
       description = "Metrica para ver cuantas veces el metodo updatePassword fue llamado",
       tags = {"update=usuarios"})
    @Timed(name = "tiempo_update",
       description = "Metrica para monitorear el tiempo de respuesta de la update de contraseña de usuarios.",
       unit = MetricUnits.SECONDS,
       absolute = true)
    @PUT
    @Path("/update-password")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(UsuarioTo usuario) {
        return ok(gson.toJson(this.usuarioService.updatePasswordFromUsuario(usuario))).build();
    }
    

    @Counted(unit = MetricUnits.NONE,
       name = "num_get_all",
       absolute = true,
       displayName = "Get all usuarios",
       description = "Metrica para ver cuantas veces el metodo getAllUsuarios fue llamado",
       tags = {"get=usuarios"})
    @Timed(name = "tiempo_get_all",
       description = "Metrica para monitorear el tiempo de respuesta de un query de todos los usuarios registrados.",
       unit = MetricUnits.SECONDS,
       absolute = true)
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        return ok(gson.toJson(this.usuarioService.getAllUsuarios())).build();
    }

    @Counted(unit = MetricUnits.NONE,
       name = "num_delete",
       absolute = true,
       displayName = "Delete usuarios",
       description = "Metrica para ver cuantas veces el metodo deleteUsuario fue llamado",
       tags = {"delete=usuarios"})
    @Timed(name = "tiempo_delete",
       description = "Metrica para monitorear el tiempo de respuesta de una eliminación de cuenta de usuario",
       unit = MetricUnits.SECONDS,
       absolute = true)
    @PUT
    @Path("/{username}/delete")
    public Response deleteUsuario(@PathParam("username") String username) {
        return ok(gson.toJson(this.usuarioService.deleteUsuario(username))).build();
    }
}
