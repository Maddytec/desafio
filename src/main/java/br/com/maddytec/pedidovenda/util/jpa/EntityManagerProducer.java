package br.com.maddytec.pedidovenda.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	
	public EntityManagerProducer() {
	
		factory = Persistence.createEntityManagerFactory("PedidoPU");
	}
	
	@Produces @RequestScoped
	public EntityManager createEntitymanager(){
		return factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager manager){
		manager.close();
		System.out.println("manager close");
		
	}
}
