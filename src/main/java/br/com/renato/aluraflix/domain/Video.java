package br.com.renato.aluraflix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.renato.aluraflix.controller.dto.DadosAtualizaoVideo;
import br.com.renato.aluraflix.controller.dto.DadosCadastroVideo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "videos")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	@ManyToOne
	@JsonIgnore
	private Categoria categoria;

	public Video() {
		super();
	}

	public Video(String titulo, String descricao, String url, Categoria categoria) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.categoria = categoria;
	}
	
	public Video(DadosCadastroVideo novoVideo) {
		this.titulo = novoVideo.titulo();
		this.descricao = novoVideo.descricao();
		this.url = novoVideo.url();
		Categoria categoria;
		if(novoVideo.categoriaId()!=null) {
			categoria = new Categoria();
			categoria.setId(novoVideo.categoriaId());
		}else {
			categoria = new Categoria();
			categoria.setId(1L);
		}
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrl() {
		return url;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void atulizarInformacoes(@Valid DadosAtualizaoVideo editaVideo) {
		if(editaVideo.descricao()!=null) {
			this.descricao = editaVideo.descricao();
		}
		if(editaVideo.titulo()!=null) {
			this.titulo = editaVideo.titulo();
		}
		if(editaVideo.url()!=null) {
			this.url = editaVideo.url();
		}
		if(editaVideo.categoriaId()!=null) {
			Categoria categoria = new Categoria();
			categoria.setId(editaVideo.categoriaId());
			this.categoria = categoria;
		}
		
	}
	
	

}
