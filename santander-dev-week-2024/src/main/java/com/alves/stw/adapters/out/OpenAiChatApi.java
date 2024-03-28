package com.alves.stw.adapters.out;


import com.alves.stw.domain.ports.GenerativeAiApi;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "OPENAI", matchIfMissing = true)
@FeignClient(name = "openAiChatApi", url = "${openai.base-url}", configuration = OpenAiChatApi.Config.class)
public interface OpenAiChatApi extends GenerativeAiApi {

    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResponse chatCompletion(OpenAiChatCompletionRequest request);

    @Override
    default String generateContent(String objective, String context) {
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
                new Message("system", objective),
                new Message("user", context)
        );

        OpenAiChatCompletionRequest request = new OpenAiChatCompletionRequest(model, messages);

        try {
            OpenAiChatCompletionResponse response = chatCompletion(request);
            return response.choices().getFirst().message().content();
        } catch (FeignException httpErrors) {
            return "Deu ruim! Erro de comunicação com a api do Open AI!";
        } catch (Exception unexpectedError) {
            return "Deu mais ruim ainda! O retorno da Api do Open AI não contém os dados esperados!";
        }
    }

    record OpenAiChatCompletionRequest(String model, List<Message> messages) { }
    record OpenAiChatCompletionResponse(List<Choice> choices){}
    record Choice(Message message) { }
    record Message(String role, String content) { }

    class Config {

        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey));
        }
    }

}
