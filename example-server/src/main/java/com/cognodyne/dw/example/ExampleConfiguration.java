package com.cognodyne.dw.example;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.cognodyne.dw.cdi.CdiConfigurable;
import com.cognodyne.dw.cdi.CdiConfiguration;
import com.cognodyne.dw.jpa.JpaConfigurable;
import com.cognodyne.dw.jpa.JpaConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ExampleConfiguration extends Configuration implements CdiConfigurable, JpaConfigurable {
    @Valid
    @JsonProperty("cdi")
    private Optional<CdiConfiguration> cdiConfig = Optional.empty();
    //
    @Valid
    @NotNull
    @JsonProperty("jpa")
    private List<JpaConfiguration>     jpaConfig;

    @Override
    public List<JpaConfiguration> getJpaConfigurations() {
        return this.jpaConfig;
    }

    @Override
    public CdiConfiguration getCdiConfiguration() {
        return this.cdiConfig.orElse(null);
    }
}
