package pebite.Ponitor_BE.exception;

import pebite.Ponitor_BE.response.Message;
import pebite.Ponitor_BE.response.ResponseMessage;

public class AuthenticationException extends RuntimeException{

    private ResponseMessage responseMessage;


    public AuthenticationException(Message message) {
        super(message.getMessage());
        responseMessage = new ResponseMessage(message);
    }

    public ResponseMessage getResponseMessage(){
        return this.responseMessage;
    }

}