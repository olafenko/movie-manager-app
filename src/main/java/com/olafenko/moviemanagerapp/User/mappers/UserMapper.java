package com.olafenko.moviemanagerapp.User.mappers;

import com.olafenko.moviemanagerapp.User.dtos.UserDto;
import com.olafenko.moviemanagerapp.User.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);


}
