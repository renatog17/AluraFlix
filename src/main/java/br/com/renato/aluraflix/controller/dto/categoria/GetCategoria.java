package br.com.renato.aluraflix.controller.dto.categoria;

import br.com.renato.aluraflix.domain.Categoria;

public record GetCategoria(
		Long id,
		String titulo,
		String cor){

	public GetCategoria(Categoria categoria) {
		this(categoria.getId(),
				categoria.getTitulo(),
				categoria.getCor()
				);
		
	}
}
