package br.com.sysmap.agenda.agenda_sysmap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")


public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "perfil")
	private String perfil;
	
	//@Enumerated(EnumType.STRING)
	//private Perfil perfil;
	
	@OneToMany(mappedBy = "usuario")
	private List<Compromisso> compromissos;
	
	@ManyToOne
	@JoinColumn (name = "equipe_id")
	private Usuario equipe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<Compromisso> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}

	public Usuario getEquipe() {
		return equipe;
	}

	public void setEquipe(Usuario equipe) {
		this.equipe = equipe;
	}

	
	
	
	
}
