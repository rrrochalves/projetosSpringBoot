package com.alves.stw.application;


import com.alves.stw.domain.model.Champion;
import com.alves.stw.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository repository) {

    public List<Champion> findAll() {
        return repository.findAll();
    }


}
