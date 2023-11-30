package io.app.ConfirmTkt.Service;

import io.app.ConfirmTkt.Converters.UserConverter;
import io.app.ConfirmTkt.Entities.UserEntity;
import io.app.ConfirmTkt.EntryDto.UserEntryDto;
import io.app.ConfirmTkt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception, NullPointerException {

        UserEntity userEntity = UserConverter.convertUserDtoToEntity(userEntryDto);
        userRepository.save(userEntity);
        return "User added successfully";
    }


}
