package com.distribuida.auth.repository;

import java.util.List;

import com.distribuida.auth.repository.model.Usuario;





public interface IUsuarioRepository {

    String insertUsuario(Usuario user);
    String updateUsuario(Usuario user);
    Usuario searchUsuarioByUserName(String userName);
    List<Usuario> getAllUsuarios();
    String deleteUsuario(Usuario user);

}
