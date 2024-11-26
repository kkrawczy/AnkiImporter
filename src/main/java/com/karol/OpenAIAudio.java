package com.karol;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

class OpenAIAudio implements AudioService {
    private static final String API_URL = "https://api.openai.com/v1/audio/speech";
    private static final String API_KEY = "XXX";

    @Override
    public InputStream generateAudioForText(String text) {
        try {
            String requestBody = """
                    {
                        "model": "tts-1",
                        "input": "%s",
                        "voice": "alloy"
                    }
                """.formatted(text);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Failed to generate audio: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while generating audio", e);
        }
    }
}
