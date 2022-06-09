package com.grupoM.BayerChallenge.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grupoM.BayerChallenge.dto.DiseaseDTO;
import com.grupoM.BayerChallenge.services.DiseaseService;

@RestController
@RequestMapping(value = "/diseases")
public class DiseaseResource {
	
	@Autowired
	private DiseaseService service;
	
	@GetMapping
	public ResponseEntity<Page<DiseaseDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy			
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<DiseaseDTO> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DiseaseDTO> findById(@PathVariable Long id){
		DiseaseDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<DiseaseDTO> insertDisease(@RequestBody DiseaseDTO dto){
		dto = service.insertDisease(dto);
		//insere no header da response a location do novo recurso adicionado
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DiseaseDTO> updateDisease(@PathVariable Long id ,@RequestBody DiseaseDTO dto){
		dto = service.updateDisease(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDisease(@PathVariable Long id){
		service.deleteDisease(id);
		return ResponseEntity.noContent().build();
	}
}
