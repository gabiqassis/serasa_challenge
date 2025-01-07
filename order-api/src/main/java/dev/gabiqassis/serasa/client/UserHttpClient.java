package dev.gabiqassis.serasa.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class UserHttpClient {
    @Value("${api.user.url}")
    private String apiUrl;

    private HttpClient client;

    private static final Logger logger = LoggerFactory.getLogger(UserHttpClient.class);

    public UserHttpClient() {
        this.client = HttpClient.newHttpClient();
        logger.info("HttpClient inicializado.");
    }

    public boolean hasUsers(Long userId) {
        try {
            URI uri = URI.create(apiUrl + "/" + userId);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Resposta recebida com código de status: {}", response.statusCode());
            if (response.statusCode() == 200) {
                return !response.body().isEmpty() && !response.body().equals("[]");
            } else if (response.statusCode() == 500) {
                logger.error("Erro ao verificar o usuário com ID {}: Usuário não encontrado", userId);
                throw new RuntimeException("Usuário não encontrado");
            } else {
                logger.error("Falha ao verificar usuário: Status {}", response.statusCode());
                throw new IOException("Falha ao verificar usuário: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao verificar usuário", e);
            throw new RuntimeException("Erro ao verificar usuário", e);
        }
    }
}
