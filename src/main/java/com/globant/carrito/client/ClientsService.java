package com.globant.carrito.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientsService {
	
	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("db");
	
	@RequestMapping(value = "/service/getClient", method = RequestMethod.GET)
	@ResponseBody
	public Clients getClient(String username) {
		EntityManager em = emf.createEntityManager();
		Clients c = em.find(Clients.class, username);
		em.close();
		return c;
	}
	
	@RequestMapping(value = "/service/newClient", method = RequestMethod.GET)
	@ResponseBody
	public Clients newClient(String name, String username, String password,
			String shippingAddress, String telephone, String email,
			boolean mailist) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Clients emc = new Clients(name, username, password, shippingAddress, telephone, email, mailist);
			em.persist(emc);
			tx.commit();
			return emc;
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
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
