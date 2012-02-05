package net.studyprojects.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import net.studyprojects.financas.modelo.Pagamento;


public class PagamentoDAO {

	private final DAO<Pagamento> dao;
	
	public PagamentoDAO(EntityManager em) {
		dao = new DAO<Pagamento>(em,Pagamento.class);
	}

	public void adiciona(Pagamento t) {
		dao.adiciona(t);
	}

	public Pagamento busca(Integer id) {
		return dao.busca(id);
	}

	public List<Pagamento> lista() {
		return dao.lista();
	}

	public void remove(Pagamento t) {
		dao.remove(t);
	}

	public void altera(Pagamento t) {
		dao.altera(t);
	}	
}
