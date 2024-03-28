package com.alves.stw.application;


import com.alves.stw.domain.exception.ChampionNotFoundException;
import com.alves.stw.domain.model.Champion;
import com.alves.stw.domain.ports.ChampionsRepository;

public record AskChampionUseCase(ChampionsRepository repository) {

    public String askChampion(Long id, String question) {
        Champion champion = repository.findById(id)
                .orElseThrow(() -> new ChampionNotFoundException(id));

        String championContext = champion.generateContextByQuestion(question);


        return championContext;
    }


}
