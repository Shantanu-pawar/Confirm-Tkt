package io.app.ConfirmTkt.Service;

import io.app.ConfirmTkt.Converters.TicketConverter;
import io.app.ConfirmTkt.Entities.ShowEntity;
import io.app.ConfirmTkt.Entities.ShowSeatEntity;
import io.app.ConfirmTkt.Entities.TicketEntity;
import io.app.ConfirmTkt.Entities.UserEntity;
import io.app.ConfirmTkt.EntryDto.TicketEntryDto;
import io.app.ConfirmTkt.Repository.ShowRepository;
import io.app.ConfirmTkt.Repository.TicketRepository;
import io.app.ConfirmTkt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired TicketRepository ticketRepository;
    @Autowired ShowRepository showRepository;
    @Autowired UserRepository userRepository;

    public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception{
    //the 3 step process : we have to set the attributes | set the foreign key | save it.

        // step 1. convert the DTO to entity for saving
        TicketEntity ticketEntity = TicketConverter.convertDtoToEntity(ticketEntryDto);

//this function is for validation for checking requested seats are available for booking or not
        boolean isValid = checkValidityOfRequestedSeats(ticketEntryDto);

        if(isValid == false){
            throw new Exception("Requested seats are not available");
        }

        // calculate the total amount for seats
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> showSeatEntityList = showEntity.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount=0;
        for(ShowSeatEntity showSeatEntity : showSeatEntityList){
            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
                totalAmount = totalAmount + showSeatEntity.getPrice();

                // now we're just booking the seat status
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        // now we've to also set it into ticket entity
        ticketEntity.setTotalAmount(totalAmount);

        // now setting the foreign key attributes for ticket entity
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());

        // we need to set that string that returns requested seats
        String allotedSeats = getAllotedSeatsFromShowSeats(requestedSeats);
        ticketEntity.setBookedSeats(allotedSeats);


        // setting the foreign key attribute
        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        // we've to also set the parent
        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);

        showRepository.save(showEntity);

        // same thing for user for save the parent
        List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);

        return "Ticket booked successfully";
    }

    private String getAllotedSeatsFromShowSeats(List<String> requestedSeats){
        String result = "";
        for(String seats : requestedSeats){
            result += seats  + ", ";
        }
        return result;
    }

    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){
        // we have this custom List to return the booking seats
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        // here we're just getting showEntity using showId from DTO
        int showId = ticketEntryDto.getShowId();
        ShowEntity showEntity = showRepository.findById(showId).get();

        // and we're getting total no of seats available
        List<ShowSeatEntity> listOfSeats = showEntity.getListOfShowSeats();

        // now here iterating over list of seats for particular show
        for(ShowSeatEntity showSeatEntity: listOfSeats){
            String seatNo = showSeatEntity.getSeatNo();

            // now just check the seat is already booked or not
            if(requestedSeats.contains(seatNo)){
                if(showSeatEntity.isBooked() == true)
                    return false; //this seat is booked already
            }
        }
//    all the seats requested were available
        return true;
    }

}
