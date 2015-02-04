package com.globant.carrito.product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsService {
	
	public ItemsService() {
		
	}

	protected EntityManagerFactory emf;
	
	public ItemsService(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	@RequestMapping(value = "/service/getItem", method = RequestMethod.GET)
	@ResponseBody
	public Items getItem(String name) {
		EntityManager em = emf.createEntityManager();
		Items i = em.find(Items.class, name);
		em.close();
		return i;
	}
	
	@RequestMapping(value = "/service/newItem", method = RequestMethod.GET)
	@ResponseBody
	public Items newItem(double price, String description) {
		EntityManager em = emf.createEntityManager();
		Items emi = new Items(price, description);
		em.persist(emi);
		em.close();
		return emi;
	}
	
	@RequestMapping(value = "/service/removeItem", method = RequestMethod.GET)
	@ResponseBody
	public void removeUser(String name) {
		EntityManager em = emf.createEntityManager();
		Items emi = getItem(name);
		if(emi != null) {
			em.remove(emi);
		}
		em.persist(emi);
		em.close();
	}
}