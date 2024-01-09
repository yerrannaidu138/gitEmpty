package com.exception;

import io.dropwizard.jersey.errors.ErrorMessage;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

public class AuthorizationException extends WebApplicationException {

    public AuthorizationException(String message,String details)
    {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage(HttpStatus.UNAUTHORIZED_401,message,details))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

}
