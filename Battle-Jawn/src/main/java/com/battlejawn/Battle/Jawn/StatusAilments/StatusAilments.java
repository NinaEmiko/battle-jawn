package com.battlejawn.Battle.Jawn.StatusAilments;

public class StatusAilments {

    private boolean paralyze = false;
    private boolean bubble = false;
    private boolean vulnerable = false;
    private boolean slow = false;

    public boolean isParalyze() {
        return paralyze;
    }

    public void setParalyze(boolean paralyze) {
        this.paralyze = paralyze;
    }

    public boolean isBubble() {
        return bubble;
    }

    public void setBubble(boolean bubble) {
        this.bubble = bubble;
    }

    public boolean isVulnerable() {
        return vulnerable;
    }

    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
    }

    public StatusAilments(boolean paralyze, boolean bubble, boolean vulnerable, boolean slow) {
        this.paralyze = paralyze;
        this.bubble = bubble;
        this.vulnerable = vulnerable;
        this.slow = slow;
    }

}