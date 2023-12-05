package io.app.ConfirmTkt.Service;

import io.app.ConfirmTkt.Converters.TheaterConverter;
import io.app.ConfirmTkt.Entities.TheaterEntity;
import io.app.ConfirmTkt.Entities.TheaterSeatEntity;
import io.app.ConfirmTkt.EntryDto.TheaterEntryDto;
import io.app.ConfirmTkt.Enums.SeatType;
import io.app.ConfirmTkt.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

/*  thought Process :
    * always remember, before saving an entity we have to set it's attributes
    * need theater entity */

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{
        // validations
        if(theaterEntryDto.getLocation()==null || theaterEntryDto.getName()==null){
            throw new Exception("name and locations should be valid Entered");
        }
        // convert Dto into entity
        TheaterEntity theaterEntity = TheaterConverter.convertDtoToEntity(theaterEntryDto);

        /*this concept explanation:
      we've to set the remaining Attributes from entity, now in order to set this we've to create List
        |
      and now we're just created an list of type theaterSeatEntity so we also have to save that to complete this
      process
      so the same process is we've to set TheaterSeatEntities attributes in
        |
    now the remaining attributes from DTO is classicSeatsCount; premiumSeatsCount; so that's what we're saving
    so create this entities, put them into list and save it. and then save ALL

         */

        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterEntryDto, theaterEntity);

        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);
        theaterRepository.save(theaterEntity);
        return "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto, TheaterEntity theaterEntity){

        int noOfClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int noOfPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();s

        // now here create classic seats and premium seats
        for (int i = 1; i <= noOfClassicSeats; i++) {
            // in this we've to add new theater seat and add it to list
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.CLASSIC).seatNo(i+"C")
                    .theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        for (int i = 1; i <= noOfPremiumSeats; i++) {
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.PREMIUM).seatNo(i + "P")
                    .theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }
        return theaterSeatEntityList; // not saving the child here.
    }

}
