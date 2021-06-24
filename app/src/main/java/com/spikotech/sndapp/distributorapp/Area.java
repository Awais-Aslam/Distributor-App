package com.spikotech.sndapp.distributorapp;

public class Area {

    private int areaId;
    private String areaName;

    public Area() {}

    public Area(String name) {
        this.areaName = name;
    }

    public Area(int areaId, String name) {
        this.areaId = areaId;
        this.areaName = name;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return areaName;
    }

    public void setName(String name) {
        this.areaName = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
