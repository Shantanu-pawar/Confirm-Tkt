package io.app.ConfirmTkt.Converters;


import io.app.ConfirmTkt.Entities.ShowEntity;
import io.app.ConfirmTkt.EntryDto.ShowEntryDto;

public class ShowConverter {

    public static ShowEntity convertShowDtoToEntity(ShowEntryDto showEntryDto){
        ShowEntity showEntity = ShowEntity.builder()

        // here we're just building the showEntity attr not other one
                .showDate(showEntryDto.getShowDate())
                .showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType())
                .build();
        return showEntity;
    }
}
