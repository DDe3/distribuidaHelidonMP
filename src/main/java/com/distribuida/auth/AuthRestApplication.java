package com.distribuida.auth;



import com.distribuida.auth.config.ConfigTestController;
import com.distribuida.auth.controller.UsuarioRestController;
import com.distribuida.auth.metric.MetricController;



import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("/authapi/v1")
@ApplicationScoped
public class AuthRestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        // resources
        classes.add(HelloController.class);
        
        
        classes.add(ConfigTestController.class);
        
        
        
        classes.add(MetricController.class);
        classes.add(UsuarioRestController.class);
        
        
        
        return classes;
    }
}
