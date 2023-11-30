package io.app.ConfirmTkt.Controller;

import io.app.ConfirmTkt.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {
    @Autowired
    TheaterService theaterService;


}
