package com.alves.stw.adapters.in;


import com.alves.stw.application.AskChampionUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Campeões", description = "End-points do dominio de campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record AskChampionsRestController(AskChampionUseCase useCase) {

    @PostMapping("/{id}/ask")
    public AskChampionResponse askChampion(@PathVariable Long id, @RequestBody AskChampionRequest request) {
        String answer = useCase().askChampion(id, request.question);

        return new AskChampionResponse(answer);
    }

    public record AskChampionRequest(String question) {}
    public record AskChampionResponse(String answer) {}
}
