package ru.gb.dao;

import ru.gb.entity.Manufacturer;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OldJdbcManufacturerDao implements ManufacturerDao {

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
    public Iterable<Manufacturer> findAll() {
        Set<Manufacturer> result = new HashSet<>();

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM manufacturer");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Manufacturer manufacturer = Manufacturer.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                result.add(manufacturer);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }

        return result;
    }

    @Override
    public Manufacturer findById(Long id) {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Manufacturer manufacturer) {

    }

    @Override
    public void update(Manufacturer manufacturer) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
