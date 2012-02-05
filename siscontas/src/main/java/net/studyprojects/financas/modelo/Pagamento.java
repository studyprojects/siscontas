package net.studyprojects.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMin;

@Entity
//@Entity(name="siscontas.pagamento")
//@Table(schema="siscontas")
public class Pagamento {

//	@SequenceGenerator(name="seq_pagamento", schema = "siscontas", sequenceName = "siscontas.seq_pagamento", allocationSize = 1)
	@Id
	@SequenceGenerator(name="seq_pagamento", sequenceName = "seq_pagamento", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_pagamento")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private FormaPagamento forma;

	@DecimalMin(value="0.01")
	private BigDecimal valor;

	private Calendar dataPagamento;

	@OneToOne
	private Conta conta;
	
	@ManyToOne
	private Pagador pagador;

	public Integer getId() {
		return id;
	}

	public FormaPagamento getForma() {
		return forma;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setPagador(Pagador pagador) {
		this.pagador = pagador;
	}

	public Pagador getPagador() {
		return pagador;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getConta() {
		return conta;
	}

}
