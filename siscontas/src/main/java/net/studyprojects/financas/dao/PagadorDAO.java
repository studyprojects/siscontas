package net.studyprojects.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import net.studyprojects.financas.modelo.Pagador;


public class PagadorDAO {

	private final DAO<Pagador> dao;

	public PagadorDAO(EntityManager em) {
		dao = new DAO<Pagador>(em, Pagador.class);
	}
	
	public void adiciona(Pagador t) {
		dao.adiciona(t);
	}

	public Pagador busca(Integer id) {
		return dao.busca(id);
	}

	public List<Pagador> lista() {
		return dao.lista();
	}

	public void remove(Pagador t) {
		dao.remove(t);
	}

	public void altera(Pagador t) {
		dao.altera(t);
	}	
	
}
