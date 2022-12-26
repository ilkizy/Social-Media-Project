package com.ilkiz.utility;

import java.util.List;

public interface IService<T,ID> {

    T save(T t);
    Iterable<T> saveAll(Iterable<T> t);
    T update(T t);
    void deleteById(ID id);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();
}
