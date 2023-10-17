package com.battlejawn.StatusAilments;

import lombok.Data;

@Data
public class StatusAilments {

    private boolean paralyze = false;
    private boolean bubble = false;
    private boolean vulnerable = false;
    private boolean slow = false;

    public StatusAilments(boolean paralyze, boolean bubble, boolean vulnerable, boolean slow) {
        this.paralyze = paralyze;
        this.bubble = bubble;
        this.vulnerable = vulnerable;
        this.slow = slow;
    }

}