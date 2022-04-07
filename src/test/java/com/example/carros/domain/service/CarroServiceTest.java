package com.example.carros.domain.service;

import com.example.carros.CarroMother;
import com.example.carros.api.assembler.CarroAssembler;
import com.example.carros.domain.model.Carro;
import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.repository.CarroRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroServiceTest {

    @InjectMocks
    private CarroService service;

    @Mock
    private CarroRepository repository;

    @Mock
    private CarroAssembler mapper;

    private Carro car = CarroMother.getCarro();
    private CarroDTO dto = CarroMother.getDTO();
    private Optional<Carro> obj = CarroMother.getOptinalCar();
    private List<CarroDTO> dtoList = CarroMother.collectDTO();
    private List<Carro> carList = CarroMother.copoletionGetCarros();

    private static final Long ID = 1L;
    private static final String TIPO = "calssico";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCarros() {
        when(mapper.toCollectonDTO(carList)).thenReturn(dtoList);
        when(repository.findAll()).thenReturn(carList);

        List<CarroDTO> response = service.getCarros();
        assertNotNull(response);
    }

    @Test
    public void getCarroById() {
      when(repository.findById(any())).thenReturn(obj);
      Carro response = service.getCarroById(ID);
      assertNotNull(response);
    }

    @Test
    public void getCarrosByTipo() {
        when(repository.findByTipo(TIPO)).thenReturn(carList);
        when(mapper.toCollectonDTO(carList)).thenReturn(dtoList);

        List<CarroDTO> reponse = service.getCarrosByTipo(TIPO);
        assertNotNull(reponse);
    }

    @Test
    public void inserir() {

       when(repository.save(car)).thenReturn(car);
       when(mapper.toDTO(car)).thenReturn(dto);

       CarroDTO response = service.inserir(car);
        assertNotNull(response);
    }

    @Test
    public void update() {
    }

    @Test
    public void delet() {
    }
}