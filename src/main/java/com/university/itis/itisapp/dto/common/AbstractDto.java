package com.university.itis.itisapp.dto.common;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public abstract class AbstractDto {

    protected Long id;

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
