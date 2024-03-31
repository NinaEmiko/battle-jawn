package com.battlejawn.Config;

import com.battlejawn.Controllers.PlayerTipController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class JsonParser {
    private final ObjectMapper objectMapper;
    public JsonParser() {
        objectMapper = new ObjectMapper();
    }
    private final Logger logger = Logger.getLogger(PlayerTipController.class.getName());

    public String extractRole(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.get("role").asText();
        } catch (Exception e) {
            logger.info("Exception: " + e);
            return null;
        }
    }
    public String extractMove(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.get("move").asText();
        } catch (Exception e) {
            logger.info("Exception: " + e);
            return null;
        }
    }

    public String extractHeroName(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.get("heroName").asText();
        } catch (Exception e) {
            logger.info("Exception: " + e);
            return null;
        }
    }

    public Long extractHeroId(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.get("heroId").asLong();
        } catch (Exception e) {
            logger.info("Exception: " + e);
        }
        return null;
    }

    public Long extractUserAccountId(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.get("userAccountId").asLong();
        } catch (Exception e) {
            logger.info("Exception: " + e);
            return null;
        }
    }

    public Long extractBattleSessionId(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.get("battleSessionId").asLong();
        } catch (Exception e) {
            logger.info("Exception: " + e);
        }
        return null;
    }
}
