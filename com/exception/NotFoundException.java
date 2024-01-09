package com.exception;

import io.dropwizard.jersey.errors.ErrorMessage;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

public class NotFoundException extends WebApplicationException {
    public NotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(HttpStatus.NOT_FOUND_404, message))
                .build());
    }
}
