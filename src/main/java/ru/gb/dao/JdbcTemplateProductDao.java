package ru.gb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Component
@RequiredArgsConstructor
public class JdbcTemplateProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public Product findById(Long id) {
        String sql = "SELECT id, title, cost, manufacture_date, manufacturer_id FROM product \n" +
                "WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductMapper(), id);
    }

    @Override
    public String findTitleById(Long id) {
        return jdbcTemplate.queryForObject("SELECT title FROM product where id = ?", String.class, id);
    }

    private static class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .cost(rs.getBigDecimal("cost"))
                    .manufactureDate(rs.getDate("manufacture_date").toLocalDate())
                    .manufacturerId(rs.getLong("manufacturer_id"))
                    .build();
        }
    }
}
