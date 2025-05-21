package br.dev.danielsebastian.coffeemaker.dao.impl;

import br.dev.danielsebastian.coffeemaker.dao.ConnectionManager;
import br.dev.danielsebastian.coffeemaker.dao.ProductDao;
import br.dev.danielsebastian.coffeemaker.exception.DBException;
import br.dev.danielsebastian.coffeemaker.model.Category;
import br.dev.danielsebastian.coffeemaker.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleProductDao implements ProductDao {

    private Connection connection;

    @Override
    public void register(Product product) throws DBException {

        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO TB_PRODUCT (cd_id, nm_name, vl_value, nm_quantity, date_create, id_category) VALUES (sq_tb_product.nextval, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getValue());
            stmt.setInt(3, product.getQuantity());
            stmt.setDate(4, Date.valueOf(product.getDateCreated()));
            stmt.setLong(5, product.getCategory().getId());

            stmt.executeUpdate();

            System.out.println("Produto registrado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao registrar produto");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(Product product) throws DBException {

        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE TB_PRODUCT SET nm_name = ?, vl_value = ?, nm_quantity = ?, date_create = ?, id_category = ? WHERE cd_id = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getValue());
            stmt.setInt(3, product.getQuantity());
            stmt.setDate(4, Date.valueOf(product.getDateCreated()));
            stmt.setLong(5, product.getCategory().getId());
            stmt.setLong(6, product.getId());

            stmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar produto");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) throws DBException {

        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM TB_PRODUCT WHERE cd_id = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.executeUpdate();

            System.out.println("Produto deletado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao deletar produto");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM TB_PRODUCT " +
                    "INNER JOIN TB_CATEGORY " +
                    " ON TB_CATEGORY.cd_id = TB_PRODUCT.id_category " +
                    "WHERE TB_PRODUCT.cd_id = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getLong("cd_id"),
                        rs.getString("nm_name"),
                        rs.getDouble("vl_value"),
                        rs.getInt("nm_quantity"),
                        rs.getDate("date_create").toLocalDate()
                );

                product.setCategory(
                        new Category(
                                rs.getLong("id_category"),
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

        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM TB_PRODUCT " +
            "INNER JOIN TB_CATEGORY ON TB_CATEGORY.cd_id = TB_PRODUCT.id_category";

            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("cd_id"),
                        rs.getString("nm_name"),
                        rs.getDouble("vl_value"),
                        rs.getInt("nm_quantity"),
                        rs.getDate("date_create").toLocalDate()
                );

                product.setCategory(
                        new Category(
                                rs.getLong("id_category"),
                                rs.getString("nm_category")
                        )
                );
                products.add(product);
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

        return products;
    }
}
