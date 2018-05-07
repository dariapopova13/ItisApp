package com.university.itis.itisapp.model.enums;

public enum RoleNames {

    ADMIN, DEAN, PROFESSOR;

    public static RoleNames getRoleName(String name){
        if (name.equalsIgnoreCase(ADMIN.name()))
            return ADMIN;
        else if (name.equalsIgnoreCase(DEAN.name()))
            return DEAN;
        else return PROFESSOR;
    }
}
