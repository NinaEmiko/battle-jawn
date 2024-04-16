package com.battlejawn.StatusAilments;

import lombok.Data;

@Data
public class StatusAilments {

    private boolean paralyze;
    private boolean bubble;
    private boolean vulnerable;
    private boolean slow;

    public StatusAilments(boolean paralyze, boolean bubble, boolean vulnerable, boolean slow) {
        this.paralyze = paralyze;
        this.bubble = bubble;
        this.vulnerable = vulnerable;
        this.slow = slow;
    }

}