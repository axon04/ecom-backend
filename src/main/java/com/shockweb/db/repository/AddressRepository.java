package com.shockweb.db.repository;

import com.shockweb.db.domain.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {}
