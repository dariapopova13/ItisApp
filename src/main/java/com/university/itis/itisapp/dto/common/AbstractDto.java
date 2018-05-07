package com.university.itis.itisapp.dto.common;

import com.university.itis.itisapp.model.common.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class AbstractDto {

    private Long id;

    public AbstractDto(AbstractEntity entity) {
        this.id = entity.getId();
    }

    public AbstractDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
