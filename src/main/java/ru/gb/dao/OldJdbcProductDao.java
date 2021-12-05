package ru.gb.dao;

import ru.gb.entity.Manufacturer;
import ru.gb.entity.Product;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OldJdbcProductDao implements ProductDao {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/gb_shop", "geek", "geek");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Product> findAll() {
        Set<Product> result = new HashSet<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                final Product product = Product.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .cost(rs.getBigDecimal("cost"))
                        .manufactureDate(rs.getDate("manufacture_date").toLocalDate())
                        .manufacturerId(rs.getLong("manufacturer_id"))
                        .build();
                result.add(product);
            }
            statement.close();
        } catch (SQLException e) {

        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public Product findById(Long id) {
        Connection connection = null;
        Product product = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, title, cost, manufacture_date, manufacturer_id FROM product \n" +
                    "WHERE id = ?");
            statement.setLong(1, id);
            statement.setMaxRows(1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                product = Product.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .cost(rs.getBigDecimal("cost"))
                        .manufactureDate(rs.getDate("manufacture_date").toLocalDate())
                        .manufacturerId(rs.getLong("manufacturer_id"))
                        .build();
                break;
            }
            statement.close();
        } catch (SQLException e) {

        } finally {
            closeConnection(connection);
        }
        return product;
    }

    @Override
    public String findTitleById(Long id) {
        Connection connection = null;
        String s = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT title FROM product WHERE id = ?");
            statement.setLong(1, id);
            statement.setMaxRows(1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                s = rs.getString("title");
                break;
            }
            statement.close();
        } catch (SQLException e) {

        } finally {
            closeConnection(connection);
        }
        return s;
    }

}
