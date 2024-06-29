package com.challenge.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetAPIData {
    public String getData(String endpoint){
        // Create the client and request body
        HttpClient client = HttpClient.newBuilder()
                // Handle 301 status code (moved permanently)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();
        // Variable
        HttpResponse<String> response;
        try {
            // Make the request
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Error handling
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error occurred while fetching data from API", e);
        }
        // Check if response is null
        if (response.body() == null) {
            return null;
        }
        System.out.println("\nStatus Code: " + response.statusCode());
        return response.body();
    }
}
