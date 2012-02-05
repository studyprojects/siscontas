package net.studyprojects.financas.modelo;

import java.util.Collection;

//@Entity(name="siscontas.pagante")
public class Pagante {

//	@Id
//	@GeneratedValue
	private Integer id;

//	@OneToOne
	private Pagador pagador;
	
//	@OneToMany(mappedBy="pagante")
	private Collection<Pagamento> pagamentos;

	public Integer getId() {
		return id;
	}

	public Pagador getPagador() {
		return pagador;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPagador(Pagador pagador) {
		this.pagador = pagador;
	}

	public void setPagamentos(Collection<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public Collection<Pagamento> getPagamentos() {
		return pagamentos;
	}

}
