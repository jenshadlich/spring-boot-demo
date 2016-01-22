package de.jeha.demo.springboot.api;

/**
 * @author jenshadlich@googlemail.com
 */
public class HelloResponse {

    private final String message;

    public HelloResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
