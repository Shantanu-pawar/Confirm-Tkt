package io.app.ConfirmTkt.Service;

import io.app.ConfirmTkt.EntryDto.TicketEntryDto;
import io.app.ConfirmTkt.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(TicketEntryDto ticketEntryDto){

        return "Ticket booked successfully";
    }
}
