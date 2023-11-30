package io.app.ConfirmTkt.Converters;

import io.app.ConfirmTkt.Entities.MovieEntity;
import io.app.ConfirmTkt.EntryDto.MovieEntryDto;

public class MovieConvertors {
//    remember whenEver we're using the Entry Dto class then in service layer
//    we must have to convert so this is that separate impl of the converter function


    // now here we just have to build the object using builder annotation for that
    //  we've to define all ARgs and no Args constructors in our Entities
    public static MovieEntity convertDtoToEntity(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity = MovieEntity.builder()
            .movieName(movieEntryDto.getMovieName()).genre(movieEntryDto.getGenre())
                .ratings(movieEntryDto.getRatings())
            .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage())

                .build();

        return movieEntity;
    }
}
