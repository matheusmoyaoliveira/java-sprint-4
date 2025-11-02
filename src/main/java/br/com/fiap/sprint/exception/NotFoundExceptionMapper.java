package br.com.fiap.sprint.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Context;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(NotFoundException e) {
        ApiError error = new ApiError(
                Response.Status.NOT_FOUND.getStatusCode(),
                "Not Found",
                e.getMessage(),
                uriInfo.getPath()
        );
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
