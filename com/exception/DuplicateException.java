package com.exception;

import io.dropwizard.jersey.errors.ErrorMessage;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

public class DuplicateException extends WebApplicationException {
    public DuplicateException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(HttpStatus.BAD_REQUEST_400,message))
                .build());
    }
}
