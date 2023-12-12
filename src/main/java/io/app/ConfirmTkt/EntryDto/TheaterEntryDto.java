package io.app.ConfirmTkt.EntryDto;

import lombok.Data;

@Data
public class TheaterEntryDto {

//   we've to use custom attributes in this DTO
//   some attr is from theaterEntity and some is from theaterSeatEntity

    private String name;
    private String location;

    // and since each theater has different requirement then i'll ask that in postman
    private int classicSeatsCount;

    private int premiumSeatsCount;
}
