package net.studyprojects.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.studyprojects.financas.modelo.Conta;


public class ContaDAO {

	private final DAO<Conta> dao;
	private final EntityManager em;
	
	public ContaDAO(EntityManager em) {
		this.em = em;
		dao = new DAO<Conta>(em,Conta.class);
	}

	public void adiciona(Conta t) {
		dao.adiciona(t);
	}

	public Conta busca(Integer id) {
		return dao.busca(id);
	}

	public List<Conta> lista() {
		return dao.lista();
	}

	public void remove(Conta t) {
		dao.remove(t);
	}

	public void altera(Conta t) {
		dao.altera(t);
	}

	public List<Conta> contasComTags() {

		String jpql = "select distinct c from Conta c left join fetch c.tags order by c ";
		Query query = this.em.createQuery(jpql);
		System.out.println(query.getResultList().size());
		return query.getResultList();
	}

//	public List<Conta> listaContasAPagar() {
//		
//		String jpql = "select c from Conta c "
//					+ "where c.pagamento IS NULL";
//		Query query = this.em.createQuery(jpql);
//		return query.getResultList();
//	}

}
