package io.app.ConfirmTkt.Controller;

import io.app.ConfirmTkt.Entities.MovieEntity;
import io.app.ConfirmTkt.EntryDto.MovieEntryDto;
import io.app.ConfirmTkt.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movie){
        try{
            String response = movieService.addMovie(movie);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            String response = "movie not able to added";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
