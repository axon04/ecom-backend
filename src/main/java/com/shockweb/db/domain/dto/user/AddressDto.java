package com.shockweb.db.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AddressDto {

    private long id;

    private String firstLine;

    private String secondLine;

    private String thirdLine;

    private String city;

    private String region;

    private int zipcode;

    private String country;
}
