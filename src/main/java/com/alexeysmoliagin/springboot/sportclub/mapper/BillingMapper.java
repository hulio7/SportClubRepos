package com.alexeysmoliagin.springboot.sportclub.mapper;

import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event.BillingEventDto;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.UUID;


@Mapper(imports = {UUID.class}, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BillingMapper {
    @Mapping(target = "idBillingEvent", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "surname", source = "user.surname")
    @Mapping(target = "age", source = "user.age")
    @Mapping(target = "price", source = "price")
    BillingEventDto toBillingEventDto (User user, int price);

}
