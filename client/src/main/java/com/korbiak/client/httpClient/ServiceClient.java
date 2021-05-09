package com.korbiak.client.httpClient;

import com.korbiak.client.config.CompanyExm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ServiceClient {
    HttpClient httpClient;

    @Value("${service.URI}")
    String serviceURI;

    public ServiceClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String resetCompany(String company) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceURI))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(company))
                .build();

        String body;
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200){
                throw new ClientException("Service returned status code: " + response.statusCode());
            }
            body = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ClientException("Failed connect to service: " + e.getMessage());
        }

        return body;
    }
}
