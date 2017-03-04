package br.com.casadocodigo.estante;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.casadocodigo.registro.Usuario;
import lombok.Getter;

@Entity
public class Estante {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Usuario usuario;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "estante_id")
	private Set<Livro> livros = new HashSet<>();

	public boolean temLivros() {
		return livros.size() > 0;
	}

	public Collection<Livro> todosLivros() {
		return Collections.unmodifiableCollection(livros);
	}

	public void adicionar(Livro livro) {
		livros.add(livro);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
