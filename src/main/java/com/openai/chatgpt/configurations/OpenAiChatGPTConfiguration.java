package com.openai.chatgpt.configurations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class OpenAiChatGPTConfiguration {
    /**
     * esta anotação é usada para injetar valores do arquivo application.properties.
     * Neste caso, ele recupera a chave API.
     */
    @Value("${openai.chatgpt.api.key}")
    private String openaiApiKey;

    /**
     *  Esta classe de configuração, configura um RestTemplatebean com um interceptor
     *  que adiciona a chave da API OpenAI ao cabeçalho "Autorização", garantindo que
     *  as solicitações HTTP subsequentes feitas por este RestTemplatesejam autenticadas.
     */
    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        //Responsável por modificar a solicitação HTTP antes de ela ser enviada
        restTemplate.getInterceptors()
                //add params ao cabeçalho da Requisição
                .add((request, body, execution) -> {
            //lambda (em cima ) lembra o por quê? (SAM). Ver depois
            request.getHeaders()
                    .add("Authorization",
                            "Bearer " + openaiApiKey);
                                    return execution.execute(request, body);
        });

        return restTemplate;
    }
}
