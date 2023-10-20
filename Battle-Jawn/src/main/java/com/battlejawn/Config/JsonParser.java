package com.battlejawn.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private ObjectMapper objectMapper;

    public JsonParser() {
        objectMapper = new ObjectMapper();
    }

    public String extractRole(String JsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(JsonString);
            return jsonNode.get("role").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Long extractId(String JsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(JsonString);
            String stringId = jsonNode.get("id").asText();
            Long longId = Long.parseLong(stringId);
            return longId;
            // return (jsonNode.get("id").asText());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
