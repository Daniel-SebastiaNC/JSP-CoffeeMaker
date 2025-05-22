package br.dev.danielsebastian.coffeemaker.dao;

import br.dev.danielsebastian.coffeemaker.model.User;

public interface UserDao {
    boolean validUser(User user);
}
