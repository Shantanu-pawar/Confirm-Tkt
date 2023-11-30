package io.app.ConfirmTkt.Controller;


import io.app.ConfirmTkt.EntryDto.TicketEntryDto;
import io.app.ConfirmTkt.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody TicketEntryDto ticketEntryDto){
        try{
            String res = ticketService.bookTicket(ticketEntryDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e){
            String res = "Ticket not able to booked! due to some issue";
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
