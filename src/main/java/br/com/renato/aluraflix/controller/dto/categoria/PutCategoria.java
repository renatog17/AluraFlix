package br.com.renato.aluraflix.controller.dto.categoria;

import jakarta.validation.constraints.NotNull;

public record PutCategoria(@NotNull Long id,
		String titulo,
		String cor){

}
