package net.studyprojects.financas.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import net.studyprojects.financas.dao.ContaDAO;
import net.studyprojects.financas.dao.PagadorDAO;
import net.studyprojects.financas.dao.PagamentoDAO;
import net.studyprojects.financas.dao.TagDAO;
import net.studyprojects.financas.infra.JPAUtil;
import net.studyprojects.financas.modelo.Conta;
import net.studyprojects.financas.modelo.Tag;


@ViewScoped
@ManagedBean
public class ContasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Conta conta = new Conta();
	private List<Conta> contas;
	private String tags;

	public void grava() {

		System.out.println("Gravando a conta");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		PagadorDAO pagDao = new PagadorDAO(em);

		System.out.println("Associando pagadores...");

		conta.setPagadores(pagDao.lista());

		ContaDAO dao = new ContaDAO(em);

		gravaEAssociaAsTags(em);
		
		if (conta.getId() == null) {
			dao.adiciona(conta);
		} else {
			dao.altera(conta);
		}
//		contas = dao.lista();
		contas = dao.contasComTags();

		em.getTransaction().commit();

		em.close();

		limpaFormularioDoJSF();
	}

	public void remove() {
//	TODO: Para remover uma conta, � necess�rio propagar toda a regra de neg�cio: Remover pagtos e tudo mais que estiver associado � mesma. Prefiro adicionar campo no bd indicando 'excluida'.
		System.out.println("Removendo a conta");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		ContaDAO dao = new ContaDAO(em);

		Conta contaParaRemover = dao.busca(this.conta.getId());

		if (contaParaRemover.getPagamento() != null) {

			PagamentoDAO pgDao = new PagamentoDAO(em);

			pgDao.remove(contaParaRemover.getPagamento());
		}
		
		dao.remove(contaParaRemover);

//		contas = dao.lista();
		contas = dao.contasComTags();

		em.getTransaction().commit();

		em.close();

		limpaFormularioDoJSF();

	}

	public Conta getConta() {
		if (conta.getDataVencimento() == null) {
			conta.setDataVencimento(Calendar.getInstance());
		}
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getContas() {
		System.out.println("Listando as contas");

		if (contas == null) {
			EntityManager em = new JPAUtil().getEntityManager();
			ContaDAO dao = new ContaDAO(em);
//			contas = dao.lista();
			contas = dao.contasComTags();
			em.close();
		}
		return contas;
	}

	public List<Conta> getContasAPagar() {
		System.out.println("Listando as contas a pagar");

		if (contas == null) {
			EntityManager em = new JPAUtil().getEntityManager();
			ContaDAO dao = new ContaDAO(em);
//			contas = dao.listaContasAPagar();
//			contas = dao.lista();
			contas = dao.contasComTags();
			em.close();
		}
		return contas;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return tags;
	}
	
	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.conta = new Conta();
		this.tags = null;
	}

	/**
	 * Esse metodo persiste as tags e as associa a uma conta.
	 * Para que n�o haja duplicidade na associa��o, foi 
	 * adicionada esta verifica��o. 
	 * @param em
	 */
	private void gravaEAssociaAsTags(EntityManager em) {

		String[] nomeDasTags = this.tags.split(" ");
		
		TagDAO tagDAO = new TagDAO(em);

		for (String nome : nomeDasTags) {
		
			Tag tag = tagDAO.adicionaOuBuscaTagComNome(nome);

			if (!conta.getTags().contains(tag)) {
				conta.getTags().add(tag);				
			}
		}
	}

}
