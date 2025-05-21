package br.dev.danielsebastian.coffeemaker.dao.impl;

import br.dev.danielsebastian.coffeemaker.dao.CategoryDao;
import br.dev.danielsebastian.coffeemaker.dao.ConnectionManager;
import br.dev.danielsebastian.coffeemaker.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleCategoryDao implements CategoryDao {

    private Connection connection;

    @Override
    public List<Category> findAll() {

        List<Category> categories = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tb_category";

            stmt = connection.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                categories.add(
                        new Category(
                                rs.getLong("cd_id"),
                                rs.getString("nm_category")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }
}
