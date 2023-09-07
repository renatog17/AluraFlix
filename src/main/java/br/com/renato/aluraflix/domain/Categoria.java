package br.com.renato.aluraflix.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.renato.aluraflix.controller.dto.categoria.PostCategoria;
import br.com.renato.aluraflix.controller.dto.categoria.PutCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String cor;
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Video> videos = new ArrayList<Video>();

	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(String titulo, String cor) {
		super();
		this.titulo = titulo;
		this.cor = cor;
	}

	public Categoria(@Valid PostCategoria postCategoria) {
		this.titulo = postCategoria.titulo();
		this.cor = postCategoria.cor();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCor() {
		return cor;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void atualizarInformacoes(@Valid PutCategoria putCategoria) {
		if (putCategoria.titulo() != null) {
			this.titulo = putCategoria.titulo();
		}
		if (putCategoria.cor() != null) {
			this.cor = putCategoria.cor();
		}

	}

}
