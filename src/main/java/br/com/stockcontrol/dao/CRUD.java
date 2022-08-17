//Interface to define database methods
package br.com.stockcontrol.dao;

import java.util.List;

public interface CRUD<T, ID> {
    T searchById(ID id);
    List<T> list();
    void insert(T t);
    void update(T t);
    void remove(T t);
}
