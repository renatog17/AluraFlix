package br.com.renato.aluraflix.controller.dto.categoria;

import java.util.List;

import br.com.renato.aluraflix.controller.dto.DadosDetalhamentoVideo;
import br.com.renato.aluraflix.domain.Categoria;

public record GetVideosPorCategoria(
		Long id,
		String titulo,
		String cor,
		List<DadosDetalhamentoVideo> videos) {

	public GetVideosPorCategoria(Categoria categoria,
			List<DadosDetalhamentoVideo> videos){
		this(categoria.getId(), categoria.getTitulo(), categoria.getCor(), videos);
	}


}
