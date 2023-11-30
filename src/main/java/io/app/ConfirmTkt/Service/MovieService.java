package io.app.ConfirmTkt.Service;

import io.app.ConfirmTkt.Converters.MovieConvertors;
import io.app.ConfirmTkt.Entities.MovieEntity;
import io.app.ConfirmTkt.EntryDto.MovieEntryDto;
import io.app.ConfirmTkt.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{
/*
        the main purpose of using movie converter Convert Movie object to JSON representation
        and movieEntry DTO : (DTO) designed for representing related information in a simplified manner.
        we must have to convert so this is that separate impl of the converter function   */

        MovieEntity movie = MovieConvertors.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
        return "Movie added successfully";
    }

}
