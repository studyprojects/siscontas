package net.studyprojects.financas.modelo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
//@Entity(name="siscontas.pagador")
//@Table(schema="siscontas")
public class Pagador {

//	@SequenceGenerator(name="seq_pagador", schema = "siscontas", sequenceName = "siscontas.seq_pagador", allocationSize = 1)
	@Id
	@SequenceGenerator(name="seq_pagador", sequenceName = "seq_pagador", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_pagador")
	private Integer id;

	private String nome;

	@ManyToMany(mappedBy="pagadores")
	private Collection<Conta> contas;

	@OneToMany(mappedBy="pagador")
	private Collection<Pagamento> pagamentos;
	
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Conta> getContas() {
		return contas;
	}

	public void setContas(Collection<Conta> contas) {
		this.contas = contas;
	}

	public Collection<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Collection<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

}
