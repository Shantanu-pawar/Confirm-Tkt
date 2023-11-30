package io.app.ConfirmTkt.Controller;

import io.app.ConfirmTkt.EntryDto.ShowEntryDto;
import io.app.ConfirmTkt.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
        try{
            String response = showService.addShow(showEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            String res = "No show available";
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
