package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDao implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager manager;
	
	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuario = manager.createQuery("select u from Usuario u where u.email= :email")
			.setParameter("email", email)
			.getResultList();
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuario "+email+" não foi encontrado");
		}
		return usuario.get(0);
	}

}
