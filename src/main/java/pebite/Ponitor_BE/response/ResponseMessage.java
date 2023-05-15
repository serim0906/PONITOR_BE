package pebite.Ponitor_BE.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Slf4j

public class ResponseMessage {

    private String code;
    private String message;


    public ResponseMessage(Message message){
        this.code = message.getCode();
        this.message = message.getMessage();
    }

}