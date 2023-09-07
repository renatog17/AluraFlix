package br.com.renato.aluraflix.controller.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaoVideo(@NotNull Long id,
		String titulo,
		String descricao,
		String url,
		Long categoriaId) {

}
