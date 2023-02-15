package com.distribuida.auth.repository;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.distribuida.auth.repository.model.Usuario;



@Transactional
@ApplicationScoped
public class UsuarioRepositoryImpl implements IUsuarioRepository {
    
    @PersistenceContext(unitName = "distribuida")
	private EntityManager em;

    @Override
    public String insertUsuario(Usuario user) {
        Usuario toRegister = searchUsuarioByUserName(user.getusername());
        if (toRegister != null) return "Usuario ya registrado"; 
        else 
        {
            em.persist(user);
            return "Usuario registrado con exito!";
        }
    }

    
    @Override
    public String updateUsuario(Usuario user) {
        em.merge(user);
        return "Password Actualizada";
    }


    
    @Override
    public Usuario searchUsuarioByUserName(String userName) {
        try {
            TypedQuery<Usuario> mq = em.createQuery("SELECT c FROM Usuario c WHERE c.username=:userName",Usuario.class);
		    mq.setParameter("userName", userName);
            return mq.getSingleResult();
        } catch (NoResultException no_result) {
            return null;
        }
		
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        TypedQuery<Usuario> tq = em.createQuery("SELECT c FROM Usuario c",Usuario.class);
		return tq.getResultList();
    }

    @Override
    public String deleteUsuario(Usuario user) {
        Usuario userDelete = em.contains(user) ? user : em.merge(user);
        em.remove(userDelete);
        return "Usuario eliminado";
    }

    
}
