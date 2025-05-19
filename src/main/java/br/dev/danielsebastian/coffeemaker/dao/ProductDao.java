package br.dev.danielsebastian.coffeemaker.dao;

import br.dev.danielsebastian.coffeemaker.exception.DBException;
import br.dev.danielsebastian.coffeemaker.model.Product;

import java.util.List;

public interface ProductDao {
    void register(Product product) throws DBException;
    void update(Product product) throws DBException;
    void delete(Long id) throws DBException;
    Product findById(Long id);
    List<Product> findAll();
}
