package pebite.Ponitor_BE.exception;

import pebite.Ponitor_BE.response.Message;
import pebite.Ponitor_BE.response.ResponseMessage;

public class ResourceNotFoundException extends RuntimeException {

    private ResponseMessage responseMessage;



    public ResourceNotFoundException(Message message) {
        super(message.getMessage());
        responseMessage = new ResponseMessage(message);
    }

    public ResourceNotFoundException() {
        super();
    }

    public ResponseMessage getResponseMessage() {
        return this.responseMessage;
    }
}