package com.university.itis.itisapp.model.common;

import java.io.Serializable;

public interface BaseEntity<T> extends Serializable{

    T getId();

    void setId(T id);
}
