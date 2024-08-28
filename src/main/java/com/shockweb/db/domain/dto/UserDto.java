package com.shockweb.db.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// NOTE: Additional properties in the entity are ignored by the mapper.

public class UserDto {

    private long id;

    private String firstName;

    private String lastName;

    // prevent setter method from being generated
    @Setter(AccessLevel.NONE)
    private String email;

    private int phone;

    private String password;

    private AddressDto address;

}
