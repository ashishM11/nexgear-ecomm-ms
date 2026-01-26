package com.ecommerce.app.mapper;

import com.ecommerce.app.kafka.model.UserCreationEvent;
import com.ecommerce.app.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

}
