package br.ufrn.imd.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericDAO<T> implements IDataAcess<T> {

	private final static String PERSISTENCE_NAME = "VitrineApp";
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public GenericDAO(){
		entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_NAME);
		entityManager = entityManagerFactory
				.createEntityManager();
	}

	public T salvar(T object) {
		entityManager.getTransaction().begin();
		entityManager.persist(object);
		entityManager.getTransaction().commit();
		return object;
	}

	public T atualizar(T object) {
		entityManager.getTransaction().begin();
		object = entityManager.merge(object);
		entityManager.getTransaction().commit();
		return object;
	}
	
	public T remover(T object){
		entityManager.getTransaction().begin();
		entityManager.remove(object);
		entityManager.getTransaction().commit();
		return object;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void fechar(){
		entityManager.close();
		entityManagerFactory.close();
	}

}
