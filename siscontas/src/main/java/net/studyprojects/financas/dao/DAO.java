package net.studyprojects.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class DAO<T> {

	private final Class<T> classe;
	private final EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;

	}
	
	public void adiciona(T t) {
		this.em.persist(t);
	}
	
	public void remove(T t) {
		this.em.remove(t);
	}
	
	public T busca(Integer id) {
		return this.em.find(classe,id);
	}
	
	public List<T> lista(){
		return this.em.createQuery("from "+classe.getName()).getResultList();
	}
	
	public void altera(T t) {
		this.em.merge(t);
	}
	
}
