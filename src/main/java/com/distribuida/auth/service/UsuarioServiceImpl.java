package com.distribuida.auth.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import com.distribuida.auth.repository.IUsuarioRepository;
import com.distribuida.auth.repository.model.Usuario;
import com.distribuida.auth.to.UsuarioTo;



@ApplicationScoped
public class UsuarioServiceImpl implements IUsuarioService {


    @Inject
    private IUsuarioRepository usuarioRepo;


    @Override
    public String registerUsuario(UsuarioTo usuario) {
        String response = usuarioRepo.insertUsuario(usuarioFromUsuarioTo(usuario));
        return response;
    }

    @Override
    public String authenticateUsuario(UsuarioTo usuario) {
        Usuario toAuthenticate = usuarioRepo.searchUsuarioByUserName(usuario.username);
        if (toAuthenticate == null) return "Usuario no existe";
        else {
            if (verificarContrasenaBcrypt(usuario.password, toAuthenticate.getPassword())) return "Autenticado";
            else {
                return "Nombre de usuario o password incorrectos";
            }
        }
    }

    @Override
    public String updatePasswordFromUsuario(UsuarioTo usuario) {
        Usuario toUpdate = usuarioRepo.searchUsuarioByUserName(usuario.username);
        if (toUpdate == null) return "El usuario " + usuario.username + " no existe";
        else {
            if (verificarContrasenaBcrypt(usuario.password, toUpdate.getPassword())) return "El password debe ser diferente al actual";
            else {
                toUpdate.setPassword(generarHashBcrypt(usuario.password));
                return usuarioRepo.updateUsuario(toUpdate);
            }
        }
    }

    private Usuario usuarioFromUsuarioTo(UsuarioTo user) {
        Usuario usuario = new Usuario();
        usuario.setusername(user.username);
        usuario.setPassword(generarHashBcrypt(user.password));
        return usuario;
    }

    @Override
    public List<UsuarioTo> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepo.getAllUsuarios();
        List<UsuarioTo> usuariosTo = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            usuariosTo.add(usuarioToFromUsuario(usuarios.get(i)));
        }
        return usuariosTo;

    }

    private UsuarioTo usuarioToFromUsuario(Usuario usuario) {
        UsuarioTo ret = new UsuarioTo();
        ret.username = usuario.getusername();
        ret.password = usuario.getPassword();
        return ret;
    }

    @Override
    public String deleteUsuario(String username) {
        Usuario toDelete = usuarioRepo.searchUsuarioByUserName(username);
        if (toDelete==null) {
            return "Usuario no existe";
        }
        return usuarioRepo.deleteUsuario(toDelete);
    }


    public String generarHashBcrypt(String contrasena) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(contrasena, salt);
    }

    public boolean verificarContrasenaBcrypt(String contrasenaIngresada, String hashAlmacenado) {
        return BCrypt.checkpw(contrasenaIngresada, hashAlmacenado);
    }

    
}
