package com.xindus1.ecommerce1.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    String username;
    String email;
    String mob;
    String address;
}
