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

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{
        // validations
        if(theaterEntryDto.getLocation()==null || theaterEntryDto.getName()==null){
            throw new Exception("name and locations should be valid Entered");
        }
        // convert Dto into entity
        TheaterEntity theaterEntity = TheaterConverter.convertDtoToEntity(theaterEntryDto);

        // then we've to set the remaining mappings parameters from the entities like list and all
        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterEntryDto, theaterEntity);

        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);
        theaterRepository.save(theaterEntity);
        return "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto,
                                                       TheaterEntity theaterEntity){

        int noOfClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int noOfPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

        // now here create classic seats and premium seats
        for (int i = 1; i <= noOfClassicSeats; i++) {
            // in this we've to add new theater seat and add it to list
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.CLASSIC).seatNo(i+"C").build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        for (int i = 1; i <= noOfPremiumSeats; i++) {
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.PREMIUM)
                    .seatNo(i + "P").build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }
        return theaterSeatEntityList; // not saving the child here.
    }

}
