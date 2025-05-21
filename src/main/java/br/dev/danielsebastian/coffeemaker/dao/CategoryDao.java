package br.dev.danielsebastian.coffeemaker.dao;

import br.dev.danielsebastian.coffeemaker.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
}
