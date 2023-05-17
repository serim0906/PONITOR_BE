package pebite.Ponitor_BE.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import pebite.Ponitor_BE.model.Customer;




import java.time.LocalDateTime;



@Getter
@NoArgsConstructor
public class CustomerSaveRequestDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private String atmId;

    private boolean victim;

    @Builder
    public CustomerSaveRequestDto(LocalDateTime startTime,LocalDateTime endTime, String atmId, boolean victim){
        this.startTime = startTime;
        this.endTime = endTime;
        this.atmId = atmId;
        this.victim= victim;

    }

    public Customer toEntity(){
        return Customer.builder()
                .startTime(startTime)
                .endTime(endTime)
                .atmId(atmId)
                .victim(victim)
                .build();
    }
}

