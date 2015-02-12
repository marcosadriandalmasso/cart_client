package com.globant.carrito.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.carrito.StatusDto;

@RestController
public class ClientsService {
	
	public static final String USERNAME = "username";
	
	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("db");
	
	@RequestMapping(value = "/service/getClient", method = RequestMethod.GET)
	@ResponseBody
	public Clients getClient(String username) {
		EntityManager em = emf.createEntityManager();
		Clients c = em.find(Clients.class, username);
		em.close();
		emf.close();
		return c;
	}
	
	@RequestMapping(value = "/service/newClient", method=RequestMethod.POST)
	@ResponseBody
	public StatusDto createClient(@RequestBody ClientDto clientDto,HttpSession session) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("db");

		EntityManager em = emf.createEntityManager();
		
		System.out.println("name: "+clientDto.getName()+"\nusername: "+clientDto.getUsername()
				+"\npass: "+clientDto.getPassword()+"\nShipp: "+clientDto.getShippingAddress()+
				"\ntelephone: "+clientDto.getTelephone()+"\nemail: "+clientDto.getEmail()+"\nmailist: "+clientDto.isMailist());
		
			EntityTransaction tx = null;
			try {
				tx = em.getTransaction();
				tx.begin();
				Clients emc = new Clients(clientDto.getName(), clientDto.getUsername(), clientDto.getPassword(), clientDto.getShippingAddress(), clientDto.getTelephone(), clientDto.getEmail(), clientDto.isMailist());
				em.persist(emc);
				tx.commit();
				session.setAttribute(USERNAME, clientDto.getUsername());
				return new StatusDto(true);
			} catch (RuntimeException e) {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				return new StatusDto(false);
			} finally {
				em.close();
			}
	}
	
	@RequestMapping(value = "/service/removeClient", method = RequestMethod.GET)
	@ResponseBody
	public void removeClient(Clients client) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(client);
			em.persist(client);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}
}
