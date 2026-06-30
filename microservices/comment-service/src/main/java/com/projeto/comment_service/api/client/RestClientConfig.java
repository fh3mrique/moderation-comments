package com.projeto.comment_service.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean // Registra um bean do tipo ModerationClient no contexto da aplicação.
    public ModerationClient moderationClient(RestClientFactory factory) {
        // Obtém um RestClient previamente configurado (URL base, autenticação, headers, etc.).
        RestClient restClient = factory.moderationClient();

        // Adapta o RestClient para que ele possa ser utilizado pelo HttpServiceProxyFactory.
        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        // Cria uma fábrica capaz de gerar implementações automáticas de interfaces HTTP.
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();


        // Gera dinamicamente a implementação da interface ModerationClient.
        // Quando um método da interface for chamado, o Spring realizará
        // a requisição HTTP correspondente e converterá a resposta para o tipo esperado.
        return proxyFactory.createClient(ModerationClient.class);

    }
}
