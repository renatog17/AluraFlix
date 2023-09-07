package br.com.renato.aluraflix.controller.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record PostCategoria(@NotBlank String titulo,
		@NotBlank String cor) {

}
