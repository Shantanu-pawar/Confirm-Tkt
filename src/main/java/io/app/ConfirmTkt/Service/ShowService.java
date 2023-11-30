package io.app.ConfirmTkt.Service;

import io.app.ConfirmTkt.Converters.ShowConverter;
import io.app.ConfirmTkt.Entities.*;
import io.app.ConfirmTkt.EntryDto.ShowEntryDto;
import io.app.ConfirmTkt.Enums.SeatType;
import io.app.ConfirmTkt.Repository.MovieRepository;
import io.app.ConfirmTkt.Repository.ShowRepository;
import io.app.ConfirmTkt.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto){

        // create show entity
        ShowEntity showEntity = ShowConverter.convertShowDtoToEntity(showEntryDto);

        // set remaining attr for that we've to get information from their repo itself.
        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();

        // now set that above data into our showEntity as foreign key
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

    // we've to also save the showSeat entity as painding attr now this list has to be set separately.
        List<ShowSeatEntity> seatEntityList = createShowSeatEntity(showEntryDto, showEntity);
        showEntity.setListOfShowSeats(seatEntityList);


        // now finally here we've to save and update it into repo
        showEntity = showRepository.save(showEntity);
        movieEntity.getShowEntityList().add(showEntity);
        theaterEntity.getShowEntityList().add(showEntity);

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
        return "show has been added successfully";
    }


    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto,ShowEntity showEntity){

        //Now the goal is to create the ShowSeatEntity [We need to set its attribute

        TheaterEntity theaterEntity = showEntity.getTheaterEntity();
        List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntityList();
        List<ShowSeatEntity> seatEntityList = new ArrayList<>();


        for(TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList){

            ShowSeatEntity showSeatEntity = new ShowSeatEntity();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());


            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity); //parent : foreign key for the showSeat Entity

            seatEntityList.add(showSeatEntity); //Adding it to the list
        }
        return  seatEntityList;
    }

}
