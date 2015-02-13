package com.globant.carrito.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.carrito.StatusDto;
import com.globant.carrito.client.Client;

@RestController
public class SecurityService {
	public static final String USERNAME = "username";

	@RequestMapping(value = "/service/login", method=RequestMethod.POST)
	@ResponseBody
	public StatusDto login(@RequestBody LoginDto dto,HttpSession session) {
	
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("db");

		EntityManager em = emf.createEntityManager();
		
		String username = dto.getUsername();
		
		try {
		    TypedQuery<Client> query = em.createQuery("from Client c where c.username = :username", Client.class);
		        query.setParameter("username", username);
		        Client client = query.getSingleResult();
		        boolean isValid = dto.getPassword().equals(client.getPassword());
		        if (isValid) {
		        	session.setAttribute(USERNAME, username);
		        }
		        return new StatusDto(isValid);
		} catch (NoResultException e) {
			return new StatusDto(false);
		} finally {
			em.close();
		}
	}
	
	@RequestMapping(value = "/service/logout", method=RequestMethod.POST)
	public void logout(HttpSession session) {
		session.invalidate();
	}
}