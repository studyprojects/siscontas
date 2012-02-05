package net.studyprojects.financas.mb;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import net.studyprojects.financas.dao.ContaDAO;
import net.studyprojects.financas.dao.PagadorDAO;
import net.studyprojects.financas.dao.PagamentoDAO;
import net.studyprojects.financas.infra.JPAUtil;
import net.studyprojects.financas.modelo.Conta;
import net.studyprojects.financas.modelo.FormaPagamento;
import net.studyprojects.financas.modelo.Pagador;
import net.studyprojects.financas.modelo.Pagamento;


@ViewScoped
@ManagedBean
public class PagamentosBean {

	private Pagamento pagamento = new Pagamento();
	private List<Pagamento> pagamentos;
	private Integer contaId;
	private Integer pagadorId;
	
	public void grava() {

		System.out.println("Gravando o pagamento");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		System.out.println("Associando conta...");
		
		ContaDAO contaDao = new ContaDAO(em);

		Conta conta = contaDao.busca(contaId);

		pagamento.setConta(conta);
		
		System.out.println("Associando pagador...");

		PagadorDAO pgdrDao = new PagadorDAO(em);
		
		Pagador pagador = pgdrDao.busca(pagadorId);
		
		pagamento.setPagador(pagador);
		
		PagamentoDAO dao = new PagamentoDAO(em);

		if (pagamento.getId() == null) {
			dao.adiciona(pagamento);
		} else {
			dao.altera(pagamento);
		}
		pagamentos = dao.lista();

		em.getTransaction().commit();

		em.close();

		limpaFormularioDoJSF();
	}

	public void remove() {
		System.out.println("Removendo o pagamento");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		PagamentoDAO dao = new PagamentoDAO(em);

		Pagamento pagamentoParaRemover = dao.busca(this.pagamento.getId());

		dao.remove(pagamentoParaRemover);

		pagamentos = dao.lista();

		em.getTransaction().commit();

		em.close();

		limpaFormularioDoJSF();

	}

	public Pagamento getPagamento() {
		if (pagamento.getDataPagamento() == null) {
			pagamento.setDataPagamento(Calendar.getInstance());
		}
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getPagamentos() {
		System.out.println("Listando os pagamentos");

		if (pagamentos == null) {
			EntityManager em = new JPAUtil().getEntityManager();
			PagamentoDAO dao = new PagamentoDAO(em);
			pagamentos = dao.lista();
			em.close();
		}
		return pagamentos;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.pagamento = new Pagamento();
		this.contaId = 0;
		this.pagadorId = 0;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setPagadorId(Integer pagadorId) {
		this.pagadorId = pagadorId;
	}

	public Integer getPagadorId() {
		return pagadorId;
	}
	
	public FormaPagamento[] getFormasDePagamento() {
		return FormaPagamento.values();
	}
	
}
