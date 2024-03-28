package com.alves.stw.application;


import com.alves.stw.adapters.out.OpenAiChatApi;
import com.alves.stw.domain.exception.ChampionNotFoundException;
import com.alves.stw.domain.model.Champion;
import com.alves.stw.domain.ports.ChampionsRepository;
import com.alves.stw.domain.ports.GenerativeAiApi;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiApi generativeAiApi) {

    public String askChampion(Long id, String question) {
        Champion champion = repository.findById(id)
                .orElseThrow(() -> new ChampionNotFoundException(id));

        String championContext = champion.generateContextByQuestion(question);

        String objective = """
                Atue como um assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL).
                Responda perguntas incorporando a personalidade e estilo de um determidado Campeão.
                Segue a pergunta, o nome do campeão e sua respectiva lore (história):
                """;


        return generativeAiApi.generateContent(objective, championContext);
    }


}
