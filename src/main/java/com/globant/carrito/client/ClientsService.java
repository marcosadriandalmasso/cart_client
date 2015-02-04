package com.globant.carrito.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientsService {

	public ClientsService() {
		
	}
	protected EntityManagerFactory emf;
	
	public ClientsService(EntityManagerFactory emf){
		this.emf = emf;
	}
	
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
		Clients emc = new Clients(name, username, password, shippingAddress, telephone, email, mailist);
		em.persist(emc);
		em.close();
		return emc;
	}
	
	@RequestMapping(value = "/service/removeClient", method = RequestMethod.GET)
	@ResponseBody
	public void removeUser(String username) {
		EntityManager em = emf.createEntityManager();
		Clients emc = getClient(username);
		if(emc != null) {
			em.remove(emc);
		}
		em.persist(emc);
		em.close();
	}
}
