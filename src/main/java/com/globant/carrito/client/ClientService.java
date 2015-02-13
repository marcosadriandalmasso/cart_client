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
public class ClientService {
	
	public static final String USERNAME = "username";
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	
	@RequestMapping(value = "/service/getClient", method = RequestMethod.GET)
	@ResponseBody
	public Client getClient(String username) {
		EntityManager em = emf.createEntityManager();
		Client c = em.find(Client.class, username);
		em.close();
		emf.close();
		return c;
	}
	
	@RequestMapping(value = "/service/newClient", method=RequestMethod.POST)
	@ResponseBody
	public StatusDto newClient(@RequestBody ClientDto clientDto,HttpSession session) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("db");

		EntityManager em = emf.createEntityManager();
		
			EntityTransaction tx = null;
			try {
				tx = em.getTransaction();
				tx.begin();
				Client emc = new Client(clientDto.getName(), clientDto.getUsername(), clientDto.getPassword(), clientDto.getShippingAddress(), clientDto.getTelephone(), clientDto.getEmail(), clientDto.isMailist());
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
	public void removeClient(Client client) {
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
