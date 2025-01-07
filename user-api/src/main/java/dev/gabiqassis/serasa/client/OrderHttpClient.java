package dev.gabiqassis.serasa.client;

import dev.gabiqassis.serasa.client.exception.OrderServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class OrderHttpClient {

    @Value("${order-service.url}")
    private String apiUrl;

    private HttpClient client;

    public OrderHttpClient() {
        this.client = HttpClient.newHttpClient();
    }

    public boolean hasOrders(String id) {
        try {
            URI uri = URI.create(apiUrl + "/" + id);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return !response.body().isEmpty() && !response.body().equals("[]");
            } else {
                throw new IOException("Falha ao buscar pedidos: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new OrderServiceException("Erro ao verificar pedidos do usuário", e);
        }
    }
}


