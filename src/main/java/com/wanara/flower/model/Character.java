package com.wanara.flower.model;

public class Character {
    private Integer charaId;
    private String charaName;
    private String isBloomed;

    public Character() {
    }

    public Character(Integer charaId, String charaName, String isBloomed) {
        this.charaId = charaId;
        this.charaName = charaName;
        this.isBloomed = isBloomed;
    }

    public Integer getCharaId() {
        return charaId;
    }

    public void setCharaId(Integer charaId) {
        this.charaId = charaId;
    }

    public String getCharaName() {
        return charaName;
    }

    public void setCharaName(String charaName) {
        this.charaName = charaName;
    }

    public String getIsBloomed() {
        return isBloomed;
    }

    public void setIsBloomed(String isBloomed) {
        this.isBloomed = isBloomed;
    }

    @Override
    public String toString() {
        return "Character{" +
                "charaId=" + charaId +
                ", charaName='" + charaName + '\'' +
                ", isBloomed='" + isBloomed + '\'' +
                '}';
    }
}
