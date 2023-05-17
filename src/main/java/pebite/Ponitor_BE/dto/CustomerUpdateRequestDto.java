package pebite.Ponitor_BE.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CustomerUpdateRequestDto {
    private LocalDateTime endTime;
    @Builder
    public CustomerUpdateRequestDto( LocalDateTime endTime){
        this.endTime = endTime;
    }
}
