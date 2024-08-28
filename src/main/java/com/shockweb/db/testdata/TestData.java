package com.shockweb.db.testdata;

import com.shockweb.db.domain.dto.AddressDto;
import com.shockweb.db.domain.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<AddressDto> addressDtoList() {
        List<AddressDto> addressList = new ArrayList<>();

        addressList.add(new AddressDto(5, "k1", "321 Pine St", "", "Phoenix", "AZ", 85001, "USA"));
        addressList.add(new AddressDto(6, "k2", "901 Maple St", "", "Philadelphia", "PA", 19102, "USA"));
        addressList.add(new AddressDto(7, "k3", "234 Cedar St", "", "San Antonio", "TX", 78205, "USA"));
        addressList.add(new AddressDto(8, "n1", "567 Spruce St", "", "San Diego", "CA", 92101, "USA"));
        addressList.add(new AddressDto(9, "n2", "890 Fir St", "", "Dallas", "TX", 75201, "USA"));
        addressList.add(new AddressDto(10, "n3", "345 Cypress St", "", "San Jose", "CA", 95101, "USA"));

        return addressList;
    }


    public static UserDto getUser1() {
        return UserDto.builder()
                .email("diana.rider@beeg.com")
                .firstName("Diana")
                .lastName("Rider")
                .phone(1100110943)
                .password("banana")
                .build();
    }

    public static UserDto getUser2() {
        return UserDto.builder()
                .email("dani.daniels@beeg.com")
                .firstName("Dani")
                .lastName("Daniels")
                .phone(1100120867)
                .password("come to me")
                .build();
    }

}
