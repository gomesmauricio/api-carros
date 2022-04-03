package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.dto.CarroDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroMother {

    public static Carro getCarro() {
        Carro carro = new Carro();

    //  carro.setId(1L);
        carro.setNome("Tucker 1948");
        carro.setDescricao("Descrição Tucker 1948");
        carro.setTipo("Classico");
        carro.setUrlFoto("http://www.livroandroid.com.br/livro/carros/classicos/Tucker.png");
        carro.setUrlVideo("http://www.livroandroid.com.br/livro/carros/classicos/tucker.mp4");
        carro.setLatitude("-23.564224");
        carro.setLongitude("-46.653156");

        return carro;

    }

    public static CarroDTO getDTO() {

        CarroDTO dto = new CarroDTO();

        dto.setId(1L);
        dto.setNome("Tucker 1948");
        dto.setTipo("Classico");
        dto.setNome("Descrição Tucker 1948");

        return dto;
    }

    public static Optional<Carro> getOptinalCar() {
        Optional<Carro> carOptional = Optional.of(getCarro());
        return carOptional;
    }

    public static List<Carro> copoletionGetCarros() {
        List<Carro> carrosList = new ArrayList<>();
        carrosList.add(getCarro());
        return carrosList;
    }

    public static List<CarroDTO> collectDTO () {
        List<CarroDTO> dtoList = new ArrayList<>();
        dtoList.add(getDTO());
        return dtoList;
    }
}
