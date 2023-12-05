package io.app.ConfirmTkt.EntryDto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketEntryDto {

//    showId parameter get all the information of movieName, theaterName, date,time so we can get it.
    private int showId;

//    this return's all the seat's that we've booked
    private List<String> requestedSeats = new ArrayList<>();

    private int userId;
}
