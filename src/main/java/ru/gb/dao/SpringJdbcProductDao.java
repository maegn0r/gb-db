package ru.gb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.entity.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class SpringJdbcProductDao implements ProductDao {

    private final DataSource dataSource;

    @Override
    public Iterable<Product> findAll() {
        Set<Product> result = new HashSet<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
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
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Product findById(Long id) {
        Connection connection = null;
        Product product = null;
        try {
            connection = dataSource.getConnection();
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
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public String findTitleById(Long id) {
        Connection connection = null;
        String s = null;
        try {
            connection = dataSource.getConnection();
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
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}
