package io.app.ConfirmTkt.Converters;


import io.app.ConfirmTkt.Entities.TheaterEntity;
import io.app.ConfirmTkt.EntryDto.TheaterEntryDto;

public class TheaterConverter {

    public static TheaterEntity convertDtoToEntity(TheaterEntryDto theaterEntryDto){

        TheaterEntity theaterEntity = TheaterEntity.builder()
                .name(theaterEntryDto.getName())
                .location(theaterEntryDto.getLocation())
                .build();

        return theaterEntity;
    }
}
