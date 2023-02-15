package com.distribuida.auth.service;

import java.util.List;

import com.distribuida.auth.to.UsuarioTo;


public interface IUsuarioService {
    
    String registerUsuario(UsuarioTo usuario);
    String authenticateUsuario(UsuarioTo usuario);
    String updatePasswordFromUsuario(UsuarioTo usuario);
    List<UsuarioTo> getAllUsuarios();
    String deleteUsuario(String username);
}
