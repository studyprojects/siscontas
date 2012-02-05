package net.studyprojects.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

@Entity
//@Entity(name="siscontas.tag")
//@Table(schema="siscontas")
public class Tag {

//	@SequenceGenerator(name="seq_tag", schema = "siscontas", sequenceName = "siscontas.seq_tag", allocationSize = 1)
	@Id
	@SequenceGenerator(name="seq_tag", sequenceName = "seq_tag", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_tag")
	private Long id;

	@Field(index=Index.TOKENIZED)
	private String nome;

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tag))
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
