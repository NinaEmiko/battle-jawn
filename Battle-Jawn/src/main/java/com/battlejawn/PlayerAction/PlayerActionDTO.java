package com.battlejawn.PlayerAction;

import org.springframework.stereotype.Service;

@Service
public class PlayerActionDTO {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
