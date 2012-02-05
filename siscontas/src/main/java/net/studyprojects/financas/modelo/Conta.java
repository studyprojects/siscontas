package net.studyprojects.financas.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMin;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

//@Entity(name="conta")
//@Table(schema="siscontas")
@Entity
@Indexed
public class Conta {

//	@SequenceGenerator(name="seq_conta", schema="siscontas", sequenceName = "seq_conta", allocationSize = 1)
	@Id
	@SequenceGenerator(name="seq_conta", sequenceName = "seq_conta", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_conta")
	private Integer id;

	private String titulo;

	private Calendar dataVencimento;

	@DecimalMin(value="0.01")
	private BigDecimal valor;

	@OneToOne(mappedBy="conta")
	private Pagamento pagamento;

	@ManyToMany
//	@JoinTable
//	(name="conta_pagador", 
//			joinColumns={@JoinColumn(name="conta.id")},
//			inverseJoinColumns={@JoinColumn(name="pagador.id")})
	private Collection<Pagador> pagadores;

	@ManyToMany
//	@JoinTable
//	(name="conta_tag",
//			joinColumns={@JoinColumn(name="conta.id")},
//			inverseJoinColumns={@JoinColumn(name="tag.id")})
	@IndexedEmbedded
	private List<Tag> tags = new ArrayList<Tag>();

	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Collection<Pagador> getPagadores() {
		return pagadores;
	}

	public void setPagadores(Collection<Pagador> pagadores) {
		this.pagadores = pagadores;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Tag> getTags() {
		return tags;
	}

}
