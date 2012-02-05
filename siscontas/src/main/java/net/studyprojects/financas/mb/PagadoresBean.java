package net.studyprojects.financas.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import net.studyprojects.financas.dao.PagadorDAO;
import net.studyprojects.financas.infra.JPAUtil;
import net.studyprojects.financas.modelo.Pagador;


@ViewScoped
@ManagedBean
public class PagadoresBean {

	private Pagador pagador = new Pagador();
	private List<Pagador> pagadores;

	public void grava() {

		System.out.println("Gravando o pagador");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		PagadorDAO dao = new PagadorDAO(em);

		if (pagador.getId() == null) {
			dao.adiciona(pagador);
		} else {
			dao.altera(pagador);
		}
		pagadores = dao.lista();

		em.getTransaction().commit();

		em.close();

		limpaFormularioDoJSF();
	}

	public void remove() {
		System.out.println("Removendo o pagador");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		PagadorDAO dao = new PagadorDAO(em);

		Pagador pagadorParaRemover = dao.busca(this.pagador.getId());

		dao.remove(pagadorParaRemover);

		pagadores = dao.lista();

		em.getTransaction().commit();

		em.close();

		limpaFormularioDoJSF();

	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.pagador = new Pagador();
	}

	public Pagador getPagador() {
		return pagador;
	}

	public List<Pagador> getPagadores() {
		System.out.println("Listando os pagadores");

		if (pagadores == null) {
			EntityManager em = new JPAUtil().getEntityManager();
			PagadorDAO dao = new PagadorDAO(em);
			pagadores = dao.lista();
			em.close();
		}
		return pagadores;
	}

	public void setPagador(Pagador pagador) {
		this.pagador = pagador;
	}

	public void setPagadores(List<Pagador> pagadores) {
		this.pagadores = pagadores;
	}
}
