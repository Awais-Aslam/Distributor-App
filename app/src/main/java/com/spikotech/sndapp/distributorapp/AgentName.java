package com.spikotech.sndapp.distributorapp;

public class AgentName {

    private int agentId;
    private String name;

    public AgentName() {}

    public AgentName(int agentId, String name) {
        this.agentId = agentId;
        this.name = name;
    }

    public AgentName(String name) {
        this.name = name;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
