package br.com.renato.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.aluraflix.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
