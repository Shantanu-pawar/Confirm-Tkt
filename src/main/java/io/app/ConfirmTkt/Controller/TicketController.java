package io.app.ConfirmTkt.Controller;


import io.app.ConfirmTkt.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;
}
