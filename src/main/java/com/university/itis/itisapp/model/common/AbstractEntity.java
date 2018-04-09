package com.university.itis.itisapp.model.common;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity implements BaseEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {

    }
}
