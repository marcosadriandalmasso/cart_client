package com.globant.carrito.product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.carrito.StatusDto;
import com.globant.carrito.cart.Cart;
import com.globant.carrito.client.Client;
import com.globant.carrito.security.SecurityService;

@RestController
public class ItemService {

	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("db");
	
	@RequestMapping(value = "/service/newItem/{prodId}", method = RequestMethod.GET)
	@ResponseBody
	public StatusDto newItem(@PathVariable("prodId") Integer prodId,HttpSession session) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			Cart cart = getCart(session, em);

			boolean found = false; // Flag for checking if the item to add already exists in cart.
			for(Item i : cart.getItems()){
				if(i.getProduct().getId() == prodId){
					i.setQty(i.getQty()+1);
					found = true;
					break;
				}
			}
			if(!found){ // If the item to add is not currently in the cart, creates a new instance
				Product product = em.find(Product.class, prodId);
//				if (product == null) ..... test con producto que no existe
				cart.addItem(new Item(product.getPrice(), product, 1));
			}
			em.persist(cart);
			tx.commit();
			return new StatusDto(true);
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			return new StatusDto(false);
		} finally {
			em.close();
		}
	}
	
	@RequestMapping(value = "/service/removeItem/{prodId}", method = RequestMethod.GET)
	@ResponseBody
	public StatusDto removeItem(@PathVariable("prodId") Integer prodId, HttpSession session) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			Cart cart = getCart(session, em);

			for(Item i : cart.getItems()){
				if (i.getProduct().getId() == prodId && (i.getQty() > 0)) {
					i.setQty(i.getQty()-1);
					if(i.getQty() == 0){ // If the item removed has quantity = 0, then remove it from client´s cart
						cart.removeItem(i);
					}
					break;
				}
			}
			em.persist(cart);
			tx.commit();
			return new StatusDto(true);
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			return new StatusDto(false);
		} finally {
			em.close();
		}
	}
	
	@RequestMapping(value = "/service/getCart", method = RequestMethod.GET)
	@ResponseBody
	public ItemResponse getCart(HttpSession session) {
		EntityManager em = emf.createEntityManager();
		Cart c = getCart(session, em);
		ItemResponse resp = new ItemResponse();
		for(Item i : c.getItems()){
			resp.getResults().add(new ItemDto(i.getQty(), i.getProduct().getName(), i.getPrice(), i.getProduct().getId()));
		}
		em.close();
		return resp;
	}
	
	private String getUsername(HttpSession session) {
		return (String)session.getAttribute(SecurityService.USERNAME);
	}
	
	/**
	 * 
	 * @param session
	 * @param em
	 * @return The cart assigned to the Client. If not found, it creates a new one.
	 */
	private Cart getCart(HttpSession session, EntityManager em) {
		try {
			TypedQuery<Cart> query = em.createQuery("select c from Cart c where c.client.username = :username and c.status = true", Cart.class);
			query.setParameter("username", getUsername(session));
			return query.getSingleResult();
		} catch (NoResultException e) {
			TypedQuery <Client> query = em.createQuery("select c from Client c where c.username = :username", Client.class);
			query.setParameter("username", getUsername(session));
			return new Cart(query.getSingleResult());
		}
	}
	
	@RequestMapping(value = "/service/checkout", method = RequestMethod.GET)
	@ResponseBody
	public StatusDto checkOut(HttpSession session) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		
		try {			
			tx = em.getTransaction();
			tx.begin();
			
			Cart cart = getCart(session, em);
			cart.setStatus(false);
			em.persist(cart);
			tx.commit();
			return new StatusDto(true);
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			return new StatusDto(false);
		} finally {
			em.close();
		}
	}
}