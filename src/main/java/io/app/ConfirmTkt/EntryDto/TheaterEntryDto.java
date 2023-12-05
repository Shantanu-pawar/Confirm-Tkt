package io.app.ConfirmTkt.EntryDto;

import lombok.Data;

@Data
public class TheaterEntryDto {

    // we've custom attributes in this DTO
    private String name;
    private String location;

    private int classicSeatsCount;

    private int premiumSeatsCount;
}
