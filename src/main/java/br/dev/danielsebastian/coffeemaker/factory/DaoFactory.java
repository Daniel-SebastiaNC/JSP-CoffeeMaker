package br.dev.danielsebastian.coffeemaker.factory;

import br.dev.danielsebastian.coffeemaker.dao.ProductDao;
import br.dev.danielsebastian.coffeemaker.dao.impl.OracleProductDao;

public class DaoFactory {
    public static ProductDao getProductDao() {
        return new OracleProductDao();
    }
}
