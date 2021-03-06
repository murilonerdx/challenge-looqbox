package com.murilo.looqbox.domain.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.murilo.looqbox.domain.model.Spotlight;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotlightResponseResultList implements Serializable {
    public SpotlightResponseResultList(Set<Spotlight> results)
    {
        this.result = results.stream().map(Spotlight::getName).collect(Collectors.toList());
    }

    public final List<String> result;
}
