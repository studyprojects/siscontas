package net.studyprojects.financas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.studyprojects.financas.modelo.Tag;


public class TagDAO {

	private EntityManager em;

	public TagDAO(EntityManager em) {
		this.em = em;
	}

	public Tag adicionaOuBuscaTagComNome(String nome) {
		String jpql = "select t from Tag t where t.nome = :pNome";
		TypedQuery<Tag> query = em.createQuery(jpql, Tag.class);
		query.setParameter("pNome", nome);
		Tag tag;
		try {
			tag = query.getSingleResult();
		} catch (NoResultException e) {
			tag = new Tag();
			tag.setNome(nome);
			em.persist(tag);
		}
		return tag;
	}

}
