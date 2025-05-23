package br.dev.danielsebastian.coffeemaker.factory;

import br.dev.danielsebastian.coffeemaker.dao.CategoryDao;
import br.dev.danielsebastian.coffeemaker.dao.ProductDao;
import br.dev.danielsebastian.coffeemaker.dao.UserDao;
import br.dev.danielsebastian.coffeemaker.dao.impl.OracleCategoryDao;
import br.dev.danielsebastian.coffeemaker.dao.impl.OracleProductDao;
import br.dev.danielsebastian.coffeemaker.dao.impl.OracleUserDao;

public class DaoFactory {
    public static ProductDao getProductDao() {
        return new OracleProductDao();
    }

    public static CategoryDao getCategoryDao() {
        return new OracleCategoryDao();
    }

    public static UserDao getUserDao() {
        return new OracleUserDao();
    }
}
