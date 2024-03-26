package com.alves.stw.domain.ports;

import com.alves.stw.domain.model.Champion;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {


    List<Champion> findAll();

    Optional<Champion> findById(Long id);
}
