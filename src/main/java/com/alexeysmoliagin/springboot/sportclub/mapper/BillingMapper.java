package com.alexeysmoliagin.springboot.sportclub.mapper;

import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event.BillingEventDto;
import com.alexeysmoliagin.springboot.sportclub.repository.users.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.UUID;


@Mapper(imports = {UUID.class}, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BillingMapper {
    @Mapping(target = "idBillingEvent", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "name", source = "users.name")
    @Mapping(target = "surname", source = "users.surname")
    @Mapping(target = "age", source = "users.age")
    @Mapping(target = "price", source = "price")
    BillingEventDto toBillingEventDto (Users users, int price);

}
