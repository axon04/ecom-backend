package com.shockweb.db.mapper.impl;

import com.shockweb.db.domain.dto.user.AddressDto;
import com.shockweb.db.domain.entity.user.AddressEntity;
import com.shockweb.db.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements Mapper<AddressEntity, AddressDto> {

    private ModelMapper modelMapper;

    public AddressMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDto toDto(AddressEntity addressEntity) {
        return modelMapper.map(addressEntity, AddressDto.class);
    }

    @Override
    public AddressEntity toEntity(AddressDto addressDto) {
        return modelMapper.map(addressDto, AddressEntity.class);
    }

    public void mapNonNullFields(AddressEntity updatedAddressEntity, AddressEntity addressEntity) {
        if (updatedAddressEntity.getFirstLine() != null) {
            addressEntity.setFirstLine(updatedAddressEntity.getFirstLine());
        }
        if (updatedAddressEntity.getSecondLine() != null) {
            addressEntity.setSecondLine(updatedAddressEntity.getSecondLine());
        }
        if (updatedAddressEntity.getThirdLine() != null) {
            addressEntity.setThirdLine(updatedAddressEntity.getThirdLine());
        }
        if (updatedAddressEntity.getCity() != null) {
            addressEntity.setCity(updatedAddressEntity.getCity());
        }
        if (updatedAddressEntity.getRegion() != null) {
            addressEntity.setRegion(updatedAddressEntity.getRegion());
        }
        if (updatedAddressEntity.getZipcode() != 0) {
            addressEntity.setZipcode(updatedAddressEntity.getZipcode());
        }
        if (updatedAddressEntity.getCountry() != null) {
            addressEntity.setCountry(updatedAddressEntity.getCountry());
        }
    }
}
