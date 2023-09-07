package br.com.renato.aluraflix.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroVideo(
		@NotBlank
		String titulo,
		@NotBlank
		String descricao,
		@NotBlank
		String url,
		Long categoriaId) {

}
