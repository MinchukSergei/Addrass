package by.bsu.web.controller.util;

public class ErrorEntity {

    private String message;

    public ErrorEntity(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
