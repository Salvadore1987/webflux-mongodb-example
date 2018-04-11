package uz.salvadore.webfluxmongodbexample.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.salvadore.webfluxmongodbexample.model.Response;

@Component
public class RestUtils<T> {

    public<T> Response<T> buildOk(String message, T obj) {
        return new Response<>(HttpStatus.OK.value(), message, obj);
    }

    public Response<T> buildError(String message) {
        return new Response<>(HttpStatus.BAD_REQUEST.value(), message, null);
    }

}
