package com.globant.carrito.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.carrito.StatusDto;
import com.globant.carrito.client.Clients;

@RestController
public class SecurityService {

	@RequestMapping(value = "/service/login", method=RequestMethod.POST)
	@ResponseBody
	public StatusDto login(@RequestBody LoginDto dto) {
		
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("db");

		EntityManager em = emf.createEntityManager();
		
		String username = dto.getUsername();
		
		TypedQuery<Clients> query = em.createQuery("from Clients c where c.username : username", Clients.class);
		query.setParameter("username", username);
		
		if(query.getSingleResult() != null) {
			Clients client = query.getSingleResult();
			em.close();
			return new StatusDto(dto.getUsername().equals(client.getUserName())
					&& dto.getPassword().equals(client.getPassword()));
		}else{
			return new StatusDto(false);
		}
	}
	
	@RequestMapping(value = "/service/logout", method=RequestMethod.POST)
	public void logout(HttpSession session) {
		session.invalidate();
	}
}