package com.university.itis.itisapp.dao;

import com.university.itis.itisapp.model.common.AbstractEntity;

import java.util.List;

public interface SearchDao<T extends AbstractEntity> {

    List<T> search(String request) throws InterruptedException;
}
