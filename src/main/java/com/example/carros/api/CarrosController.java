package com.example.carros.api;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.carros.api.assembler.CarroAssembler;
import com.example.carros.domain.Carro;
import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.service.CarroService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	private ModelMapper mapper;
	private CarroService service;
	private CarroAssembler assembler;
	
	public static final String ID = "/{id}";
	
	@GetMapping()
	public ResponseEntity get() {
		List<CarroDTO> carros = service.getCarros();
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping(ID)
	public ResponseEntity get(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(mapper.map(service.getCarroById(id), CarroDTO.class));
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}
	
	@PostMapping
	public ResponseEntity post(@RequestBody Carro carro) {
		try {
			CarroDTO c = service.inserir(carro);
			return c != null ?
					ResponseEntity.created(getUri(c.getId())).build() :
						ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping(ID)
	public ResponseEntity<CarroDTO> update(@PathVariable("id") Long id,  @RequestBody CarroDTO carroDTO) {
		
		Carro c = service.update(carroDTO, id);
		
		return ResponseEntity.ok().body(assembler.toDTO(c));
	}
	
	@DeleteMapping(ID)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = service.delet(id);
		return ok ? 
				ResponseEntity.ok().build() :
				ResponseEntity.notFound().build();
	}

}
