package com.alves.stw.domain.model;

public record Champion(
        Long id,
        String name,
        String role,
        String lore,
        String imageUrl) {
    public String generateContextByQuestion(String question) {
        return """
                Pergunta: %s
                Nome do campeão: %s
                Função: %s
                Lore (historia): %s
                """.formatted(question, this.name, this.role, this.lore);
    }
}
