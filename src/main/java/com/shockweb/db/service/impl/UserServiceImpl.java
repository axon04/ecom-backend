package com.shockweb.db.service.impl;

import com.shockweb.db.domain.dto.AddressDto;
import com.shockweb.db.domain.dto.UserDto;
import com.shockweb.db.domain.entity.AddressEntity;
import com.shockweb.db.domain.entity.UserEntity;
import com.shockweb.db.mapper.Mapper;
import com.shockweb.db.mapper.impl.AddressMapperImpl;
import com.shockweb.db.mapper.impl.UserMapperImpl;
import com.shockweb.db.repository.AddressRepository;
import com.shockweb.db.repository.UserRepository;
import com.shockweb.db.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Mapper<UserEntity, UserDto> userMapper;

    private final Mapper<AddressEntity, AddressDto> addressMapper;
    private final AddressRepository addressRepository;
    private final UserMapperImpl userMapperImpl;
    private final AddressMapperImpl addressMapperImpl;

    public UserServiceImpl(UserRepository userRepository,
                           Mapper<UserEntity, UserDto> userMapper,
                           AddressRepository addressRepository,
                           Mapper<AddressEntity, AddressDto> addressMapper,
                           UserMapperImpl userMapperImpl,
                           AddressMapperImpl addressMapperImpl) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.userMapperImpl = userMapperImpl;
        this.addressMapperImpl = addressMapperImpl;
    }


//    getUserByEmail(String username)
//    saveNewUser(UserDTO newUser)
//    updateUser(UserDTO updatedUser) full update, partial update
//    getAllAddressesByEmail(String username)
//    saveNewAddress(String username, AddressDTO newAddress)
//    deleteUser(String username)
//    updateAddress(String username, AddressDTO newAddress) PATCH + PUT
//    deleteAddress(String username, long addressId

//TODO:    getDefaultAddress(String username)
//TODO:    setDefaultAddress(String username, long addressId)
/*
Update address requires the address id to be passed in the request body. This will cause security issues directly exposing the address id to the client.
Instead, the address id should be fetched from the database using the user id . This will prevent the client from directly manipulating the address id.
But this will require an additional database call to fetch the address id.
This will increase the response time of the API.
 */
    // get user by email (this gives address null)
    @Override
    public UserDto getUserByEmail(String email) {

        // check if user exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // return the user
        return userMapper.toDto(userEntity);
    }

    // get all addresses by email
    @Override
    @Transactional
    public List<AddressDto> getAllAddressesByEmail(String email) {
        // check if user exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<AddressDto> addressDtos =  new ArrayList<>();

        // map the addresses to address dtos using foreach and lambda
        userEntity.getAddress().forEach(address -> {
            addressDtos.add(addressMapper.toDto(address));
        });

        // return the addresses
        return addressDtos;
    }

    // save new user
    @Override
    @Transactional
    public UserDto saveNewUser(UserDto newUser, AddressDto newAddress) {
        // check if user exists
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // map the address dto to address entity and save the address
        AddressEntity savedAddress = addressRepository.save(addressMapper.toEntity(newAddress));

        log.info("Saved address: {}", savedAddress);

        // map the user dto to user entity
        UserEntity userEntity = userMapper.toEntity(newUser);

        // set the address to the user
        userEntity.setAddress(List.of(savedAddress));

        log.info("UserEntity: {}", userEntity);

        // save the user
        UserEntity savedUser = userRepository.save(userEntity);
        // return the saved user
        return userMapper.toDto(savedUser);
    }

    // update user (full update)
    @Override
    @Transactional
    public UserDto updateUser(String email, UserDto updatedUser) {
        // check if user exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // map the updated user dto to user entity
        UserEntity updatedUserEntity = userMapper.toEntity(updatedUser);

        // map the non-null fields of the updated user entity to the user entity
        userMapperImpl.mapNonNullFields(updatedUserEntity, userEntity);

        // save the updated user entity
        UserEntity savedUser = userRepository.save(userEntity);

        // return the updated user
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public void saveNewAddress(String email, AddressDto newAddress) {

        // check if user exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // save the address
        AddressEntity savedAddress = addressRepository.save(addressMapper.toEntity(newAddress));

        // add the address to the user
        userEntity.getAddress().add(savedAddress);

        // save the user
        userRepository.save(userEntity);

    }

    // update address (full + partial update)
    @Override
    @Transactional
    public AddressDto updateAddress(String email, long addressId, AddressDto newAddress) {
        // check if user exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieve the address to be updated
        List<AddressEntity> addresses = userEntity.getAddress();
        AddressEntity addressToUpdate = addresses.stream()
                .filter(address -> Long.compare(address.getId(), addressId) == 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // Update the address
        addressMapperImpl.mapNonNullFields(addressMapper.toEntity(newAddress), addressToUpdate);

        // Save the updated address to the database
        UserEntity updatedUser = userRepository.save(userEntity);
        return updatedUser.getAddress().stream()
                .filter(address -> Long.compare(address.getId(), addressId) == 0)
                .map(addressMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    @Transactional
    public void deleteUser(String email) {

        // Check if user with the same email already exists
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("User with email " + email + " does not exist.");
        }

        userRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public void deleteAddress(String email, long addressId) {

        // check if user exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieve the address to be updated
        List<AddressEntity> addresses = userEntity.getAddress();
        AddressEntity addressToDelete = addresses.stream()
                .filter(address -> Long.compare(address.getId(), addressId) == 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // delete the address
        addressRepository.delete(addressToDelete);
        userEntity.getAddress().remove(addressToDelete);
        userRepository.save(userEntity);
    }

}
