package com.example.carros.domain.service;

import com.example.carros.api.assembler.CarroAssembler;
import com.example.carros.domain.Carro;
import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.repository.CarroRepository;
import com.example.carros.domain.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarroService {

	private CarroAssembler mapper;
	private CarroRepository carroRepository;

	public List<CarroDTO> getCarros() {
		List<CarroDTO> list = mapper.toCollectonDTO(carroRepository.findAll());
		return list;
	}

	public Carro getCarroById(Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		return carro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		List<CarroDTO> carros = mapper.toCollectonDTO(carroRepository.findByTipo(tipo));
		return carros;
	}

	public CarroDTO inserir(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
		CarroDTO c = mapper.toDTO(carroRepository.save(carro));
		return c;
	}

	public Carro update(CarroDTO carroDTO, Long id) {
		// busca o carro no banco
		Optional<Carro> optional = carroRepository.findById(id);

		if (optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carroDTO.getNome());
			db.setTipo(carroDTO.getTipo());

			carroRepository.save(db);
			return db;
		} else {
			throw new ObjectNotFoundException("Id não encontrado! ");
		}

	}

	public boolean delet(Long id) {
		Optional<Carro>	carro = carroRepository.findById(id);
		
		if(carro.isPresent()) {
			carroRepository.deleteById(id);
			return true;
		}
		throw new ObjectNotFoundException("Objeto não encontrado");
	}

}
