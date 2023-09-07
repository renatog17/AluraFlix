package br.com.renato.aluraflix.controller.dto;

import br.com.renato.aluraflix.domain.Video;

public record DadosDetalhamentoVideo(
		Long id,
		String titulo,
		String descricao, 
		String url,
		Long categoriaId) {

	public DadosDetalhamentoVideo(Video video) {
		this(video.getId(),
				video.getTitulo(),
				video.getDescricao(),
				video.getUrl(),
				video.getCategoria().getId());
	}
}
