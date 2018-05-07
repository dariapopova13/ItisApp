package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import com.university.itis.itisapp.model.enums.RoleNames;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "roles", schema = "itis_app")
public class Role extends AbstractEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleNames name;

    public RoleNames getName() {
        return name;
    }

    public void setName(RoleNames name) {
        this.name = name;
    }

    public String getSimpleName() {
        return name.name();
    }


}
