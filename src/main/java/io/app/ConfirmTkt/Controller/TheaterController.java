package io.app.ConfirmTkt.Controller;

import io.app.ConfirmTkt.EntryDto.TheaterEntryDto;
import io.app.ConfirmTkt.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        try {
            String response = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "Theater Not able to add";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
