package com.lojapet.petfi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.lojapet.petfi.dto.PetDTO;
import com.lojapet.petfi.services.PetService;

@RestController
@RequestMapping(path = "/pets")
@CrossOrigin("*")
public class PetController {

	@Autowired
	private PetService petService;

	@GetMapping
	public ResponseEntity<Page<PetDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<PetDTO> list = petService.findAllPaged(pageRequest);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(path = "/cities")
	public ResponseEntity<List<String>> findAllCities(){
		List<String> list = petService.findAllCities();
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/cityWanted/{isWanted}/{city}")
	public ResponseEntity<Page<PetDTO>> findAllPetsWantedAndCity(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, @PathVariable Boolean isWanted,
			@PathVariable String city) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<PetDTO> list = petService.findAllPetsWantedAndCity(isWanted, city, pageRequest);
		System.out.println(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(path = "/wanted/{isWanted}")
	public ResponseEntity<Page<PetDTO>> findAllPetsWantedAndCity(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@PathVariable Boolean isWanted){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<PetDTO> list = petService.findAllPetsWanted(isWanted, pageRequest);
		System.out.println(list);
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
		PetDTO dto = petService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(path = "/name/{name}")
	public ResponseEntity<Page<PetDTO>> findAllByName(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, @PathVariable String name) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<PetDTO> list = petService.findAllByName(name, pageRequest);
		return ResponseEntity.ok(list);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/{clientId}")
	public ResponseEntity<PetDTO> insert(@PathVariable Long clientId, @RequestBody PetDTO dto) {
		dto = petService.insert(clientId, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "/{id}")
	public ResponseEntity<PetDTO> update(@PathVariable Long id, @RequestBody PetDTO dto) {
		dto = petService.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		petService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
