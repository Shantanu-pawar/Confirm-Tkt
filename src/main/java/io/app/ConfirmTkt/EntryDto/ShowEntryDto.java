package io.app.ConfirmTkt.EntryDto;

import io.app.ConfirmTkt.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowEntryDto {

    private LocalDate showDate;
    private LocalTime showTime;
    private ShowType showType;

    //we've to add movie and theater details also
    private int movieId;
    private int theaterId;

    // and list the pricing of particular seats while adding show
    private int classicSeatPrice;
    private int premiumSeatPrice;

}
