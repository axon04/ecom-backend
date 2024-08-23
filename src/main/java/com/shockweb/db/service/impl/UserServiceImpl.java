package com.shockweb.db.service.impl;

import com.shockweb.db.domain.dto.AddressDto;
import com.shockweb.db.domain.dto.UserDto;
import com.shockweb.db.domain.entity.AddressEntity;
import com.shockweb.db.domain.entity.UserAddressEntity;
import com.shockweb.db.domain.entity.UserEntity;
import com.shockweb.db.mapper.Mapper;
import com.shockweb.db.repository.AddressRepository;
import com.shockweb.db.repository.UserAddressRepository;
import com.shockweb.db.repository.UserRepository;
import com.shockweb.db.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Mapper<UserEntity, UserDto> userMapper;

    private final Mapper<AddressEntity, AddressDto> addressMapper;
    private final AddressRepository addressRepository;
    private final UserAddressRepository userAddressRepository;

    public UserServiceImpl(UserRepository userRepository,
                           Mapper<UserEntity, UserDto> userMapper,
                           Mapper<AddressEntity, AddressDto> addressMapper,
                           AddressRepository addressRepository,
                           UserAddressRepository userAddressRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.userAddressRepository = userAddressRepository;
    }


//    getUserByUsername(String username)
//    getDefaultAddress(String username)
//    getAllAddresses(String username)
//    setNewUser(UserDTO newUser)
//    setNewAddress(String username, AddressDTO newAddress)

//TODO:    updateUser(UserDTO updatedUser) full update

//TODO:    updateUser(String username, enum field name, <> newValue) partial update
//TODO:    updateAddress(String username, AddressDTO newAddress) PATCH + PUT
//TODO:    setDefaultAddress(String username, long addressId)
//TODO:    deleteUser(String username)
//TODO:    deleteAddress(String username, long addressId

    // get user by email
    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::mapTo)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found."));
    }


    // get default address by email
    @Override
    public AddressDto getDefaultAddressByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found."));

        UserAddressEntity userAddressEntity = userAddressRepository.findByUserEntityAndIsDefault(userEntity, true)
                .orElseThrow(() -> new IllegalArgumentException("Default address for user with email " + email + " not found."));

        return addressMapper.mapTo(userAddressEntity.getAddressEntity());
    }



    // get all addresses by email
    @Override
    public List<AddressDto> getAllAddressesByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found."));

        List<UserAddressEntity> userAddressEntities = userAddressRepository.findAllByUserEntity(userEntity);
        return userAddressEntities.stream()
                .map(userAddressEntity -> addressMapper.mapTo(userAddressEntity.getAddressEntity()))
                .toList();
    }



    // save new user
    @Override
    @Transactional
    public void saveNewUser(UserDto newUser) {
        // Check if user with the same email already exists
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + newUser.getEmail() + " already exists.");
        }

        // Map UserDto to UserEntity and AddressDto to AddressEntity
        UserEntity userEntity = userMapper.mapFrom(newUser);
        AddressEntity defaultAddress = addressMapper.mapFrom(newUser.getDefaultAddress());

        // Save the new user
        UserEntity savedUser = userRepository.save(userEntity);
        AddressEntity savedAddress = addressRepository.save(defaultAddress);
        UserAddressEntity addressLink = UserAddressEntity.builder()
                .userEntity(savedUser)
                .addressEntity(savedAddress)
                .isDefault(true)
                .build();
        userAddressRepository.save(addressLink);
    }




    @Override
    @Transactional
    public void saveNewAddress(String email, AddressDto newAddress) {
        // Check if user with the email exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found."));

        // Map AddressDto to AddressEntity
        AddressEntity addressEntity = addressMapper.mapFrom(newAddress);

        // Save the new address
        AddressEntity savedAddress = addressRepository.save(addressEntity);
        UserAddressEntity addressLink = UserAddressEntity.builder()
                .userEntity(userEntity)
                .addressEntity(savedAddress)
                .isDefault(false)
                .build();
        userAddressRepository.save(addressLink);
    }



    // update user fully
    @Override
    public void updateUser(String email, UserDto updatedUser) {
        // Check if user with the email exists
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found."));

        // Map UserDto to UserEntity
        UserEntity updatedUserEntity = userMapper.mapFrom(updatedUser);

        // Set the email so that it is not changed
        updatedUserEntity.setEmail(email);

        // Save the updated user
        userRepository.save(updatedUserEntity);
    }


}
