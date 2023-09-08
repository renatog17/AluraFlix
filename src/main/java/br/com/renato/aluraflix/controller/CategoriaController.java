package br.com.renato.aluraflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.aluraflix.controller.dto.DadosDetalhamentoVideo;
import br.com.renato.aluraflix.controller.dto.categoria.GetCategoria;
import br.com.renato.aluraflix.controller.dto.categoria.GetVideosPorCategoria;
import br.com.renato.aluraflix.controller.dto.categoria.PostCategoria;
import br.com.renato.aluraflix.controller.dto.categoria.PutCategoria;
import br.com.renato.aluraflix.domain.Categoria;
import br.com.renato.aluraflix.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<?> getCategorias(@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "page", defaultValue = "0")int page){
		PageRequest of = PageRequest.of(page, size);
		return categoriaRepository.findAll(of).map(GetCategoria::new);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoria(@PathVariable Long id){
		Categoria categoria = categoriaRepository.getReferenceById(id);
		return ResponseEntity.ok(new GetCategoria(categoria));
	}
	
	@PostMapping()
	@Transactional
	public ResponseEntity<?> postCategoria(@RequestBody @Valid PostCategoria postCategoria, UriComponentsBuilder uriBuilder){
		Categoria categoria = new Categoria(postCategoria);
		categoriaRepository.save(categoria);
		var uri = uriBuilder.path("categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(categoria);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> putCategoria(@RequestBody @Valid PutCategoria putCategoria){
		Categoria categoria = categoriaRepository.getReferenceById(putCategoria.id());
		categoria.atualizarInformacoes(putCategoria);
		return ResponseEntity.ok(new GetCategoria(categoria));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
		categoriaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}/videos")
	public ResponseEntity<?> getVideosPorCategoria(@PathVariable Long id){
		Categoria categoria = categoriaRepository.getReferenceById(id);
		List<DadosDetalhamentoVideo> videosDto = categoria.getVideos().stream()
				.map(video -> {
					DadosDetalhamentoVideo videoDto = new DadosDetalhamentoVideo(video);
					return videoDto;
				}).toList();
		
		GetVideosPorCategoria videosPorCategoriaDTO = new GetVideosPorCategoria(categoria, videosDto);
		
		return ResponseEntity.ok(videosPorCategoriaDTO);
	}
	
}
