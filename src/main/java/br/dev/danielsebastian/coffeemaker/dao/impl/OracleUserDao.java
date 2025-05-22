package br.dev.danielsebastian.coffeemaker.dao.impl;

import br.dev.danielsebastian.coffeemaker.dao.ConnectionManager;
import br.dev.danielsebastian.coffeemaker.dao.UserDao;
import br.dev.danielsebastian.coffeemaker.model.User;

import java.sql.*;

public class OracleUserDao implements UserDao {
    private Connection connection;

    @Override
    public boolean validUser(User user) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM TB_USER WHERE DS_EMAIL = ? AND DS_PASSWORD = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
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

        return false;

    }
}
