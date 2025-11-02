package br.com.fiap.sprint.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.UriInfo;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(BusinessException e) {
        ApiError error = new ApiError(
                Response.Status.BAD_REQUEST.getStatusCode(),
                "Bad Request",
                e.getMessage(),
                uriInfo.getPath()
        );
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
