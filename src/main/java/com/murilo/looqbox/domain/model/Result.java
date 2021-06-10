package com.murilo.looqbox.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result  implements Serializable {

    private String name;
    private Spotlight spotlight;

    public Result(String name, Spotlight spotlight) {
        this.name = name;
        this.spotlight = spotlight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Result() {
    }

    public Spotlight getSpotlight() {
        return spotlight;
    }

    public void setSpotlight(Spotlight spotlight) {
        this.spotlight = spotlight;
    }
}