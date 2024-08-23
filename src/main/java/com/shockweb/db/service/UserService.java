package com.shockweb.db.service;

import com.shockweb.db.domain.dto.AddressDto;
import com.shockweb.db.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByEmail(String email);
    AddressDto getDefaultAddressByEmail(String email);
    List<AddressDto> getAllAddressesByEmail(String email);
    void saveNewUser(UserDto newUser);
    void saveNewAddress(String email, AddressDto newAddress);
    void updateUser(String email, UserDto updatedUser);

}
