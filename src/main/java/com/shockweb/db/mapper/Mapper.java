package com.shockweb.db.mapper;

public interface Mapper<A,B> {

    B toDto(A a);

    A toEntity(B b);

}
