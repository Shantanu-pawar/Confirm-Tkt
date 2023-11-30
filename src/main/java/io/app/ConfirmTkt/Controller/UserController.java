package io.app.ConfirmTkt.Controller;

import io.app.ConfirmTkt.Entities.UserEntity;
import io.app.ConfirmTkt.EntryDto.UserEntryDto;
import io.app.ConfirmTkt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){
        try {
            String response = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            String response = "User could not able to add.something went wrong check the information!";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
