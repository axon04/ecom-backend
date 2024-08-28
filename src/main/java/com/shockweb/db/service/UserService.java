package com.shockweb.db.service;

import com.shockweb.db.domain.dto.AddressDto;
import com.shockweb.db.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByEmail(String email);
    List<AddressDto> getAllAddressesByEmail(String email);
    UserDto saveNewUser(UserDto newUser, AddressDto newAddress);
    void saveNewAddress(String email, AddressDto newAddress);
    UserDto updateUser(String email, UserDto updatedUser);
    AddressDto updateAddress(String email, long addressId, AddressDto newAddress);
    void deleteUser(String email);
    void deleteAddress(String email, long addressId);
}
