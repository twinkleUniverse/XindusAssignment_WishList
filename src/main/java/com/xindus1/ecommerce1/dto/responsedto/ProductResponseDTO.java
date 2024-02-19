package com.xindus1.ecommerce1.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    String productName;
    String description;
    BigDecimal price;
    String category;
    int quantity;
}
