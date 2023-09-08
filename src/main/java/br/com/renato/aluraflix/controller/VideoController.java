package br.com.renato.aluraflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.renato.aluraflix.controller.dto.DadosAtualizaoVideo;
import br.com.renato.aluraflix.controller.dto.DadosCadastroVideo;
import br.com.renato.aluraflix.controller.dto.DadosDetalhamentoVideo;
import br.com.renato.aluraflix.domain.Video;
import br.com.renato.aluraflix.repository.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired
	VideoRepository videoRepository;

	@GetMapping
	public Page<?> getVideos(@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "page", defaultValue = "0") int page){
		
		PageRequest of = PageRequest.of(page, size);
		return videoRepository.findAll(of).map(DadosDetalhamentoVideo::new);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getVideos(@RequestParam(name = "search") String titulo){
		List<DadosDetalhamentoVideo> videos = videoRepository.findByTitulo(titulo).stream().map(
				video ->{
					DadosDetalhamentoVideo videoDto = new DadosDetalhamentoVideo(video);
					return videoDto;
				}).toList();
		return ResponseEntity.ok(videos);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getVideo(@PathVariable Long id){
		Video referenceById = videoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoVideo(referenceById));
	}
	
	@PostMapping()
	@Transactional
	public ResponseEntity<?> postVideo(@RequestBody @Valid DadosCadastroVideo novoVideo, UriComponentsBuilder uriBuilder){
		Video video = new Video(novoVideo);
		videoRepository.save(video);
		var uri = uriBuilder.path("videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(video);
	}
	
	@PutMapping("{id}")
	@Transactional
	public void putVideo(@RequestBody @Valid DadosAtualizaoVideo editaVideo){
		Video video = videoRepository.getReferenceById(editaVideo.id());
		video.atulizarInformacoes(editaVideo);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public void deleteVideo(@PathVariable Long id) {
		videoRepository.deleteById(id);
	}
}
