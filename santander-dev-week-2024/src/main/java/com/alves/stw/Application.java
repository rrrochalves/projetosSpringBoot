package com.alves.stw;

import com.alves.stw.application.AskChampionUseCase;
import com.alves.stw.application.ListChampionsUseCase;
import com.alves.stw.domain.ports.ChampionsRepository;
import com.alves.stw.domain.ports.GenerativeAiApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
		return new ListChampionsUseCase(repository);
	}

	@Bean
	public AskChampionUseCase provideAskChampionUseCase(ChampionsRepository repository, GenerativeAiApi generativeAiApi) {
		return new AskChampionUseCase(repository, generativeAiApi);
	}
}
