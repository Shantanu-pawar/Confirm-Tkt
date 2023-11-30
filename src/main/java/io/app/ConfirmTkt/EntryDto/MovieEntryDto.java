package io.app.ConfirmTkt.EntryDto;

import io.app.ConfirmTkt.Enums.Genre;
import io.app.ConfirmTkt.Enums.Language;

import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;
    private double ratings;
    private int duration;
    private Language language;
    private Genre genre;

}
