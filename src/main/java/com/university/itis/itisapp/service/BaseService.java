package com.university.itis.itisapp.service;

public interface BaseService<T> {

    T get(Long id);

    T saveOrUdpate(T t);

    void delete(Long id);

}
