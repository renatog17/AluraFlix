package br.com.renato.aluraflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.aluraflix.domain.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

	List<Video> findByTitulo(String titulo);
}
