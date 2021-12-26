package ru.gb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


//@Component
@RequiredArgsConstructor
public class NamedParameterJdbcTemplateProductDao implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Iterable<Product> findAll() {
        String sql = "SELECT * FROM product";
        return namedParameterJdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public Product findById(Long id) {
        String sql = "SELECT id, title, cost, manufacture_date, manufacturer_id FROM product \n" +
                "WHERE id = :productId;";
        HashMap<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new ProductMapper());
    }

    @Override
    public String findTitleById(Long id) {
        String sql = "SELECT title FROM product where id = :productId";
        HashMap<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
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



