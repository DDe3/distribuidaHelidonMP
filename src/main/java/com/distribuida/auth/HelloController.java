package com.distribuida.auth;

import java.util.Optional;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.helidon.security.Principal;
import io.helidon.security.SecurityContext;

/**
 *
 */

@Path("/hello")
@Singleton
public class HelloController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext context) {
        Optional<Principal> userPrincipal = context.userPrincipal();
        return "Hello, " + userPrincipal.get().getName() + "!";
    }

}


