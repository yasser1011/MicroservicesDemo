package com.yasser.ecommerce.product;

import com.yasser.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Integer availableQuantity;
    private Integer priceInCents;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
