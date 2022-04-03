package com.example.carros.api.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.carros.domain.Carro;
import com.example.carros.domain.dto.CarroDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CarroAssembler {
	
	private ModelMapper modelMapper;
	
	
	public CarroDTO toDTO(Carro carro) {
		return modelMapper.map(carro, CarroDTO.class);
	}
	
	public Carro toDomain(CarroDTO carroDto) {
		return modelMapper.map(carroDto, Carro.class);		
	}
	
	public List<CarroDTO> toCollectonDTO(List<Carro> carros) {
		return carros.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	public List<Carro> toCollectionDomain(List<CarroDTO> carrosDTO) {
		return carrosDTO.stream()
				.map(this::toDomain)
				.collect(Collectors.toList());		
	}
	
	public static CarroDTO create(Carro carro) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(carro, CarroDTO.class);
	}

}