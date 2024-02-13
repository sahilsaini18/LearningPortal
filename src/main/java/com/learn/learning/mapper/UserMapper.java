package com.learn.learning.mapper;

import org.mapstruct.Mapper;

import com.learn.learning.dto.RegisterUserDto;
import com.learn.learning.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserEntity registerUserDtoToUserEntity(RegisterUserDto registerUser);
	
	RegisterUserDto userEntityToRegisterUserDto(UserEntity userEntity);

}
