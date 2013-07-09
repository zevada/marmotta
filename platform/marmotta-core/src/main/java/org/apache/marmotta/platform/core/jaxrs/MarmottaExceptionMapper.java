package org.apache.marmotta.platform.core.jaxrs;

import org.apache.marmotta.platform.core.api.config.ConfigurationService;
import org.apache.marmotta.platform.core.exception.MarmottaException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Map MarmottaExceptions to a internal server error and return the default error object
 *
 * @author Sebastian Schaffert (sschaffert@apache.org)
 */
@Provider
@ApplicationScoped
public class MarmottaExceptionMapper implements CDIExceptionMapper<MarmottaException> {

    /**
     * Map an exception to a {@link javax.ws.rs.core.Response}. Returning
     * {@code null} results in a {@link javax.ws.rs.core.Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link javax.ws.rs.core.Response.Status#INTERNAL_SERVER_ERROR} response
     *
     * @param exception the exception to map to a response
     * @return a response mapped from the supplied exception
     */
    @Override
    public Response toResponse(MarmottaException exception) {
        return ErrorResponse.errorResponse(Response.Status.INTERNAL_SERVER_ERROR, exception);
    }
}
