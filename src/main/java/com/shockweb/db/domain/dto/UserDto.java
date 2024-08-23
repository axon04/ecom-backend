package com.shockweb.db.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// NOTE: Additional properties in the entity are ignored by the mapper.

public class UserDto {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private int phone;

    private String password;

    private AddressDto defaultAddress;

}
