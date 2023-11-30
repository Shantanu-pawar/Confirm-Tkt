package io.app.ConfirmTkt.Service;

import io.app.ConfirmTkt.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;


}
