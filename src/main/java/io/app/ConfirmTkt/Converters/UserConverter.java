package io.app.ConfirmTkt.Converters;

import io.app.ConfirmTkt.Entities.UserEntity;
import io.app.ConfirmTkt.EntryDto.UserEntryDto;

public class UserConverter {

    public static UserEntity convertUserDtoToEntity(UserEntryDto userEntryDto){

        UserEntity userEntity = UserEntity.builder()
                .name(userEntryDto.getName())
                .age(userEntryDto.getAge())
                .email(userEntryDto.getEmail())
                .mobNo(userEntryDto.getMobNo())
                .address(userEntryDto.getAddress())
                .build();

        return userEntity;
    }
}
