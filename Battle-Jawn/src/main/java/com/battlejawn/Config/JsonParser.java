package com.battlejawn.Config;

import com.battlejawn.Controllers.PlayerTipController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

public class JsonParser {
    private final ObjectMapper objectMapper;
    public JsonParser() {
        objectMapper = new ObjectMapper();
    }
    private final Logger logger = Logger.getLogger(PlayerTipController.class.getName());

    public String extractRole(String JsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(JsonString);
            return jsonNode.get("role").asText();
        } catch (Exception e) {
            logger.info("Exception: " + e);
            return null;
        }
    }
}
