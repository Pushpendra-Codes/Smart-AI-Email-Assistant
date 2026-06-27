package com.pushpendra.demo.service;

import com.pushpendra.demo.model.Content;
import com.pushpendra.demo.model.GeminiRequest;
import com.pushpendra.demo.model.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIService {



    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;


    private List<Content> contents;

    private final RestClient restClient = RestClient.create();

    public String getReply(String emailText, String tone) {
        String prompt = "Generate an email reply in given tone. STRICT RULES:  Return only the email body content. No greeting. No closing. No signature Email:\n" + emailText +"Tone:"+ tone;


        Part part = new Part();
        part.setText(prompt);

        List<Part> parts = new ArrayList<>();
        parts.add(part);
        Content content = new Content();
        content.setParts(parts);

        List<Content> contents = new ArrayList<>();
        contents.add(content);
        GeminiRequest request = new GeminiRequest();
        request.setContents(contents);

         emailText= restClient.post().uri(apiUrl).header("x-goog-api-key",apiKey).header("Content-Type", "application/json").body(request).retrieve().body(String.class);


        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(emailText);


        String response = root
                .path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();

        return response;
    }
}
