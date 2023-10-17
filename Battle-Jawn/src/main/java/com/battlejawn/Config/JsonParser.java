package com.battlejawn.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private ObjectMapper objectMapper;

    public JsonParser() {
        objectMapper = new ObjectMapper();
    }

    public String extractJson(String JsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(JsonString);
            return jsonNode.get("role").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
