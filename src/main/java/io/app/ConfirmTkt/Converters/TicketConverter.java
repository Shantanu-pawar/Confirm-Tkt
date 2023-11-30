package io.app.ConfirmTkt.Converters;

import io.app.ConfirmTkt.Entities.TicketEntity;
import io.app.ConfirmTkt.EntryDto.TicketEntryDto;

public class TicketConverter{

    public static TicketEntity convertDtoToEntity(TicketEntryDto ticketEntryDto) {
        TicketEntity ticketEntity = new TicketEntity();
        return ticketEntity;
    }
}
