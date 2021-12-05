package ru.gb.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "PRODUCT")
@NamedQueries({
        @NamedQuery(name = "Product.findTitleById",
                query = "select p.title from Product p where p.id = :id"),
        @NamedQuery(name = "Product.findById",
                query = "select p from Product p where p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "MANUFACTURE_DATE")
    private LocalDate manufactureDate;

    @Column(name = "MANUFACTURER_ID")
    private Long manufacturerId;
}
