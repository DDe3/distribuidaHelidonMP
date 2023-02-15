package com.distribuida.auth.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;



import com.distribuida.auth.service.IUsuarioService;
import com.distribuida.auth.to.UsuarioTo;

import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.Response.ok;



@RequestScoped
@Path("/usuarios")
public class UsuarioRestController {

    @Inject
    private IUsuarioService usuarioService;

    
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUsuario(UsuarioTo usuario) {
        return ok(this.usuarioService.registerUsuario(usuario)).build();
    }

     
    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUsuario(UsuarioTo usuario) {
        return ok(this.usuarioService.authenticateUsuario(usuario)).build();
    }

    @PUT
    @Path("/update-password")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(UsuarioTo usuario) {
        return ok(this.usuarioService.updatePasswordFromUsuario(usuario)).build();
    }
    

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        return ok(this.usuarioService.getAllUsuarios()).build();
    }


    @PUT
    @Path("/{username}/update")
    public Response deleteUsuario(@PathParam("username") String username) {
        return ok(this.usuarioService.deleteUsuario(username)).build();
    }
}
