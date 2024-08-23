package com.shockweb.db.repository;

import com.shockweb.db.domain.entity.UserAddressEntity;
import com.shockweb.db.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

    Optional<UserAddressEntity> findByUserEntityAndIsDefault(UserEntity userEntity, boolean isDefault);

    List<UserAddressEntity> findAllByUserEntity(UserEntity userEntity);
}
