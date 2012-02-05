package net.studyprojects.financas.util;

import javax.persistence.EntityManager;

import net.studyprojects.financas.infra.JPAUtil;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;


public class IndexaTagsCadastradas {

	public static void main(String[] args) throws InterruptedException {
		EntityManager em = new JPAUtil().getEntityManager();
		FullTextEntityManager fullTextEntityManager = Search
				.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer().startAndWait();
	}
}
