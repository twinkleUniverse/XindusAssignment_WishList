package com.xindus1.ecommerce1.dto.requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class AuthRequest {
    private String email ;
    private String password;
}
